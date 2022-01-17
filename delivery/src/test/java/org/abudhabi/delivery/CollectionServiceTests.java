package org.abudhabi.delivery;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.abudhabi.delivery.beans.Delivery;
import org.abudhabi.delivery.service.CollectionService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CollectionServiceTests {

	@Autowired
	private CollectionService collectionService;

	@Before
	public void setup() {
		collectionService.resetAll();
	}

	@Test
	public void testRetriveAll() {
		Assert.assertTrue(collectionService.retrieveAll().isEmpty());
	}

	@Test
	public void testInsertAll() {
		List<Delivery> deliveriesList = new ArrayList<Delivery>();
		deliveriesList.add(new Delivery(1l, "New", "Order received", new Date(), 10l, new Date(), 10l));
		deliveriesList.add(new Delivery(2l, "VIP", "Order Preparing", new Date(), 1l, new Date(), 2l));

		collectionService.insertAll(deliveriesList, 2l);
		List<String> deliveries = collectionService.retrieveAll();
		System.out.println("fetched deliveries:" + deliveries);
		Assert.assertTrue(!deliveries.isEmpty());
		//
		collectionService.resetAll();
	}
}
