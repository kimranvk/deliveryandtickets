package org.abudhabi.delivery;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.abudhabi.delivery.beans.Delivery;
import org.abudhabi.delivery.controller.DeliveryResource;
import org.abudhabi.delivery.service.DeliveryService;
import org.abudhabi.delivery.utils.JsonUtils;
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
public class DeliveryControllerTests {

	private MockMvc mvc;

	private DeliveryService deliveryService = Mockito.mock(DeliveryService.class);

	public static final MediaType APPLICATION_JSON = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype());

	@Before
	public void setup() {
		DeliveryResource deliveryResource = new DeliveryResource();
		Whitebox.setInternalState(deliveryResource, "deliveryService", deliveryService);
		mvc = MockMvcBuilders.standaloneSetup(deliveryResource).build();
	}

	@org.junit.Test
	public void testRetrieveAllDeliveries() {
		List<Delivery> deliveriesList = new ArrayList<Delivery>();
		deliveriesList.add(new Delivery(1l, "New", "Order delivered", new Date(), 10l, new Date(), 10l));
		// deliveriesList.add(new Delivery(2l, "VIP", "Order prepared", new
		// Date(), 1l, new Date(), 2l));

		when(deliveryService.adjustPriority()).thenReturn(deliveriesList);

		try {
			// System.out.println("HI:"+mvc.perform(get("/deliveries")).toString());
			MvcResult mvcResult = mvc.perform(get("/deliveries")).andExpect(status().is2xxSuccessful())
					.andExpect(content().contentType(APPLICATION_JSON)).andReturn();
			Assert.assertTrue(mvcResult.getResponse().getContentAsString().contains("Order"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("could not make call to /deliveries");
		}

		Mockito.verify(deliveryService, Mockito.times(1)).adjustPriority();
	}

	@org.junit.Test
	public void testRetrieveAllRedis() {
		List<String> deliveriesList = new ArrayList<>();
		deliveriesList
				.add(JsonUtils.toJson(new Delivery(1l, "New", "Order delivered", new Date(), 10l, new Date(), 10l)));
		when(deliveryService.readAllFromCache()).thenReturn(deliveriesList);

		try {
			MvcResult mvcResult = mvc.perform(get("/cache")).andExpect(status().is2xxSuccessful())
					.andExpect(content().contentType(APPLICATION_JSON)).andReturn();

			Assert.assertTrue(mvcResult.getResponse().getContentAsString().contains("Order"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("could not make call to /deliveries");
		}

		Mockito.verify(deliveryService, Mockito.times(1)).readAllFromCache();
	}

	// private String getJson(Object obj) throws JsonProcessingException {
	// ObjectMapper mapper = new ObjectMapper();
	// mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	// ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	// return ow.writeValueAsString(obj);
	// }

}