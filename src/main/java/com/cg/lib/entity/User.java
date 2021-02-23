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
@Table(name="Users")
public class User {
	
	@Id
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private LocalDate dob;
	private Long mob_No;
	private String email;
	private Boolean approved;
	
	@Transient
	private String serverResponse;
}
