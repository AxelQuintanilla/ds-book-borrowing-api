package com.telus.ds.controller;

import com.telus.ds.entity.Book;
import com.telus.ds.entity.dto.BookDTO;
import com.telus.ds.exception.ResourceNotFoundException;
import com.telus.ds.service.BookService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")

public class BookController {
	@Autowired
    BookService bookService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/getBook")
    public BookDTO getArtist(@RequestParam("idBook") Integer idBook){
        Book book = bookService.findById(idBook);
        if(book == null ){
            throw new ResourceNotFoundException("Book not found with id=" + idBook);
        }
        return convertToDTO(book);
    }
    
    private BookDTO convertToDTO(Book book) {
        return modelMapper.map(book, BookDTO.class);
    }
    
    private void configModelMapper() {
		if(!modelMapper.getConfiguration().getMatchingStrategy().equals(MatchingStrategies.LOOSE)){
			modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		}
	}
}
