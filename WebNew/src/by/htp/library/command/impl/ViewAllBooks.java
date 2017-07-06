package by.htp.library.command.impl;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import by.htp.library.command.Command;
import by.htp.library.domain.Book;
import by.htp.library.service.BookService;
import by.htp.library.service.factory.ServiceFactory;
import by.htp.service.exception.ServiceException;

public class ViewAllBooks implements Command {
	private static final String LIST = "List";
	private static final String MESSAGE_NO_BOOKS = " There are no available books";
	private static final String MESSAGE_ABOUT_PROBLEM = "Sorry,technical problem";
    private static final String TAKE_ALL_JSP= "WEB-INF/jsp/takeAllBook.jsp";
	private static final String ERROR_MESSAGE= "errorMessage";
	private static final String MAIN_JSP = "WEB-INF/jsp/main.jsp";
	private static final String MESSAGE = "Message";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServiceFactory factory=ServiceFactory.getInstance();
		BookService bookService=factory.getBookService();
	
		String page = null;
		
		try {
			ArrayList <Book> List  = bookService.showBooks();
			if (List.size()==0)	{
				
			   request.setAttribute(ERROR_MESSAGE, MESSAGE_NO_BOOKS );
			   page=MAIN_JSP;
			}
			else{
				 String mes=request.getParameter(MESSAGE);
			     if (!(mes==null||mes.isEmpty())){
			    	 request.setAttribute(MESSAGE, mes);
			     }
				request.setAttribute(LIST, List);
			    page=TAKE_ALL_JSP;
				
			}
		} catch (ServiceException e) {
			e.printStackTrace();
			request.setAttribute(ERROR_MESSAGE, MESSAGE_ABOUT_PROBLEM);
			page=MAIN_JSP;
		}
		RequestDispatcher dispatcher=request.getRequestDispatcher(page);
		
			dispatcher.forward(request, response);

	}

}
