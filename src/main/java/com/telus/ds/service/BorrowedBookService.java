package com.telus.ds.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telus.ds.entity.BorrowedBook;
import com.telus.ds.entity.Ticket;
import com.telus.ds.repository.BorrowedBookRepository;

@Service

public class BorrowedBookService {
	 @Autowired
	    private BorrowedBookRepository borrowedbookRepository;
	    
	    public BorrowedBook getBorrowedBook(int idBorrowedBook) {
	        return borrowedbookRepository.findByidBorrowedBook(idBorrowedBook);
	    } 
	    
	    public BorrowedBook create(BorrowedBook borrowedbook) {
	    	/*borrowedbookRepository.setBorrowDate(LocalDateTime.now());*/
	    	return borrowedbookRepository.save(borrowedbook);
	    }
	    
	    public List<BorrowedBook> getBorrowedBooks() {
	        return borrowedbookRepository.findAll();
	    }
}
