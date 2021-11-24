package com.telus.ds.entity.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class ReturnedBookDTO {
	private Integer book2id;
	private Integer user2id;
	private LocalDateTime returnedDate;
}
