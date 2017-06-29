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
	private static final String NazvanieKnigi = "nazvanie";
	private static final String Avtor= "avtor";
	private static final String BOOK= "book";
	private static final String ErrorMessage = "errorMessage";
	private static final String infoMessage = "The book is not add!";
	private static final String infoMessageError = "Sorry,technical problem";
	private static final String viewBookjsp = "WEB-INF/jsp/viewBook.jsp";
	private static final String mainjsp = "WEB-INF/jsp/main.jsp";
	private static final String errorjsp = "error.jsp";
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nazvanie;
		String avtor;
		
		nazvanie=request.getParameter(NazvanieKnigi);
		avtor=request.getParameter(Avtor);
		
		ServiceFactory factory=ServiceFactory.getInstance();
		BookService bookService=factory.getBookService();
		
		Book book = null;
		String page = null;
		try {
			book = bookService.addBook(nazvanie, avtor);
			if (book!=null)	{
				request.setAttribute(BOOK, book);
			     page=viewBookjsp;
			}
			else{
				request.setAttribute(ErrorMessage, infoMessage);
				page=mainjsp;
				
			}
		} catch (ServiceException e) {
			e.printStackTrace();
			request.setAttribute(ErrorMessage, infoMessageError);
			page=errorjsp;
		}
			
		RequestDispatcher dispatcher=request.getRequestDispatcher(page);
		
			dispatcher.forward(request, response);
			
	}

}