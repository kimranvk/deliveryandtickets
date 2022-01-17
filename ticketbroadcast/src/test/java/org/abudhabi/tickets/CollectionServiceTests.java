package org.abudhabi.tickets;

import org.abudhabi.tickets.service.CollectionService;
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
		;
	}

	@Test
	public void testGetAll() {
		try {
			Assert.assertNotNull(collectionService.getAll());
		} catch (Exception e) {
			Assert.fail("Could not retrive all records");
		}
	}
}
