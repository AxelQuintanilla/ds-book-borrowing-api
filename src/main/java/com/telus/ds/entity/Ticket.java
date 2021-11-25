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
@Table(name = "ticket")
public class Ticket {

    public Ticket() {
    }

    public Ticket(double total, Book book, User user) {
        super();
        this.total = total;
        this.bookObj = book;
        this.userObj = user;
    }

    @Id
    @Column(name = "ticketid", updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ticketid;

    @Column(name = "total", updatable = true)
    @NotNull(message = "total is required")
    private double total;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book3id", nullable = false)
    @NotNull(message = "Book is required")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Book bookObj;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user3id", nullable = false)
    @NotNull(message = "User is required")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User userObj;
}
