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

public class Authorization implements Command {
	private static final String LOGIN= "login";
	private static final String PASSWORD = "password";
	private static final String USER = "user";
	private static final String ERRORMESSAGE= "errorMessage";
	private static final String MESSAGE1 = "wrong login or password";
	private static final String MESSAGE2 = "Sorry,technical problem";
	private static final String MAINJSP = "WEB-INF/jsp/main.jsp";
	private static final String INDEXJSP = "index.jsp";
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login;
		String password;
		
		login=request.getParameter(LOGIN);
		password=request.getParameter(PASSWORD);
		
		ServiceFactory factory=ServiceFactory.getInstance();
		UserService userService=factory.getUserService();
		
		User user = null;
		String page = null;
		try {
			user = userService.authorization(login, password);
			if (user!=null)	{
				request.setAttribute(USER , user);
			     page=MAINJSP ;
			}
			else{
				request.setAttribute( ERRORMESSAGE, MESSAGE1);
				page=INDEXJSP;
				
			}
		} catch (ServiceException e) {
			e.printStackTrace();
			request.setAttribute( ERRORMESSAGE, MESSAGE2);
			page=INDEXJSP;
		}
			
		RequestDispatcher dispatcher=request.getRequestDispatcher(page);
		
			dispatcher.forward(request, response);
			
	}

}
