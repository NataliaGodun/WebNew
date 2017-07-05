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

public class AddNewBook implements Command {
	private static final String NAME_BOOK = "nazvanie";
	private static final String NAME_WRITER= "avtor";
	private static final String ERROR_MESSAGE = "errorMessage";
	private static final String MESSAGE_FAILING_ADDITION  = "The book is not add!";
	private static final String MESSAGE_ABOUT_PROBLEM = "Sorry,technical problem";
	private static final String MESSAGE_SUCCESSFUL_ADDITION = "&Message=Book successful addition in library!";
	private static final String URL_VIEW_BOOK=" http://localhost:8080/WebNew/Controller?command=viewBook&id=";
	private static final String MAIN_JSP = "WEB-INF/jsp/main.jsp";
	private static final String ERROR_JSP = "error.jsp";
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String	nameBook = request.getParameter(NAME_BOOK);
		String writer = request.getParameter(NAME_WRITER);
		
		ServiceFactory factory=ServiceFactory.getInstance();
		BookService bookService=factory.getBookService();
		
		Book book = null;
		String page = null;
		try {
			book = bookService.addBook(nameBook, writer);
			if (book!=null)	{
				int i=book.getId();
				String url=URL_VIEW_BOOK+i;
				String url2=url+MESSAGE_SUCCESSFUL_ADDITION;
			     response.sendRedirect(url2);
			}
			else{
				request.setAttribute(ERROR_MESSAGE, MESSAGE_FAILING_ADDITION);
				page=MAIN_JSP;
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