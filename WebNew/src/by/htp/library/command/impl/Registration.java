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
	private static final String Name = "name";
	private static final String Login = "login";
	private static final String Password = "password";
	private static final String User = "user";
	private static final String ErrorMessage = "errorMessage";
	private static final String infoMessage = "The user with such login already exists";
	private static final String mainjsp = "WEB-INF/jsp/main.jsp";
	private static final String indexjsp = "index.jsp";
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				String name;
				String login;
				String password;
				
				name=request.getParameter(Name);
				login=request.getParameter(Login);
				password=request.getParameter(Password);
				
				ServiceFactory factory=ServiceFactory.getInstance();
				UserService userService=factory.getUserService();
				
				User user = null;
				try {
					user = userService.registration(name, login, password);
				} catch (ServiceException e) {
					e.printStackTrace();
				}
				
			
				
				
				String page;
				if (user!=null){
					request.setAttribute(User, user);
					page= mainjsp;
				}else{
					request.setAttribute(ErrorMessage, infoMessage);
					page=indexjsp;
					
				}
				
				RequestDispatcher dispatcher=request.getRequestDispatcher(page);
				
					dispatcher.forward(request, response);
					
			}

		}


