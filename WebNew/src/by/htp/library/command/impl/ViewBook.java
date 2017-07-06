package by.htp.library.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import by.htp.library.command.Command;
import by.htp.library.domain.Book;
import by.htp.library.service.BookService;
import by.htp.library.service.factory.ServiceFactory;
import by.htp.service.exception.ServiceException;

public class ViewBook implements Command {
	private static final String ID = "id";
	private static final String BOOK = "book";
	private static final String VIEW_JSP = "WEB-INF/jsp/viewBook.jsp";
	private static final String MAIN_JSP = "WEB-INF/jsp/main.jsp";
	private static final String ERROR_MESSAGE = "ErrorMessage";
	private static final String MESSAGE = "Message";
	private static final String MESSAGE_NO_BOOKS= " There are no available books";
	private static final String MESSAGE_ABOUT_PROBLEM= "Sorry,technical problem";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id;
		
		
		id=request.getParameter(ID);
		

		ServiceFactory factory=ServiceFactory.getInstance();
		BookService bookService=factory.getBookService();
		
		Book book = null;
		String page = null;
		try {
			book = bookService.viewBook(id);
			if (book!=null)	{
				request.setAttribute(BOOK, book);
			     page=VIEW_JSP;
			     String mes=request.getParameter(MESSAGE);
			     if (!(mes==null||mes.isEmpty())){
			    	 request.setAttribute(MESSAGE, mes);
			     }else{}
			}
			else{
				request.setAttribute(ERROR_MESSAGE, MESSAGE_NO_BOOKS);
				page=MAIN_JSP;
				
			}
		} catch (ServiceException e) {
			request.setAttribute(ERROR_MESSAGE, MESSAGE_ABOUT_PROBLEM);
			page=MAIN_JSP;
		}
			
		RequestDispatcher dispatcher=request.getRequestDispatcher(page);
		
			dispatcher.forward(request, response);
			
	}
}
