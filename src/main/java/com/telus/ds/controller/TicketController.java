package com.telus.ds.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.telus.ds.entity.Ticket;
import com.telus.ds.entity.dto.TicketDTO;
import com.telus.ds.exception.ResourceNotFoundException;
import com.telus.ds.service.TicketService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/ticket")
public class TicketController {
    @Autowired
    TicketService ticketService;

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

    @PostMapping("/create")
    public TicketDTO create(@RequestBody Ticket ticket) {
        log.info("Creating a ticket");
        return convertToDTO(ticketService.create(ticket));
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
