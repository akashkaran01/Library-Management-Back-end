package com.cg.lib.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@NoArgsConstructor
@ToString
@Entity
public class Admin {
	
	@Id
	private String username;
	private String password;
	
	@Transient
	private String serverResponse;
}
