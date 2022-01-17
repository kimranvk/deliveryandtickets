package org.abudhabi.tickets;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

import org.abudhabi.tickets.beans.Delivery;
import org.abudhabi.tickets.controller.TicketsResource;
import org.abudhabi.tickets.service.CollectionService;
import org.abudhabi.tickets.utils.JsonUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.Whitebox;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { Main.class })
@WebAppConfiguration
@SpringBootTest
public class TicketsControllerTests {

	private MockMvc mvc;

	private CollectionService collectionService = Mockito.mock(CollectionService.class);

	public static final MediaType APPLICATION_JSON = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype());

	@Before
	public void setup() {
		TicketsResource ticketsResource = new TicketsResource();
		Whitebox.setInternalState(ticketsResource, "collectionService", collectionService);
		mvc = MockMvcBuilders.standaloneSetup(ticketsResource).build();
	}

	@org.junit.Test
	public void testRetrieveAllTickets() {
		Set<String> deliveriesList = new TreeSet<>();
		Delivery delivery = new Delivery(1l, "New", "Order delivered", new Date(), 10l, new Date(), 10l);
		
		System.out.println("id:"+delivery.getDeliveryId());
		System.out.println("getCurrentDistanceFromDestinationInMeters:"+delivery.getCurrentDistanceFromDestinationInMeters());
		System.out.println("getCustomerType:"+delivery.getCustomerType());
		System.out.println("getDeliveryStatus:"+delivery.getDeliveryStatus());
		System.out.println("getPriority:"+delivery.getPriority());
		System.out.println("getMeanTimeToPrepareMins:"+delivery.getMeanTimeToPrepareMins());
		System.out.println("getExpectedDeliveryTime:"+delivery.getExpectedDeliveryTime());
		System.out.println("getTimeToReachDestination:"+delivery.getTimeToReachDestination());
		deliveriesList.add(JsonUtils.toJson(delivery));
		when(collectionService.getAll()).thenReturn(deliveriesList);

		try {
			// System.out.println("HI:"+mvc.perform(get("/deliveries")).toString());
			MvcResult mvcResult = mvc.perform(get("/tickets")).andExpect(status().is2xxSuccessful())
					.andExpect(content().contentType(APPLICATION_JSON)).andReturn();
			Assert.assertTrue(mvcResult.getResponse().getContentAsString().contains("Order"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("could not make call to /tickets");
		}

		Mockito.verify(collectionService, Mockito.times(1)).getAll();
	}

}