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

public class DeleteBook implements Command {
	private static final String NAME_BOOK = "nazvanie";
	private static final String NAME_WRITER= "avtor";
	private static final String BOOK= "book";
	private static final String ERROR_MESSAGE = "errorMessage";
	private static final String MESSAGE_FAIL_DELETE = "The book is not delete!";
	private static final String MESSAGE_ABOUT_PROBLEM = "Sorry,technical problem";
	private static final String MESSAGE_SUCCESSFUL_ADDITION = "&Message=Book successful addition in library!";
	private static final String MESSAGE_SUCCESSFUL_DELETE = "&Message=Book successful delete!";
	//private static final String URL_VIEW_BOOK=" http://localhost:8080/WebNew/Controller?command=viewBook&id=";
	private static final String URL_VIEW_ALL_BOOK=" http://localhost:8080/WebNew/Controller?command=showall";
	private static final String MAIN_JSP = "WEB-INF/jsp/main.jsp";
	private static final String ERROR_JSP = "error.jsp";
	private static final String VIEW_JSP = "WEB-INF/jsp/viewBook.jsp";
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nazvanie;
		String avtor;
		
		nazvanie=request.getParameter(NAME_BOOK);
		avtor=request.getParameter(NAME_WRITER);
		
		ServiceFactory factory=ServiceFactory.getInstance();
		BookService bookService=factory.getBookService();
		
		Book book = null;
		String page = null;
		try {
			book = bookService.deleteBook(nazvanie, avtor);
			if (book==null)	{
				
				String url= URL_VIEW_ALL_BOOK;
				String url2=url+MESSAGE_SUCCESSFUL_DELETE;
			     response.sendRedirect(url2);
			}
			else{
				request.setAttribute(ERROR_MESSAGE, MESSAGE_FAIL_DELETE);
				page=VIEW_JSP;
				RequestDispatcher dispatcher=request.getRequestDispatcher(page);
				dispatcher.forward(request, response);
			}
		} catch (ServiceException e) {
			e.printStackTrace();
			request.setAttribute(ERROR_MESSAGE, MESSAGE_ABOUT_PROBLEM);
			page=ERROR_JSP;
			RequestDispatcher dispatcher=request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
		}
			
		
			
	}

	

}
