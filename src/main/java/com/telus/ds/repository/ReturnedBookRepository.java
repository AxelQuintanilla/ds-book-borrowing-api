package com.telus.ds.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.telus.ds.entity.ReturnedBook;


public interface ReturnedBookRepository extends JpaRepository<ReturnedBook, Integer> {
	ReturnedBook findByidReturnedBook(int idReturnedBook);

}
