package com.cg.lib.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.lib.dao.IBookDAO;
import com.cg.lib.dao.IBookIssueDAO;
import com.cg.lib.dao.IUserDAO;
import com.cg.lib.entity.BookIssue;
import com.cg.lib.entity.Books;
import com.cg.lib.entity.User;
import com.cg.lib.exception.AlreadyExistsException;
import com.cg.lib.exception.BookNotFoundException;
import com.cg.lib.exception.InvalidArgumentException;
import com.cg.lib.exception.InvalidCredentialsException;
import com.cg.lib.exception.UserNotFoundException;

@Service
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private IUserDAO userDao;
	
	@Autowired
	private IBookDAO bookDao;
	
	@Autowired
	private IBookIssueDAO bookIssueDao;
	
	/*
	 * This method is for User Registration
	 * 
	 * @Param takes User as input 
	 * @return User object as result with added serverResponse
	 */
	@Override
	public User userRegistration(User user) {
		if(user != null) {
			if(!userDao.existsById(user.getUsername())) {
				userDao.save(user);
				user.setServerResponse("User registered successfully");
				return user;
			}else {
				throw new AlreadyExistsException("User already exists.");
			}
		}else {
			throw new InvalidArgumentException("Please fill register form again");
		}
		
		/*
		 * if(user == null) { throw new
		 * InvalidArgumentException("Please fill register form again"); }else {
		 * if(userDao.existsById(user.getUsername())) { throw new
		 * AlreadyExistsException("User already exists."); } userDao.save(user);
		 * user.setServerResponse("User registered successfully"); return user; }
		 */
	}
	
	
	/*
	 * This method is for user login,
	 * IF user credentials are right then it will return Login Sucessfully ,
	 * ELSE return Invalid Credentials
	 * 
	 * @Param username, password from the controller
	 * @return user object with added serverResponse to the controller
	 */
	@Override
	public User userValidation(String username, String password) {
		boolean userPresence = userDao.existsById(username);
		if(userPresence)
		{
			List<User> allUsers = userDao.findAll();
			for (User user : allUsers) 
			{ 
				if(user.getUsername().equals(username))
				{
					if(user.getPassword().equals(password)
					&& user.getApproved()) {
						user.setServerResponse("Login successful...");
						return user;
					}else {
						throw new InvalidCredentialsException("Invalid credentials. Enter "
								+ "correct credentials...");
					}
				}
			}
		}
		
		throw new UserNotFoundException("No such user exists...");
		
		/*if(!userPresence) 
		{
			throw new UserNotFoundException("No such user exists...");
		}else {
			List<User> allUsers = userDao.findAll();
			for (User user : allUsers) 
			{ 
				if(user.getUsername().equals(username))
				{
					if(user.getPassword().equals(password)
					&& user.getApproved()) {
						user.setServerResponse("Login successful...");
						return user;
					}else {
						break;
					}
				}
			}
		}
		throw new InvalidCredentialsException("Invalid credentials. Enter "
				+ "correct credentials...");
*/	
	}

	/*
	 * This method returns the lists of books present in the database
	 * @param NoArgs
	 * @return List of Books present in the database 
	 */
	@Override
	public List<Books> listOfBooks() {
		List<Books> allBooks=bookDao.findAll();
		if(allBooks == null) {
			throw new BookNotFoundException("No books present in Books database...");
		}
		return allBooks;
	}

	/*
	 * This method issues books to the user
	 * 
	 * @param BookIssue object from controller
	 * @return BookIssue object with added serverResponse to controller
	 */
	@Override
	public BookIssue issueBooks(BookIssue bookIssue) {
		if(bookIssue.getUserName() != null) {
			if (userDao.existsById(bookIssue.getUserName())) {
				bookIssueDao.save(bookIssue);
				bookIssue.setServerResponse("Book Issued Sucessfully");
				return bookIssue;
			}else {
				throw new UserNotFoundException("Enter correct username."
						+ "No such user exists...");
			}
		}else {
			throw new InvalidArgumentException("Getting null object. Something went wrong,"
					+ " Retry Again for Issue Book");
		}
		
		/*
		 * if(bookIssue.getUserName() == null) { throw new
		 * InvalidArgumentException("Getting null object. Something went wrong, Retry Again for Issue Book"
		 * ); }else { bookIssueDao.save(bookIssue);
		 * bookIssue.setServerResponse("Book Issued Sucessfully"); return bookIssue; }
		 */
	}
	

	
}
