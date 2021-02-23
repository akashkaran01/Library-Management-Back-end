package com.cg.lib.dto;

import java.time.LocalDate;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@NoArgsConstructor
@ToString
public class UserDTO {
	
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private LocalDate dob;
	private Long mob_No;
	private String email;
	private Boolean approved;
	private String serverResponse;	
}
