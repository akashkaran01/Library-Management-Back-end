package com.cg.lib.util;

import com.cg.lib.dto.UserDTO;
import com.cg.lib.entity.User;
import com.cg.lib.dto.BookIssueDTO;
import com.cg.lib.dto.BooksDTO;
import com.cg.lib.entity.BookIssue;
import com.cg.lib.entity.Books;


public class Util {
	
	public static User dtoToUser(UserDTO userDto) {
		User newUser = new User();
		newUser.setDob(userDto.getDob());
		newUser.setEmail(userDto.getEmail());
		newUser.setFirstName(userDto.getFirstName());
		newUser.setLastName(userDto.getLastName());
		newUser.setMob_No(userDto.getMob_No());
		newUser.setPassword(userDto.getPassword());
		newUser.setUsername(userDto.getUsername());
		newUser.setApproved(userDto.getApproved());
		newUser.setServerResponse(userDto.getServerResponse());
		return newUser;
	}
	
	public static UserDTO userToDto(User user) {
		UserDTO userDto = new UserDTO();
		userDto.setDob(user.getDob());
		userDto.setEmail(user.getEmail());
		userDto.setFirstName(user.getFirstName());
		userDto.setLastName(user.getLastName());
		userDto.setMob_No(user.getMob_No());
		userDto.setPassword(user.getPassword());
		userDto.setUsername(user.getUsername());
		userDto.setApproved(user.getApproved());
		userDto.setServerResponse(user.getServerResponse());
		return userDto;
	}
	
	public static Books dtoToBooks(BooksDTO book) {
		Books newBook = new Books();
		newBook.setAuthorName(book.getAuthorName());
		newBook.setBookName(book.getBookName());
		newBook.setPublishedYear(book.getPublishedYear());
		newBook.setServerResponse(book.getServerResponse());
		return newBook;
	}
	
	public static BooksDTO booksToDto(Books book) {
		BooksDTO newBook = new BooksDTO();
		newBook.setAuthorName(book.getAuthorName());
		newBook.setBookName(book.getBookName());
		newBook.setPublishedYear(book.getPublishedYear());
		newBook.setServerResponse(book.getServerResponse());
		return newBook;
	}
	
	public static BookIssue dtoToBookIssue(BookIssueDTO bookIssueDTO) {
		BookIssue bookIssue= new BookIssue();
		bookIssue.setBookIssueId(bookIssueDTO.getBookIssueId());
		bookIssue.setUserName(bookIssueDTO.getUserName());
		bookIssue.setIssueBookDate(bookIssueDTO.getIssueBookDate());
		bookIssue.setReturnBookDate(bookIssueDTO.getReturnBookDate());
		bookIssue.setBook(bookIssueDTO.getBook());
		bookIssue.setServerResponse(bookIssueDTO.getServerResponse());
		return bookIssue;
	}
	
	public static BookIssueDTO BookIssueToDto(BookIssue bookIssue) {
		BookIssueDTO bookIssueDto= new BookIssueDTO();
		bookIssueDto.setBookIssueId(bookIssue.getBookIssueId());
		bookIssueDto.setUserName(bookIssue.getUserName());
		bookIssueDto.setIssueBookDate(bookIssue.getIssueBookDate());
		bookIssueDto.setReturnBookDate(bookIssue.getReturnBookDate());
		bookIssueDto.setBook(bookIssue.getBook());
		bookIssueDto.setServerResponse(bookIssue.getServerResponse());
		return bookIssueDto;
	}
	
	public static User duplicateUser(User user) {
		User newUser = new User();
		
		newUser.setApproved(user.getApproved());
		newUser.setDob(user.getDob());
		newUser.setEmail(user.getEmail());
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setMob_No(user.getMob_No());
		newUser.setPassword(user.getPassword());
		newUser.setUsername(user.getUsername());
		newUser.setServerResponse("");
		return newUser;
	}
	

}
