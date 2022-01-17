package org.abudhabi.delivery.service;

import java.util.Date;
import java.util.List;

import org.abudhabi.delivery.beans.Delivery;
import org.abudhabi.delivery.repository.DeliveryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class DeliveryService implements IDeliveryService {
	private static final Logger logger = LoggerFactory.getLogger(DeliveryService.class);

	@Autowired
	private DeliveryRepository deliveryRepository;

	@Autowired
	private CollectionService collectionService;

	@Override
	public List<Delivery> adjustPriority() {
		List<Delivery> deliveries = deliveryRepository.findAll();
		for (Delivery delivery : deliveries) {
			if ("VIP".equals(delivery.getCustomerType())) {
				delivery.setPriority("High");
				continue;
			}
			
			// (Order received, Order Preparing, Order Pickedup, Order Delivered
			if (new Date().after(delivery.getExpectedDeliveryTime())
					&& !"Order Delivered".equals(delivery.getDeliveryStatus())) {
				delivery.setPriority("High");
				continue;
			}

			if(!"Order Delivered".equals(delivery.getDeliveryStatus())) {
				Long minutes = delivery.getMeanTimeToPrepareMins();
				Date timeToReach = delivery.getTimeToReachDestination();
				final long ONE_MINUTE_IN_MILLIS = 60000;
	
				long timeToReachAfterAddingFoodPreparationMinutes = timeToReach.getTime()
						+ (minutes * ONE_MINUTE_IN_MILLIS);
				Date estimatedTime = new Date(timeToReachAfterAddingFoodPreparationMinutes);
				if (estimatedTime.after(delivery.getExpectedDeliveryTime())) {
					delivery.setPriority("High");
					continue;
				}
			}
			
			delivery.setPriority("Low");
			if(logger.isDebugEnabled()) {
				logger.debug("adjustPriority - delivery.id:[{}], priority:[{}]", delivery.getDeliveryId(), delivery.getPriority());
			}
		}
	   
		return deliveries;
	}

	@Async
	@Override
	public void startInterval(long expiryInSeconds) {
		List<Delivery> deliveries = adjustPriority();
		collectionService.resetAll();
		collectionService.insertAll(deliveries, expiryInSeconds);
	}

	@Override
	public List<String> readAllFromCache() {
		return collectionService.retrieveAll();
	}
}
