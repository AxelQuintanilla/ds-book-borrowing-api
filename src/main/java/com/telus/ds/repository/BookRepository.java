package com.telus.ds.repository;

import com.telus.ds.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
    Book findBybookid(Integer bookid);
    Book findByISBN(String ISBN);
}
