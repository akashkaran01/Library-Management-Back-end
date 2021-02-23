package com.cg.lib.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@NoArgsConstructor
@ToString
@Entity
@Table(name = "BookIssue")
public class BookIssue {
	
	@Id
	@GeneratedValue
	private int bookIssueId;
	private String userName;
	private LocalDate issueBookDate;
	private LocalDate returnBookDate;
	
	@OneToMany(targetEntity = Books.class)
	@JoinColumn(name = "bookIssueId", referencedColumnName = "bookIssueId")
	private List<Books> book;

	@Transient
	private String serverResponse;
}
