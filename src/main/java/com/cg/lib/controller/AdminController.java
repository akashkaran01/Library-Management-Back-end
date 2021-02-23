package com.cg.lib.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.lib.dto.BooksDTO;
import com.cg.lib.dto.UserDTO;
import com.cg.lib.entity.Admin;
import com.cg.lib.entity.BookIssue;
import com.cg.lib.entity.Books;
import com.cg.lib.entity.User;
import com.cg.lib.service.AdminServiceImpl;
import com.cg.lib.util.Util;

@RestController
@RequestMapping("/admin")
@CrossOrigin("http://localhost:4200")
public class AdminController {
	
	@Autowired
	private AdminServiceImpl adminService;
	
	
	/**
     * This method is for Admin Login
     * 
     *
     * @param username and password
     * @return response to server(Admin)
     */
	@GetMapping("/login/{username}/{password}")
	public ResponseEntity<Admin> adminLogin(@PathVariable("username") String username,@PathVariable("password") String password)
	{
		Admin result = adminService.adminLogin(username, password);
		ResponseEntity<Admin> response = new ResponseEntity<Admin>(result, HttpStatus.OK);
		return response;
	}
	
	/**
     * This method is for admin to view the list of all Users from the database
     * 
     *
     * @param @NOArgs
     * @return List of Users
     */
	@GetMapping("/userApproval")
	public ResponseEntity<List<User>> viewAllUsers(){
		List<User> result = adminService.viewAllUsers();
		ResponseEntity<List<User>> response = new ResponseEntity<>(result, HttpStatus.OK);
		return response;
	}
	
	/**
     * This method is for admin for aprroval of Users to the database
     * 
     *
     * @param UserDTO 
     * @return response to server(UserDTO)
     */
	@PutMapping("/userApproval")
	public ResponseEntity<UserDTO> approval(@RequestBody UserDTO userDto){
		User userObj = Util.dtoToUser(userDto);
		User user = adminService.approval(userObj);
		UserDTO result = Util.userToDto(user);
		ResponseEntity<UserDTO> response = new ResponseEntity<UserDTO>(result, HttpStatus.OK);
		return response;
	}

	/**
     * This method is for admin to view all books from the database
     * 
     *
     * @param @NOArgs
     * @return List of Books
     */
	@GetMapping("/viewAllBooks") 
	public ResponseEntity<List<Books>> viewAllBooks(){
		List<Books> result = adminService.viewAllBooks();
		ResponseEntity<List<Books>> response = new ResponseEntity<>(result, HttpStatus.OK);
		return response;
	}
	
	/**
     * This method is for admin to add book to the database
     * 
     *
     * @param BooksDTO 
     * @return response to server(BooksDTO)
     */
	@PostMapping("/addBook")
	public ResponseEntity<BooksDTO> addBook(@RequestBody BooksDTO newBook)
	{
		Books book = Util.dtoToBooks(newBook);
		Books addedBook = adminService.addBook(book);
		BooksDTO result = Util.booksToDto(addedBook);
		ResponseEntity<BooksDTO> response = new ResponseEntity<BooksDTO>(result, HttpStatus.OK);
		return response;
	}
	
	/**
     * This method is for admin to edit book to the database
     * 
     *
     * @param BooksDTO 
     * @return response to server(BooksDTO)
     */
	@PutMapping("/editBook")
	public ResponseEntity<BooksDTO> editBook(@RequestBody BooksDTO newBook)
	{
		Books book = Util.dtoToBooks(newBook);
		Books editedBook = adminService.editBook(book);
		BooksDTO result = Util.booksToDto(editedBook);
		ResponseEntity<BooksDTO> response = new ResponseEntity<BooksDTO>(result, HttpStatus.OK);
		return response;
	}
	
	/**
     * This method is for admin to delete book to the database
     * 
     *
     * @param BooksDTO 
     * @return response to server(BooksDTO)
     */
	@DeleteMapping("/deleteBook/{bookName}")
	public ResponseEntity<BooksDTO> deleteBook(@PathVariable String bookName)
	{
		Books deletedBook = adminService.deleteBook(bookName);
		BooksDTO result = Util.booksToDto(deletedBook);
		ResponseEntity<BooksDTO> response = new ResponseEntity<BooksDTO>(result, HttpStatus.OK);
		return response;
	}
	
	
	@GetMapping("/alertForUnreturnedBooks")
	public ResponseEntity<List<BookIssue>> alertForUnreturnedBook() {
		List<BookIssue> result = adminService.alertForUnreturnedBook();
		ResponseEntity<List<BookIssue>> response = new ResponseEntity<List<BookIssue>>
					(result, HttpStatus.OK);
		return response;
	}
	
}
