package org.abudhabi.delivery.controller;

import java.util.List;

import org.abudhabi.delivery.beans.Delivery;
import org.abudhabi.delivery.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeliveryResource {

	@Autowired
	private DeliveryService deliveryService;

	@GetMapping("/deliveries")
	public List<Delivery> retrieveAllDeliveries() {
		return deliveryService.adjustPriority();
	}

	@GetMapping("/cache")
	public List<String> retrieveAllRedis() {
		return deliveryService.readAllFromCache();
	}
}
