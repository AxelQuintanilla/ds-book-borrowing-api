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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@Slf4j
@RestController
@RequestMapping("/returnedbook")
public class ReturnedBookController {

    @Autowired
    ReturnedBookService returnedBookService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/getReturnedBook")
    public ReturnedBookDTO getReturnedBook(@RequestParam("returnedbooksid") Integer returnedBooksId) {

        ReturnedBook returnedBookFound = returnedBookService.getReturnedBook(returnedBooksId);
        if (returnedBookFound == null) {
            throw new ResourceNotFoundException("ReturnedBook not found with id=" + returnedBooksId);
        }
        return convertToDTO(returnedBookFound);
    }

    @GetMapping("/getReturnedBooks")
    public List<ReturnedBookDTO> getReturnedBooks() {
        return returnedBookService.getReturnedBooks()
                .stream()
                .map(t -> convertToDTO(t))
                .collect(Collectors.toList());
    }

    @PostMapping("/create")
    public ReturnedBookDTO create(@RequestBody ReturnedBook returnedBook) {
        log.info("Creating a returned book");
        return convertToDTO(returnedBookService.create(returnedBook));
    }
    
    @PutMapping("/update/{returnedbooksid}")
    private ReturnedBookDTO update(@RequestBody ReturnedBook returnedBookUpdated, @PathVariable("returnedbooksid") int returnedBookId) {
        ReturnedBook returnedBook = returnedBookService.getReturnedBook(returnedBookId);
        log.info("Updating a returned book");
        return convertToDTO(returnedBookService.update(returnedBook, returnedBookUpdated));
    }

    private ReturnedBookDTO convertToDTO(ReturnedBook returnedBook) {
        configModelMapper();
        return modelMapper.map(returnedBook, ReturnedBookDTO.class);
    }
    
    @DeleteMapping("/delete/{returnedbooksid}")
    private void deleteBook(@PathVariable("returnedbooksid") int returnedBookId) {
        returnedBookService.delete(returnedBookId);
    }

    private void configModelMapper() {
        if (!modelMapper.getConfiguration().getMatchingStrategy().equals(MatchingStrategies.LOOSE)) {
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        }
    }

}
