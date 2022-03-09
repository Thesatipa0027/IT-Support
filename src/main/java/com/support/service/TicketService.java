package com.support.service;

import java.util.List;
import java.util.Optional;

import com.support.entity.Ticket;

public interface TicketService {
	
	Ticket createTicket(Ticket ticket);

	List<Ticket> ticketList();

	Optional<Ticket> getTicketById(long id);

	Optional<Ticket> setAssignee(long id);

}
