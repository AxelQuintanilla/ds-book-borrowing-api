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

import com.telus.ds.entity.ReturnedBook;
import com.telus.ds.entity.dto.ReturnedBookDTO;
import com.telus.ds.exception.ResourceNotFoundException;
import com.telus.ds.service.ReturnedBookService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/returnedbook")
public class ReturnedBookController {
	 @Autowired
	 ReturnedBookService returnedbookService;

	    @Autowired
	    private ModelMapper modelMapper;

	    @GetMapping("/getReturnedBook")
	    public ReturnedBookDTO getReturnedBook(@RequestParam("idReturnedBook") int idReturnedBook) {

	    	ReturnedBook returnedbookFound = returnedbookService.getReturnedBook(idReturnedBook);
	        if (returnedbookFound == null) {
	            throw new ResourceNotFoundException("ReturnedBook not found with id=" + idReturnedBook);
	        }
	        return convertToDTO(returnedbookFound);
	    }
	    
	    @GetMapping("/getReturnedBooks")
	    public List<ReturnedBookDTO> getReturnedBooks() {
	        return returnedbookService.getReturnedBooks()
	                .stream()
	                .map(t -> convertToDTO(t))
	                .collect(Collectors.toList());
	    }

	    @PostMapping("/create")
	    public ReturnedBookDTO create(@RequestBody ReturnedBook returnedbook) {
	        log.info("Creating a returnedbook");
	        return convertToDTO(returnedbookService.create(returnedbook));
	    }

	    
	    private ReturnedBookDTO convertToDTO(ReturnedBook returnedbook) {
	        configModelMapper();
	        return modelMapper.map(returnedbook, ReturnedBookDTO.class);
	    }
	    
	    private void configModelMapper() {
	        if (!modelMapper.getConfiguration().getMatchingStrategy().equals(MatchingStrategies.LOOSE)) {
	            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
	        }
	    }
	
}
