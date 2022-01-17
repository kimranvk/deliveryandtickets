package org.abudhabi.tickets.controller;

import java.util.Set;

import org.abudhabi.tickets.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TicketsResource {

	@Autowired
	private CollectionService collectionService;

	@GetMapping("/tickets")
	public Set<String> retrieveAllTickets() {
		return collectionService.getAll();
	}
}
