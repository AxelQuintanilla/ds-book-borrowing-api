package com.telus.ds.entity.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class ReturnedBookDTO {
	private Integer idBook2;
	private Integer idUserClient2;
	private LocalDateTime returnedDate;
}
