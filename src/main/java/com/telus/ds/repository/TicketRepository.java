package com.telus.ds.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.telus.ds.entity.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer>{
    Ticket findByidTicket(int id);
}
