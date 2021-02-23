package com.cg.lib.service;

import java.util.List;

import com.cg.lib.entity.BookIssue;
import com.cg.lib.entity.Books;
import com.cg.lib.entity.User;

public interface IUserService {
	
	User userValidation(String username, String password);
	
	User userRegistration(User user);
	
	List<Books> listOfBooks();
	
	BookIssue issueBooks(BookIssue bookIssue);
	
}
