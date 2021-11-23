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
import org.hibernate.validator.constraints.Length;

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
        this.quantity = quantity;
        this.state = state;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idBook", updatable = false)
    private Integer id;
    @Column(name = "bookName", updatable = false)
    @Length(max = 45, min = 10)
    @NotNull(message = "Book name is required")
    private String bookName;
    @Column(name="ISBN", updatable=false)
    @NotNull(message = "ISBN is required")
    private Long ISBN;
    @Column(name="genre", updatable=false)
    @Length(max = 30, min = 5)
    @NotNull(message = "genre is required")
    private String genre;
    @Column(name="quantity", updatable=true)
    @NotNull(message = "quantity is required")
    private Integer quantity;
    @Column(name="state", updatable=true)
    @NotNull(message = "state is required")
    private Integer state;
}
