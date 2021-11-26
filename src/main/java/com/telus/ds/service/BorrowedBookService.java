package com.telus.ds.service;

import com.telus.ds.entity.Book;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telus.ds.entity.BorrowedBook;
import com.telus.ds.exception.BadInputParamException;
import com.telus.ds.repository.BookRepository;
import com.telus.ds.repository.BorrowedBookRepository;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service

public class BorrowedBookService {

    @Autowired
    private BorrowedBookRepository borrowedBookRepository;
    
    @Autowired
    private BookRepository bookRepository;
    
    @Autowired
    private BookService bookService;

    public BorrowedBook getBorrowedBook(Integer borrowedBooksId) {
        return borrowedBookRepository.findByborrowedbooksid(borrowedBooksId);
    }

    public BorrowedBook create(BorrowedBook borrowedBook) {
        borrowedBook.setBorrowDate(LocalDate.now());
        borrowedBook.setExpectedReturnDate(LocalDate.now().plusDays(7));
        Book book = bookRepository.findBybookid(borrowedBook.getBookObj().getBookid());
        String type = "create";
        if(book.getQuantity()<=0){
            throw new BadInputParamException("There are no available books.");
        } else {
            bookService.remainingQuantity(book, type);
            return borrowedBookRepository.save(borrowedBook);
        }
    }

    public BorrowedBook update(BorrowedBook borrowedBook, BorrowedBook borrowedBookUpdated) {
        if (borrowedBookUpdated.getExpectedReturnDate() != null) {
            borrowedBook.setExpectedReturnDate(borrowedBookUpdated.getExpectedReturnDate());
        }
        if (borrowedBookUpdated.getBorrowDate() != null) {
            borrowedBook.setBorrowDate(borrowedBookUpdated.getBorrowDate());
        }
        if (borrowedBookUpdated.getRenewalQuantity() != null) {
            borrowedBook.setRenewalQuantity(borrowedBookUpdated.getRenewalQuantity());
        }
        if (borrowedBookUpdated.getReturnedDate() != null) {
            borrowedBook.setReturnedDate(borrowedBookUpdated.getReturnedDate());
        }
        if (borrowedBookUpdated.getReturned() != null) {
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

    public Integer checkHowManyBorrowedBooks(BorrowedBook bBook) {
        Integer quantityBorrowedBooks = borrowedBookRepository.checkBorrowedBooks(bBook.getUserObj().getUserid());
        return quantityBorrowedBooks;
    }
    
    public BorrowedBook renewal(BorrowedBook borrowedBook){
        BorrowedBook borrowedBookRenewal = new BorrowedBook();
        borrowedBookRenewal.setRenewalQuantity(borrowedBook.getRenewalQuantity()+1);
        borrowedBookRenewal.setBorrowDate(LocalDate.now());
        borrowedBookRenewal.setExpectedReturnDate(LocalDate.now().plusDays(7));
        return update(borrowedBook, borrowedBookRenewal);
    }
    
    public BorrowedBook returnBorrowedBook(BorrowedBook borrowedBook){
        BorrowedBook borrowedBookReturned = new BorrowedBook();
        borrowedBookReturned.setReturned(Boolean.TRUE);
        borrowedBookReturned.setReturnedDate(LocalDate.now());
        Book book = bookRepository.findBybookid(borrowedBook.getBookObj().getBookid());
        String type = "return";
        bookService.remainingQuantity(book, type);
        return update(borrowedBook, borrowedBookReturned);
    }
}
