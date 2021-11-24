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

import com.telus.ds.entity.BorrowedBook;
import com.telus.ds.entity.dto.BorrowedBookDTO;
import com.telus.ds.exception.ResourceNotFoundException;
import com.telus.ds.service.BorrowedBookService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/borrowedbook")
public class BorrowedBookController {
	 @Autowired
	 BorrowedBookService borrowedbookService;

	    @Autowired
	    private ModelMapper modelMapper;

	    @GetMapping("/getBorrowedBook")
	    public BorrowedBookDTO getBorrowedBook(@RequestParam("borrowedbooksid") Integer borrowedbooksid) {

	    	BorrowedBook borrowedbookFound = borrowedbookService.getBorrowedBook(borrowedbooksid);
	        if (borrowedbookFound == null) {
	            throw new ResourceNotFoundException("Borrowed Book not found with id=" + borrowedbooksid);
	        }
	        return convertToDTO(borrowedbookFound);
	    }
	    
	    @GetMapping("/getBorrowedBooks")
	    public List<BorrowedBookDTO> getBorrowedBooks() {
	        return borrowedbookService.getBorrowedBooks()
	                .stream()
	                .map(t -> convertToDTO(t))
	                .collect(Collectors.toList());
	    }

	    @PostMapping("/create")
	    public BorrowedBookDTO create(@RequestBody BorrowedBook borrowedbook) {
	        log.info("Creating a borrowedbook");
	        return convertToDTO(borrowedbookService.create(borrowedbook));
	    }

	    
	    private BorrowedBookDTO convertToDTO(BorrowedBook borrowedbook) {
	        configModelMapper();
	        return modelMapper.map(borrowedbook, BorrowedBookDTO.class);
	    }
	    
	    private void configModelMapper() {
	        if (!modelMapper.getConfiguration().getMatchingStrategy().equals(MatchingStrategies.LOOSE)) {
	            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
	        }
	    }
}
