package by.htp.library.service.factory;

import by.htp.library.service.BookService;
import by.htp.library.service.UserService;
import by.htp.library.service.impl.BookServiceImpl;
import by.htp.library.service.impl.UserServiceImpl;

public class ServiceFactory {
	private final static ServiceFactory instance = new ServiceFactory();
	
	private final  UserService userService =  new UserServiceImpl();
	private final BookService bookService =   new BookServiceImpl();
	
	public static ServiceFactory getInstance(){
		return instance;
	}
	public UserService getUserService(){
		return userService;
	}
	public BookService getBookService(){
		return bookService;
	}
	}
	
