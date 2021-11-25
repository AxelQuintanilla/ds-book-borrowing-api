package com.telus.ds.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telus.ds.entity.BorrowedBook;
import com.telus.ds.repository.BorrowedBookRepository;

@Service

public class BorrowedBookService {

    @Autowired
    private BorrowedBookRepository borrowedBookRepository;

    public BorrowedBook getBorrowedBook(Integer borrowedBooksId) {
        return borrowedBookRepository.findByborrowedbooksid(borrowedBooksId);
    }

    public BorrowedBook create(BorrowedBook borrowedBook) {
        /*borrowedbookRepository.setBorrowDate(LocalDateTime.now());*/
        return borrowedBookRepository.save(borrowedBook);
    }
    
    public BorrowedBook update(BorrowedBook borrowedBook, BorrowedBook borrowedBookUpdated) {
        if(borrowedBookUpdated.getExpectedReturnDate()!=null){
            borrowedBook.setExpectedReturnDate(borrowedBookUpdated.getExpectedReturnDate());
        }
        if(borrowedBookUpdated.getBorrowDate()!=null){
            borrowedBook.setBorrowDate(borrowedBookUpdated.getBorrowDate());
        }
        if(borrowedBookUpdated.getRenewalQuantity()!=null){
            borrowedBook.setRenewalQuantity(borrowedBookUpdated.getRenewalQuantity());
        }
        if(borrowedBookUpdated.getExpectedReturnDate()!=null){
            borrowedBook.setExpectedReturnDate(borrowedBookUpdated.getExpectedReturnDate());
        }
        if(borrowedBookUpdated.getReturned()!=null){
            borrowedBook.setReturned(borrowedBookUpdated.getReturned());
        }
        return borrowedBookRepository.save(borrowedBook);
    }
    
    public void delete(int borrowedBookId) {
        borrowedBookRepository.deleteById(borrowedBookId);
    }

    public List<BorrowedBook> getBorrowedBooks() {
        return borrowedBookRepository.findAll();
    }
}
