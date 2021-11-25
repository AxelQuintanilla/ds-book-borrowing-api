package com.telus.ds.entity.dto;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class BorrowedBookDTO {
	private LocalDate expectedReturnDate;
	private LocalDate borrowDate;
	private Integer renewalQuantity;
        private LocalDate returnedDate;
        private Boolean returned;
}
