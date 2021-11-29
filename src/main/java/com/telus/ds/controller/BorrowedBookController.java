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
import com.telus.ds.exception.*;
import com.telus.ds.service.BorrowedBookService;
import static com.telus.ds.util.Constants.*;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@Slf4j
@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/borrowedbook")
public class BorrowedBookController {

    @Autowired
    BorrowedBookService borrowedBookService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/getBorrowedBook")
    public BorrowedBookDTO getBorrowedBook(@RequestParam("borrowedbooksid") Integer borrowedbooksid) {

        BorrowedBook borrowedBookFound = borrowedBookService.getBorrowedBook(borrowedbooksid);
        if (borrowedBookFound == null) {
            throw new ResourceNotFoundException("Borrowed Book not found with id=" + borrowedbooksid);
        }
        return convertToDTO(borrowedBookFound);
    }
    
    @GetMapping("/getBorrowedBooks")
    public List<BorrowedBookDTO> getBorrowedBooks() {
        return borrowedBookService.getBorrowedBooks()
                .stream()
                .map(t -> convertToDTO(t))
                .collect(Collectors.toList());
    }

    @PostMapping("/create")
    public BorrowedBookDTO create(@RequestBody BorrowedBook borrowedBook) {
        Integer check = borrowedBookService.checkHowManyBorrowedBooks(borrowedBook);
        log.info("Books borrowed from this user: " + check.toString());
        if(check>=MAXIMUM_BOOKS_PER_USER){
            throw new BadInputParamException("You can only have up to 3 books borrowed.");
        }else {
            log.info("Creating a borrowed book");
            return convertToDTO(borrowedBookService.create(borrowedBook));
        }
    }
    
    @PutMapping("/update/{borrowedbooksid}")
    private BorrowedBookDTO update(@RequestBody BorrowedBook borrowedBookUpdated, @PathVariable("borrowedbooksid") Integer borrowedBooksId) {
        BorrowedBook borrowedBook = borrowedBookService.getBorrowedBook(borrowedBooksId);
        if (borrowedBook == null) {
            throw new ResourceNotFoundException("Borrowed Book not found with id=" + borrowedBooksId);
        }
        log.info("Updating a borrowed book");
        return convertToDTO(borrowedBookService.update(borrowedBook, borrowedBookUpdated));
    }
    
    @PutMapping("/renewal/{borrowedbooksid}")
    private BorrowedBookDTO renewal(@PathVariable("borrowedbooksid") Integer borrowedBooksId){
        BorrowedBook borrowedBook = borrowedBookService.getBorrowedBook(borrowedBooksId);
        if (borrowedBook == null) {
            throw new ResourceNotFoundException("Borrowed Book not found with id=" + borrowedBooksId);
        }
        if(borrowedBook.getRenewalQuantity()>=MAXIMUM_RENEWAL_QUANTITY){
            throw new BadInputParamException("You can only renew a book up to 2 times.");
        } else {
            log.info("Renovating a borrowed book.");
            return convertToDTO(borrowedBookService.renewal(borrowedBook));
        }
    }
    
    @PutMapping("/returnborrowedBook/{borrowedbooksid}")
    private BorrowedBookDTO returnBorrowedBook(@PathVariable("borrowedbooksid") Integer borrowedBooksId){
        BorrowedBook borrowedBook = borrowedBookService.getBorrowedBook(borrowedBooksId);
        if (borrowedBook == null) {
            throw new ResourceNotFoundException("Borrowed Book not found with id=" + borrowedBooksId);
        }
        return convertToDTO(borrowedBookService.returnBorrowedBook(borrowedBook));
    }
    
    @DeleteMapping("/delete/{borrowedbooksid}")
    private void deleteBook(@PathVariable("borrowedbooksid") int borrowedBooksId) {
        borrowedBookService.delete(borrowedBooksId);
    }

    private BorrowedBookDTO convertToDTO(BorrowedBook borrowedBook) {
        configModelMapper();
        return modelMapper.map(borrowedBook, BorrowedBookDTO.class);
    }

    private void configModelMapper() {
        if (!modelMapper.getConfiguration().getMatchingStrategy().equals(MatchingStrategies.LOOSE)) {
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        }
    }
}
