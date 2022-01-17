package org.abudhabi.delivery.service;

import java.util.List;

import org.abudhabi.delivery.beans.Delivery;

public interface ICollectionService {
	void resetAll();

	void insertAll(List<Delivery> deliveries, Long expiryInSeconds);

	List<String> retrieveAll();
}
