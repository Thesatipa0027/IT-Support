package com.support.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.support.entity.Ticket;
import com.support.repository.TicketRepository;
import com.support.service.TicketService;

@Service
public class TicketServiceImpl implements TicketService{

	TicketRepository repository;

	public TicketServiceImpl(TicketRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public Ticket createTicket(Ticket ticket) {
		return repository.save(ticket);
	}

	@Override
	public List<Ticket> ticketList() {
		return repository.findAll();
	}

	@Override
	public Optional<Ticket> getTicketById(long id) {
		final Optional<Ticket> ticket = repository.findById(id);
		return ticket;
	}

	@Override
	public Optional<Ticket> setAssignee(long id) {
		return repository.findById(id);
		
	}

}
