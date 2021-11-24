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
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Length;

@Entity
@Getter
@Setter
@ToString
@Table(name = "book")
public class Book {

    public Book() {
    }
    
    public Book(String book_name, String ISBN, String genre, Integer quantity, Boolean state) {
        this.book_name = book_name;
        this.ISBN = ISBN;
        this.genre = genre;
        this.quantity = quantity;
        this.state = state;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookid", updatable = false)
    private Integer bookid;
    @Column(name = "book_name", updatable = false)
    @Length(max = 45, min = 10)
    @NotNull(message = "Book name is required")
    private String book_name;
    @Column(name="ISBN", updatable=false)
    @NotNull(message = "ISBN is required")
    private String ISBN;
    @Column(name="genre", updatable=false)
    @Length(max = 30, min = 5)
    private String genre;
    @Column(name="quantity", updatable=true)
    @NotNull(message = "quantity is required")
    private Integer quantity;
    @Column(name="state", updatable=true, columnDefinition = "TINYINT")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    @NotNull(message = "state is required")
    private Boolean state;
}
