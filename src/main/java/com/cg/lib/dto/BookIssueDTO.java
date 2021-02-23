package com.cg.lib.dto;

import java.time.LocalDate;
import java.util.List;

import com.cg.lib.entity.Books;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class BookIssueDTO {
	
	private int bookIssueId;
	private String userName;
	private LocalDate issueBookDate;
	private LocalDate returnBookDate;
	private List<Books> book;
	private String serverResponse;

}
