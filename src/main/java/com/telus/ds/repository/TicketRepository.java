package com.telus.ds.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.telus.ds.entity.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer>{
    Ticket findByticketid(Integer ticketid);
    
    @Query(value="SELECT TIMESTAMPDIFF(DAY,:expectedReturnDate,:returnedDate) from bookapi.borrowedbooks where borrowedbooksid=:borrowedbooksid",nativeQuery = true)
	Integer checkDelayDay(@Param("borrowedbooksid") Integer borrowedbooksid,@Param("expectedReturnDate") LocalDate expectedReturnDate,@Param("returnedDate") LocalDate returnedDate );
	
}
