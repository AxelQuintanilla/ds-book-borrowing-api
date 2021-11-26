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

    public Book update(Book book, Book bookUpdated) {
        if(bookUpdated.getBookname()!=null){
            book.setBookname(bookUpdated.getBookname());
        }
        if(bookUpdated.getGenre()!=null){
            book.setGenre(bookUpdated.getGenre());
        }
        if(bookUpdated.getISBN()!=null){
            book.setISBN(bookUpdated.getISBN());
        }
        if(bookUpdated.getQuantity()!=null){
            book.setQuantity(bookUpdated.getQuantity());
        }
        if(bookUpdated.getState()!=null){
            book.setState(bookUpdated.getState());
        }
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
    
    public void remainingQuantity(Book book, String type){
        Book newBook = new Book();
        if(type=="create"){
            newBook.setQuantity(book.getQuantity()-1);
        }else {
            newBook.setQuantity(book.getQuantity()+1);

        }
        
        update(book, newBook);
    }
}
