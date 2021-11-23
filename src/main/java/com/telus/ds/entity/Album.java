package com.telus.ds.entity;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name = "ALBUMS")
public class Album {
	
	@Id
	@Column(name="ALBUM_ID", updatable=false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="AUID", updatable=false)
	@Length(max = 12, min = 12)
	@NotNull(message = "AUID is required")
	private String auid;
	
	@Column(name="NAME", updatable=false)
	@NotNull(message = "NAME is required")
	private String name;
	
	//private ArrayList<Track> tracks;
	
	public Album() {}
	
	public Album(String auid, String name) {
		super();
		this.auid = auid;
		this.name = name;
	}
	
	

}
