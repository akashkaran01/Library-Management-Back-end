package com.cg.lib.service;

import java.util.List;

import com.cg.lib.entity.Admin;
import com.cg.lib.entity.BookIssue;
import com.cg.lib.entity.Books;
import com.cg.lib.entity.User;

public interface IAdminService {
	
	Admin adminLogin(String username, String password);
	
	List<User> viewAllUsers();
	
	User approval(User user);
		
	List<Books> viewAllBooks();
	
	Books addBook(Books newBook);
	
	Books editBook(Books newBook);
	
	Books deleteBook(String bookName);

	List<BookIssue> alertForUnreturnedBook();
}
