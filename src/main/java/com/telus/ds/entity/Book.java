package com.telus.ds.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Getter
@Setter
@ToString
@Table(name = "book")


public class Book {
	
    public Book() {
    }

    public Book(String bookName, Long ISBN, String genre, Integer quantity, Integer state) {
        this.bookName = bookName;
        this.ISBN = ISBN;
        this.genre = genre;
        this.quantity=quantity;
        this.state=state;   
    }
    
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name="idBook", updatable=false)
	 private Integer id;
	 private String bookName;
	 private Long ISBN;
	 private String genre;
	 private Integer quantity;
	 private Integer state;

}
