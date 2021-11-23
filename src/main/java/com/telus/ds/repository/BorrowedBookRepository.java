package com.telus.ds.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.telus.ds.entity.BorrowedBook;
import com.telus.ds.entity.Ticket;

public interface BorrowedBookRepository extends JpaRepository<BorrowedBook, Integer> {
	BorrowedBook findByidBorrowedBook(int idBorrowedBook);

}
