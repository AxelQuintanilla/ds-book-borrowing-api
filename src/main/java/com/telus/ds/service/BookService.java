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
    
    public Book create(Book book) {
	/*user.setFirstName(firstName);
        user.setLastName(lastName);*/
	return bookRepository.save(book);
    }
    
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }
}
