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
@Table(name = "user")
public class User {
    public User() {
    }
    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;  
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idUser", updatable=false)
    private int id;
    @Column(name="firstName", updatable=false)
    @Length(max = 15, min = 15)
    @NotNull(message = "first name is required")
    private String firstName;
    @Column(name="lastName", updatable=false)
    @Length(max = 15, min = 15)
    @NotNull(message = "last name is required")
    private String lastName;
}
