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

import com.telus.ds.entity.Book;
import com.telus.ds.entity.dto.BookDTO;
import com.telus.ds.exception.ResourceNotFoundException;
import com.telus.ds.service.BookService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/getBook")
    public BookDTO getArtist(@RequestParam("idBook") Integer idBook) {
        Book book = bookService.findById(idBook);
        if (book == null) {
            throw new ResourceNotFoundException("Book not found with id=" + idBook);
        }
        return convertToDTO(book);
    }
    
    @GetMapping("/getBooks")
    public List<BookDTO> getBooks() {
        return bookService.getBooks()
                .stream()
                .map(t -> convertToDTO(t))
                .collect(Collectors.toList());
    }
    
    @PostMapping("/create")
    public BookDTO create(@RequestBody Book book) {
        log.info("Creating a book");
        return convertToDTO(bookService.create(book));
    }

    private BookDTO convertToDTO(Book book) {
        return modelMapper.map(book, BookDTO.class);
    }

    private void configModelMapper() {
        if (!modelMapper.getConfiguration().getMatchingStrategy().equals(MatchingStrategies.LOOSE)) {
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        }
    }
}
