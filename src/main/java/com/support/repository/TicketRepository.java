package com.support.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.support.entity.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

}
