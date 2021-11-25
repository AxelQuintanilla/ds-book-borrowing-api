package com.telus.ds.entity;

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
import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;

@Entity
@Getter
@Setter
@ToString
@Table(name = "borrowedbooks")

public class BorrowedBook {

    public BorrowedBook() {
    }

    public BorrowedBook(LocalDate returnDate, LocalDate borrowDate, Integer renewalQuantity, Integer idBook) {
        super();
        this.expectedReturnDate = returnDate;
        this.borrowDate = borrowDate;
        this.renewalQuantity = renewalQuantity;
    }

    @Id
    @Column(name = "borrowedbooksid", updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer borrowedbooksid;

    @Column(name = "expectedreturn_date", updatable = true)
    private LocalDate expectedReturnDate;
    
    @Column(name = "returned_date", updatable = true)
    @NotNull(message = "returnedDate is required")
    private LocalDate returnedDate;

    @Column(name = "borrow_date", updatable = true)
    @NotNull(message = "borrowDate DATE is required")
    private LocalDate borrowDate;

    @Column(name = "renewal_quantity", updatable = true)
    private Integer renewalQuantity;
    
    @Column(name="returned", updatable=true, columnDefinition = "TINYINT")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    @NotNull(message = "state is required")
    private Boolean returned;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bookid", nullable = false)
    @NotNull(message = "Book is required")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Book bookObj;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid", nullable = false)
    @NotNull(message = "User is required")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User userObj;
}
