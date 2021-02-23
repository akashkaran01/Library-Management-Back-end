package com.cg.lib.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.lib.dto.BookIssueDTO;
import com.cg.lib.dto.UserDTO;
import com.cg.lib.entity.BookIssue;
import com.cg.lib.entity.Books;
import com.cg.lib.entity.User;
import com.cg.lib.service.UserServiceImpl;
import com.cg.lib.util.Util;

@RestController
@RequestMapping("/user")
@CrossOrigin("http://localhost:4200")
public class UserController {
	
	@Autowired
	private UserServiceImpl userService;
	
	/**
     * This method is for Registration of User to the database
     * 
     *
     * @param UserDTO 
     * @return response to server(UserDTO)
     */
	@PostMapping("/registerUser")
	public ResponseEntity<UserDTO> userRegistration(@RequestBody UserDTO userDto) 
	{
		System.out.println("User Date of Birth : "+userDto.getDob());
		User newUser = Util.dtoToUser(userDto);
		User result = userService.userRegistration(newUser);
		UserDTO finalResult = Util.userToDto(result);
		ResponseEntity<UserDTO> response = 
				new ResponseEntity<UserDTO>(finalResult, HttpStatus.OK);
		return response;
	}
	
	/**
    * This method is for User Login
    * 
    *
    * @param username and password
    * @return response to server(UserDTO)
    */
	@GetMapping("/validate/{username}/{password}")
	public ResponseEntity<UserDTO> userValidation(@PathVariable("username") 
	String username,@PathVariable("password") String password)
	{
		User result = userService.userValidation(username, password);
		UserDTO finalResult = Util.userToDto(result);
		ResponseEntity<UserDTO> response = 
				new ResponseEntity<UserDTO>(finalResult, HttpStatus.OK);
		return response;
	}
	
	/**
    * This method issues Books to the User 
    * 
    *
    * @param BookIssueDTo
    * @return response to server(BookIssueDTO)
    */
	@PostMapping("/issueBooks")
	public ResponseEntity<BookIssueDTO> issuedBooks(@RequestBody BookIssueDTO bookIssueDto)
	{
		System.out.println("Test 1 : "+bookIssueDto);
		BookIssue bookIssueObj = Util.dtoToBookIssue(bookIssueDto);
		BookIssue bookIssue = userService.issueBooks(bookIssueObj);
		BookIssueDTO result = Util.BookIssueToDto(bookIssue);
		ResponseEntity<BookIssueDTO> response = new ResponseEntity<BookIssueDTO>(result, HttpStatus.OK);
		return response;
		
	}
	
	/** 
    * This method views List Of Books to user from the database
    *
    * @param NOArgs
    * @return list of Books
    */
	@GetMapping("/issueBooks")
	public ResponseEntity<List<Books>> listOfBooks(){
		
		List<Books> allBooks=userService.listOfBooks();
		ResponseEntity<List<Books>> response = new ResponseEntity<List<Books>>(allBooks, HttpStatus.OK);
		return response;
	}

}
