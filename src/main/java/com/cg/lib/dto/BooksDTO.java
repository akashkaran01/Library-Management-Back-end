package com.cg.lib.dto;

import java.time.LocalDate;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class BooksDTO {
	
	private String bookName;
	private String authorName;
	private LocalDate publishedYear;
	
	private String serverResponse;
}
