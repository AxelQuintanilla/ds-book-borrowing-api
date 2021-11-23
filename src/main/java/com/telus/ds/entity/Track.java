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
@Table(name = "TRACKS")
public class Track {
	
	public Track() {}
	
	public Track(String isrc, Integer duration, Artist artist) {
		super();
		this.isrc = isrc;
		this.duration = duration;
		this.artistObj = artist;
	}
	
	@Id
	@Column(name="TRACK_ID", updatable=false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="ISRC", updatable=false)
	@Length(max = 12, min = 12)
	@NotNull(message = "ISRC is required")
	private String isrc;
	
	@Column(name="DURATION", updatable=false)
	@NotNull(message = "DURATION is required")
	private Integer duration;
	
	@Column(name="CREATION_DATE", updatable=false)
	@NotNull(message = "CREATION DATE is required")
	private LocalDateTime creationDate;
	
    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "ARTIST_ID", nullable = false)
    @NotNull(message = "Artist is required")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
    private Artist artistObj;
	
}
