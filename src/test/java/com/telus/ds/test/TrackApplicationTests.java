package com.telus.ds.test;

import com.telus.ds.BookBorrowingApplication;
import com.telus.ds.controller.BookController;
import com.telus.ds.entity.Book;
import com.telus.ds.entity.Ticket;
import com.telus.ds.entity.User;
import com.telus.ds.entity.dto.BookDTO;
import com.telus.ds.repository.BookRepository;
import com.telus.ds.repository.TicketRepository;
import com.telus.ds.repository.UserRepository;
import com.telus.ds.service.BookService;
import com.telus.ds.service.TicketService;
import static org.mockito.Mockito.mock;
import org.apache.logging.log4j.core.util.Assert;
import org.hamcrest.MatcherAssert;
import static org.hamcrest.Matchers.equalTo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BookBorrowingApplication.class)
class TrackApplicationTests {
    
    @Autowired
    private BookRepository bookRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private BookController bookController;
    
    @Autowired
    private TicketService ticketService;
    
    @Test 
    void findByIdBookRepository(){
        //with
        //insert data from data.sql
        
        //when
        Book resultBook = bookRepository.findBybookid(12);
        
        //then
        MatcherAssert.assertThat(resultBook.getISBN(), equalTo("015402366"));
    }
    
    @Test
    void saveUserRepository(){
        //with
        User user = new User("Steve", "Jobs");
        
        //when
        userRepository.save(user);
        User resultUser = userRepository.findByfirstName("Steve");
        
        //then
        MatcherAssert.assertThat(resultUser.getLastName(), equalTo("Jobs"));
    }
    
    @Test
    void getTicketService(){
        //with
        TicketRepository ticketRepositoryMock = mock(TicketRepository.class);
        Ticket ticket = ticketRepositoryMock.findByticketid(34);
        
        //when
        Mockito.when(ticketRepositoryMock.findByticketid(34)).thenReturn(ticket);
        Ticket resultTicket = ticketService.getTicket(34);
        
        //then
        Assert.isNonEmpty(resultTicket);
    }
    
    @Test
    void getBookController(){
        //with
        BookService bookServiceMock = mock(BookService.class);
        Book book = new Book("Learn REACT development", "333444555", "Education", 2, true);
        bookRepository.save(book);
        
        //when
        Mockito.when(bookServiceMock.findByISBN("333444555")).thenReturn(book);
        BookDTO resultBookDTO = bookController.getBookByISBN("333444555");
        
        //then
        Assert.isNonEmpty(resultBookDTO);
    }
}
