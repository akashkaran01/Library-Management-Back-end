package com.cg.lib.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ExceptionResponse {
	
	private Date timestamp;
	private int status;
	private String error;
	private String message;
	private String path;
		
	
}
