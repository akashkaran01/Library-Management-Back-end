package com.cg.lib.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.lib.dao.IAdminDAO;
import com.cg.lib.dao.IBookDAO;
import com.cg.lib.dao.IBookIssueDAO;
import com.cg.lib.dao.IUserDAO;
import com.cg.lib.entity.Admin;
import com.cg.lib.entity.BookIssue;
import com.cg.lib.entity.Books;
import com.cg.lib.entity.User;
import com.cg.lib.exception.AlreadyExistsException;
import com.cg.lib.exception.BookNotFoundException;
import com.cg.lib.exception.InvalidArgumentException;
import com.cg.lib.exception.InvalidCredentialsException;
import com.cg.lib.exception.UserNotFoundException;
import com.cg.lib.util.Util;

@Service
public class AdminServiceImpl implements IAdminService {
	
	@Autowired
	private IAdminDAO adminDAO;
	
	@Autowired
	private IBookDAO bookDAO;
	
	@Autowired
	private IUserDAO userDAO;
	
	@Autowired
	private IBookIssueDAO bookIssueDAO;

	
	/* This method is for admin to login
	 * IF user credentials are right then it will return Admin login successful ,
	 * ELSE return Invalid Credentials Exception
	 * 
	 * @Param Takes username and password as input
	 * @return Admin object to complete login
	 */
	@Override
	public Admin adminLogin(String username, String password) {
		
		Admin admin = new Admin();
		
		if(username.equals("admin") && password.equals("admin")) {
			admin.setUsername(username);
			admin.setPassword(password);
			admin.setServerResponse("Admin login successful...");
		}else {
			throw new InvalidCredentialsException("Invalid entries. "
					+ "Enter correct details...");
		}
		return admin;
	}
	
	/*
	 * This method is for Admin to view list of all unapproved users.
	 * 
	 * @Param NoArgs
	 * @return List of users
	 */
	@Override
	public List<User> viewAllUsers(){
		List<User> allUsers = userDAO.findAll();
		if(!allUsers.isEmpty()) {
			List<User> unapprovedUsers = new ArrayList<User>();
			for(User user:allUsers) {
				if(!user.getApproved()) {
					unapprovedUsers.add(user);
				}
			}
			return unapprovedUsers;
		}else {
			throw new UserNotFoundException("There are no registered users in database...");
		}
		
		
		/*
		 * if(allUsers.isEmpty()) { throw new
		 * UserNotFoundException("There are no registered users in database..."); }
		 * List<User> unapprovedUsers = new ArrayList<User>(); for(User user:allUsers) {
		 * if(!user.getApproved()) { unapprovedUsers.add(user); } } return
		 * unapprovedUsers;
		 */
	}
	
	
	/*
	 * This method is for Admin to approve or reject registered user.
	 * 
	 * @Param takes User as an input 
	 * @return User object with added serverResponse in result
	 */
	@Override
	public User approval(User user) {
		if(user != null) {
			if(user.getApproved()) {
				userDAO.save(user);
				user.setServerResponse("User : "+user.getUsername()+" is approved by Admin.");
				return user;
			}else {
				User newUser = Util.duplicateUser(user);
				userDAO.delete(user);
				newUser.setServerResponse("User : "+newUser.getUsername()+" is rejected by Admin.");
				return newUser;
			}
		}else {
			throw new InvalidArgumentException("Getting null object...");
		}
		
		
		
		/*
		 * if(user.getApproved()) { userDAO.save(user);
		 * user.setServerResponse("User : "+user.getUsername()+" is approved by Admin."
		 * ); return user; }else { User newUser = Util.duplicateUser(user);
		 * userDAO.delete(user);
		 * newUser.setServerResponse("User : "+newUser.getUsername()
		 * +" is rejected by Admin."); return newUser; }
		 */
	}

	/*
	 * This method is for Admin to view all books
	 * 
	 * @Param NoArgs
	 * @return List of Books as a result
	 */
	@Override
	public List<Books> viewAllBooks() {
		List<Books> allBooks = bookDAO.findAll();
		if(!allBooks.isEmpty()) {
			return allBooks;
		}else {
			throw new BookNotFoundException("There are no books available in database...");
		}
		
		/*
		 * if(allBooks.isEmpty()) { throw new
		 * BookNotFoundException("There are no books available in database..."); }
		 * return allBooks;
		 */
	}
	
	/*
	 * This method is for Admin to add book
	 * 
	 * @Param it will takes Book as an input 
	 * @return it will return String as a result
	 */
	@Override
	public Books addBook(Books newBook) {
		if(newBook != null) {
			if(!bookDAO.existsById(newBook.getBookName())) {
				bookDAO.save(newBook);
				newBook.setServerResponse("Book added successfully...");
				return newBook;
			}else {
				throw new AlreadyExistsException("Book with name : "+newBook.getBookName()+
						" already exists...");				
			}
		}else {
			throw new InvalidArgumentException("Getting null object..");
		}
		
		
		
		/*
		 * if(bookDAO.existsById(newBook.getBookName())) { throw new
		 * AlreadyExistsException("Book with name : "+newBook.getBookName()+
		 * " already exists..."); }else { bookDAO.save(newBook);
		 * newBook.setServerResponse("Book added successfully..."); return newBook; }
		 */
		
	}
	
	/*
	 * This method is for Admin to edit Book
	 * 
	 * @Param takes Book as an input
	 * @return Book object with added serverResponse as result
	 */
	
	@Override
	public Books editBook(Books newBook) {
		if(newBook != null) {
			Optional<Books> previousBook = bookDAO.findById(newBook.getBookName());
			if(previousBook.isPresent()) {
				bookDAO.save(newBook);
				newBook.setServerResponse("Book edited successfully...");
				return newBook;
			}else {
				throw new BookNotFoundException("No such book present...");
			}
		}else {
			throw new InvalidArgumentException("Getting null object");
		}
		
		
		/*
		 * Optional<Books> previousBook = bookDAO.findById(newBook.getBookName());
		 * if(!previousBook.isPresent()) { throw new
		 * BookNotFoundException("No such book present..."); }else {
		 * bookDAO.save(newBook);
		 * newBook.setServerResponse("Book edited successfully..."); return newBook; }
		 */
	}
	
	/*
	 * This method is for Admin to delete Book
	 * 
	 * @Param it will takes Book as an input
	 * @return it will return String as a result
	 */

	@Override
	public Books deleteBook(String bookName) {
		Books deletedBook = new Books();
		if(!bookDAO.existsById(bookName)) {
			throw new BookNotFoundException("No such book present...");
		}else {
			List<Books> allBooks = bookDAO.findAll();
			for(Books book : allBooks) {
				if(book.getBookName().equals(bookName)) 
				{
					deletedBook.setAuthorName(book.getAuthorName());
					deletedBook.setBookName(bookName);
					deletedBook.setPublishedYear(book.getPublishedYear());
					
					bookDAO.deleteById(bookName);
					deletedBook.setServerResponse("Book deleted successfully...");
					break;
				}
			}			
		}
		return deletedBook;
	}	
	
	
	@Override
	public List<BookIssue> alertForUnreturnedBook()	{
		List<BookIssue> unreturnedBooks = new ArrayList<BookIssue>();
		if(!bookIssueDAO.findAll().isEmpty()) {
			unreturnedBooks = bookIssueDAO.findAll().
			stream().filter(bookIssueObj -> bookIssueObj.getReturnBookDate() == null)
			.collect(Collectors.toList());		
		}
		return unreturnedBooks;
	}

}
