package by.htp.library.service.factory;

import by.htp.library.service.UserService;
import by.htp.library.service.impl.UserServiceImpl;

public class ServiceFactory {
	private final static ServiceFactory instance = new ServiceFactory();
	
	private UserService userService =  new UserServiceImpl();
	
	public UserService getUserService(){
		return userService;
	}
	public static ServiceFactory getInstance(){
		return instance;
	}
	}
	
