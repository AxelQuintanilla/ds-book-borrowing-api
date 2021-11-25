package com.telus.ds.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.telus.ds.entity.BorrowedBook;

public interface BorrowedBookRepository extends JpaRepository<BorrowedBook, Integer> {
	BorrowedBook findByborrowedbooksid(Integer borrowedbooksid);
        
        @Query(value = "SELECT count(*) FROM bookapi.borrowedbooks WHERE userid = :userid", nativeQuery = true)
        Integer checkBorrowedBooks(@Param("userid") Integer userid);

}
