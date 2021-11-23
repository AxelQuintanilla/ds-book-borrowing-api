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
@Table(name = "ARTISTS")
public class Artist {
	
    public Artist() {
    }

    public Artist(String name, String phone, Integer age) {
        this.name = name;
        this.phone = phone;
        this.age = age;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ARTIST_ID", updatable=false)
    private Long id;
    private String name;
    private String phone;
    private Integer age;

    @Transient
	@JsonIgnore
	@OneToMany(mappedBy = "artistObj")
	private List<Track> tracks;

}
