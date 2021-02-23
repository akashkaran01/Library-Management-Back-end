package com.cg.lib.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@NoArgsConstructor
@ToString
@Entity
@Table(name="Books")
public class Books {
	
	@Id
	private String bookName;
	private String authorName;
	private LocalDate publishedYear;	
	
	@Transient
	private String serverResponse;	
	
}
