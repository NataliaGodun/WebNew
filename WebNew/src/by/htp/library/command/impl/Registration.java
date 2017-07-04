package by.htp.library.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.library.command.Command;
import by.htp.library.domain.User;

import by.htp.library.service.UserService;
import by.htp.library.service.factory.ServiceFactory;
import by.htp.service.exception.ServiceException;

public class Registration implements Command {
	private static final String LOGIN= "login";
	private static final String PASSWORD = "password";
	private static final String USER = "user";
	private static final String ERROR_MESSAGE= "errorMessage";
	private static final String MESSAGE_LOGIN_EXISTS = "The user with such login already exists";
	private static final String MESSAGE_ABOUT_PROBLEM = "Sorry,technical problem";
	private static final String MAIN_JSP = "WEB-INF/jsp/main.jsp";
	private static final String INDEX_JSP = "index.jsp";
	private static final String NAME = "name";
	
	
	
	
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				String name;
				String login;
				String password;
				
				name=request.getParameter(NAME);
				login=request.getParameter(LOGIN);
				password=request.getParameter(PASSWORD);
				
				ServiceFactory factory=ServiceFactory.getInstance();
				UserService userService=factory.getUserService();
				
				String page;
				User user = null;
				try {
					user = userService.registration(name, login, password);
					if (user!=null){
						request.setAttribute(USER, user);
						page=MAIN_JSP;
					}else{
						request.setAttribute(ERROR_MESSAGE, MESSAGE_LOGIN_EXISTS);
						page=INDEX_JSP;
						
					}
				} catch (ServiceException e) {
					e.printStackTrace();
					request.setAttribute( ERROR_MESSAGE, MESSAGE_ABOUT_PROBLEM);
					page=INDEX_JSP;
				}
						
				RequestDispatcher dispatcher=request.getRequestDispatcher(page);
				
					dispatcher.forward(request, response);
					
			}

		}


