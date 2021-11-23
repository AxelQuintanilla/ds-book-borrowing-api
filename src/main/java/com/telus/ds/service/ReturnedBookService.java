package com.telus.ds.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.telus.ds.entity.ReturnedBook;
import com.telus.ds.repository.ReturnedBookRepository;
@Service
public class ReturnedBookService {
	@Autowired
	private ReturnedBookRepository returnedbookRepository;
    
    public ReturnedBook getReturnedBook(int idReturnedBook) {
        return returnedbookRepository.findByidReturnedBook(idReturnedBook);
    } 
    
    public ReturnedBook create(ReturnedBook returnedbook) {
    	/*borrowedbookRepository.setReturnedDate(LocalDateTime.now());*/
    	return returnedbookRepository.save(returnedbook);
    }
    
    public List<ReturnedBook> getReturnedBooks() {
        return returnedbookRepository.findAll();
    }
}
