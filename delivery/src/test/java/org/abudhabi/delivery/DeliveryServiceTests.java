package org.abudhabi.delivery;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.abudhabi.delivery.beans.Delivery;
import org.abudhabi.delivery.repository.DeliveryRepository;
import org.abudhabi.delivery.service.CollectionService;
import org.abudhabi.delivery.service.DeliveryService;
import org.abudhabi.delivery.utils.JsonUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.Whitebox;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
// @SpringBootTest
public class DeliveryServiceTests {
	private CollectionService collectionService = Mockito.mock(CollectionService.class);
	private DeliveryRepository deliveryRepository = Mockito.mock(DeliveryRepository.class);
	private DeliveryService deliveryService = new DeliveryService();

	@Before
	public void setup() {
		Whitebox.setInternalState(deliveryService, "deliveryRepository", deliveryRepository);
		Whitebox.setInternalState(deliveryService, "collectionService", collectionService);
	}

	@Test
	public void testAdjustPriorityVIP() {
		List<Delivery> deliveriesList = new ArrayList<Delivery>();
		deliveriesList.add(new Delivery(1l, "New", "Order delivered", new Date(), 10l, new Date(), 10l));
		deliveriesList.add(new Delivery(2l, "VIP", "Order prepared", new Date(), 1l, new Date(), 2l));

		when(deliveryRepository.findAll()).thenReturn(deliveriesList);
		deliveriesList = deliveryService.adjustPriority();
		Delivery delivery = deliveriesList.get(1);
		Assert.assertNotNull(delivery);
		Assert.assertEquals(delivery.getCustomerType(), "VIP");
		Assert.assertEquals(delivery.getPriority(), "High");
	}

	@Test
	public void testAdjustPriorityNEWButDelayed() {
		List<Delivery> deliveriesList = new ArrayList<Delivery>();
		deliveriesList.add(new Delivery(1l, "New", "Order received", new Date(System.currentTimeMillis() - 60000), 10l,
				new Date(), 10l));
		deliveriesList.add(new Delivery(2l, "VIP", "Order Preparing", new Date(), 1l, new Date(), 2l));

		when(deliveryRepository.findAll()).thenReturn(deliveriesList);
		deliveriesList = deliveryService.adjustPriority();
		Delivery delivery = deliveriesList.get(0);
		Assert.assertNotNull(delivery);
		Assert.assertEquals(delivery.getCustomerType(), "New");
		Assert.assertEquals(delivery.getPriority(), "High");
	}

	@Test
	public void testAdjustPriorityNEWButExceedExpectedTime() {
		// (Order received, Order Preparing, Order Pickedup, Order Delivered
		List<Delivery> deliveriesList = new ArrayList<Delivery>();
		Delivery newDelivery = new Delivery(1l, "New", "Order received", new Date(), 10l, new Date(), 10l);
		newDelivery.setMeanTimeToPrepareMins(120l);
		newDelivery.setTimeToReachDestination(new Date());
		newDelivery.setExpectedDeliveryTime(new Date(System.currentTimeMillis() + 60000));
		deliveriesList.add(newDelivery);
		deliveriesList.add(new Delivery(2l, "VIP", "Order Preparing", new Date(), 1l, new Date(), 2l));

		when(deliveryRepository.findAll()).thenReturn(deliveriesList);
		deliveriesList = deliveryService.adjustPriority();
		Delivery delivery = deliveriesList.get(0);
		Assert.assertNotNull(delivery);
		Assert.assertEquals(delivery.getCustomerType(), "New");
		Assert.assertEquals(delivery.getPriority(), "High");
	}

	@Test
	public void testAdjustPriorityNEWButLowPriority() {
		// (Order received, Order Preparing, Order Pickedup, Order Delivered
		List<Delivery> deliveriesList = new ArrayList<Delivery>();
		Delivery newDelivery = new Delivery(1l, "New", "Order received", new Date(), 10l, new Date(), 10l);
		newDelivery.setMeanTimeToPrepareMins(120l);
		newDelivery.setTimeToReachDestination(new Date());
		newDelivery.setExpectedDeliveryTime(new Date(System.currentTimeMillis() + (1000 * 60000)));
		deliveriesList.add(newDelivery);
		deliveriesList.add(new Delivery(2l, "VIP", "Order Preparing", new Date(), 1l, new Date(), 2l));

		when(deliveryRepository.findAll()).thenReturn(deliveriesList);
		deliveriesList = deliveryService.adjustPriority();
		Delivery delivery = deliveriesList.get(0);
		Assert.assertNotNull(delivery);
		Assert.assertEquals(delivery.getCustomerType(), "New");
		Assert.assertEquals(delivery.getPriority(), "Low");
	}

	@Test
	public void testStartInterval() {
		List<Delivery> deliveriesList = new ArrayList<Delivery>();
		deliveriesList.add(new Delivery(2l, "VIP", "Order Preparing", new Date(), 1l, new Date(), 2l));

		when(deliveryService.adjustPriority()).thenReturn(deliveriesList);
		doNothing().when(collectionService).resetAll();
		doNothing().when(collectionService).insertAll(deliveriesList, 2l);

		deliveryService.startInterval(2l);
		Mockito.verify(collectionService, Mockito.times(1)).resetAll();
		Mockito.verify(collectionService, Mockito.times(1)).insertAll(deliveriesList, 2l);
	}

	@Test
	public void testReadAllFromCache() {
		List<String> deliveriesList = new ArrayList<>();
		deliveriesList
				.add(JsonUtils.toJson(new Delivery(2l, "VIP", "Order Preparing", new Date(), 1l, new Date(), 2l)));
		when(collectionService.retrieveAll()).thenReturn(deliveriesList);
		Assert.assertTrue(!deliveryService.readAllFromCache().isEmpty());
		Mockito.verify(collectionService, Mockito.times(1)).retrieveAll();
	}
}
