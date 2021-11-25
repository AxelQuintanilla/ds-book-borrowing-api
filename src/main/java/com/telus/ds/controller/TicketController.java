package com.telus.ds.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.telus.ds.entity.BorrowedBook;
import com.telus.ds.entity.Ticket;
import com.telus.ds.entity.User;
import com.telus.ds.entity.dto.TicketDTO;
import com.telus.ds.entity.dto.UserDTO;
import com.telus.ds.exception.ResourceNotFoundException;
import com.telus.ds.service.BorrowedBookService;
import com.telus.ds.service.TicketService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/ticket")
public class TicketController {
    @Autowired
    TicketService ticketService;
    @Autowired
    BorrowedBookService borrowedBookService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/getTicket")
    public TicketDTO getTicket(@RequestParam("ticketid") Integer ticketid) {

        Ticket ticketFound = ticketService.getTicket(ticketid);
        if (ticketFound == null) {
            throw new ResourceNotFoundException("Ticket not found with id=" + ticketid);
        }
        return convertToDTO(ticketFound);
    }

    @GetMapping("/getTickets")
    public List<TicketDTO> getTickets() {
        return ticketService.getTickets()
                .stream()
                .map(t -> convertToDTO(t))
                .collect(Collectors.toList());
    }
    

    @PostMapping("/create/{borrowedbooksid}")
    public TicketDTO create(@PathVariable ("borrowedbooksid") Integer borrowedbooksid) {
        BorrowedBook borrowedbookFound = borrowedBookService.getBorrowedBook(borrowedbooksid);
        
        log.info("Creating a ticket");
        return convertToDTO(ticketService.create(borrowedbookFound));
    }
    
    @PutMapping("/update/{ticketid}")
    private TicketDTO update(@RequestBody Ticket ticketUpdated, @PathVariable("ticketid") int ticketid) {
        Ticket ticket = ticketService.findByticketid(ticketid);
        log.info("Updating a ticket");
        return convertToDTO(ticketService.update(ticket, ticketUpdated));
    }
    
    @DeleteMapping("/delete/{ticketid}")
    private void deleteTicket(@PathVariable("ticketid") int ticketid) {
    	ticketService.delete(ticketid);
    }
    
 

    private TicketDTO convertToDTO(Ticket ticket) {
        configModelMapper();
        return modelMapper.map(ticket, TicketDTO.class);
    }

    private void configModelMapper() {
        if (!modelMapper.getConfiguration().getMatchingStrategy().equals(MatchingStrategies.LOOSE)) {
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        }
    }
}
