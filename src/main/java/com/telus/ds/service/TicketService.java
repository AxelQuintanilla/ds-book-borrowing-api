package com.telus.ds.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telus.ds.entity.Ticket;
import com.telus.ds.entity.User;
import com.telus.ds.repository.TicketRepository;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;
    
    public Ticket getTicket(Integer ticketid) {
        return ticketRepository.findByticketid(ticketid);
    } 
    
    public Ticket create(Ticket ticket) {
	return ticketRepository.save(ticket);
    }
    
    public List<Ticket> getTickets() {
        return ticketRepository.findAll();
    }
    
    public void delete(Integer ticketid) {
    	ticketRepository.deleteById(ticketid);
    }
    
    public Ticket findByticketid(Integer ticketid) {
        return ticketRepository.findByticketid(ticketid);
    }
    
    public Ticket update(Ticket ticket, Ticket ticketUpdated) {
        if(ticketUpdated.getTotal()!=0){
        	ticket.setTotal(ticketUpdated.getTotal());
        }

        return ticketRepository.save(ticket);
    }
    
    public void update(Ticket ticket, int ticketid) {
    	ticketRepository.save(ticket);
    }
}
