package com.telus.ds.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name = "borrowedbooks")

public class BorrowedBook {
	
	public BorrowedBook() {	}
	
	public BorrowedBook(Integer idUserClient,LocalDateTime returnDate, LocalDateTime borrowDate, Integer renewalQuantity, Integer idBook  ) {
		super();
		this.idUserClient=idUserClient;
		this.returnDate=returnDate;
		this.borrowDate=borrowDate;
		this.renewalQuantity=renewalQuantity;
		
	}
	
	@Id
	@Column(name="idBorrowedBooks", updatable=false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idBorrowedBooks;
	
	@Column(name="idUserClient", updatable=false)
	@NotNull(message = "idUserClient is required")
	private Integer idUserClient;	
	
	@Column(name="returnDate", updatable=false)
	@NotNull(message = "returnDate is required")
	private LocalDateTime returnDate;
	
	@Column(name="borrowDate", updatable=false)
	@NotNull(message = "borrowDate DATE is required")
	private LocalDateTime borrowDate;
	
	@Column(name="renewalQuantity", updatable=false)
	private Integer renewalQuantity;
	
	@Column(name="idBook", updatable=false)
	@NotNull(message = "idBook is required")
	private Integer duration;

	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "idBook", nullable = false)
    @NotNull(message = "Book is required")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
    private Book bookObj;
}
