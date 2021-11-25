package com.telus.ds.service;

import com.telus.ds.entity.Book;
import com.telus.ds.repository.BookRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Book findBybookid(Integer bookid) {
        return bookRepository.findBybookid(bookid);
    }

    public Book saveOrUpdate(Book book, Book bookUpdated) {
        book.setBookname(bookUpdated.getBookname());
        if(bookUpdated.getGenre()!=null){
            book.setGenre(bookUpdated.getGenre());
        }
        /*book.setISBN(bookUpdated.getISBN());
        book.setQuantity(bookUpdated.getQuantity());
        book.setState(bookUpdated.getState());*/
        return bookRepository.save(book);
    }
    
    public Book create (Book book){
        return bookRepository.save(book);
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public void delete(int bookid) {
        bookRepository.deleteById(bookid);
    }

    public void update(Book book, int bookid) {
        bookRepository.save(book);
    }
}
