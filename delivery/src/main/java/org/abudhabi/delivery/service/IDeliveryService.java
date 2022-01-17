package org.abudhabi.delivery.service;

import java.util.List;

import org.abudhabi.delivery.beans.Delivery;

public interface IDeliveryService {
	List<Delivery> adjustPriority();
	void startInterval(long expiryInSeconds);
	List<String> readAllFromCache();
}
