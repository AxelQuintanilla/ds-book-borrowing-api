package com.telus.ds.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telus.ds.entity.Ticket;
import com.telus.ds.repository.TicketRepository;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;
    
    public Ticket getTicket(Integer ticketid) {
        return ticketRepository.findByticketid(ticketid);
    } 
    
    public Ticket create(Ticket ticket) {
	/*user.setFirstName(firstName);
        user.setLastName(lastName);*/
	return ticketRepository.save(ticket);
    }
    
    public List<Ticket> getTickets() {
        return ticketRepository.findAll();
    }
}
