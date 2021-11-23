package com.telus.ds.service;

import com.telus.ds.entity.Artist;
import com.telus.ds.entity.Book;
import com.telus.ds.repository.ArtistRepository;
import com.telus.ds.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class BookService {
	 @Autowired
	    private BookRepository bookRepository;
	    public Book findById(Integer idBook){
	        return bookRepository.findBookById(idBook);
	    }
}
