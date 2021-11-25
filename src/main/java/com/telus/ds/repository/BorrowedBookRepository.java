package com.telus.ds.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;

import com.telus.ds.entity.BorrowedBook;

public interface BorrowedBookRepository extends JpaRepository<BorrowedBook, Integer> {
	BorrowedBook findByborrowedbooksid(Integer borrowedbooksid);

	
}
