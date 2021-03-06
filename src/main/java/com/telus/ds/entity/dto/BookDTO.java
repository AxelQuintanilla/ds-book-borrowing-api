package com.telus.ds.entity.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class BookDTO {
	private String bookname;
	private String ISBN;
	private String genre;
	private Integer quantity;
	private Boolean state;
}
