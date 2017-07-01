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
	private static final String NAMEBOOK = "nazvanie";
	private static final String NAMEWRITER= "avtor";
	private static final String BOOK= "book";
	private static final String MESSAGE = "Message";
	private static final String ERRORMESSAGE = "errorMessage";
	private static final String MESSAGE1 = " нига успешно добавлена в библиотеку.";
	private static final String MESSAGE2 = "The book is not add!";
	private static final String MESSAGE3 = "Sorry,technical problem";
	private static final String WITHMESSAGE = "&Message=new";
	private static final String URLVIEWBOOK=" http://localhost:8080/WebNew/Controller?command=viewBook&id=";
	private static final String MAINJSP = "WEB-INF/jsp/main.jsp";
	private static final String ERRORJSP = "error.jsp";
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nazvanie;
		String avtor;
		
		nazvanie=request.getParameter(NAMEBOOK);
		avtor=request.getParameter(NAMEWRITER);
		
		ServiceFactory factory=ServiceFactory.getInstance();
		BookService bookService=factory.getBookService();
		
		Book book = null;
		String page = null;
		try {
			book = bookService.addBook(nazvanie, avtor);
			if (book!=null)	{
				request.setAttribute(BOOK, book);
				
				int i=book.getId();
				String url=URLVIEWBOOK+i;
				String url2=url+WITHMESSAGE;
				request.setAttribute(MESSAGE,  MESSAGE1);
			     response.sendRedirect(url2);
			}
			else{
				request.setAttribute(ERRORMESSAGE, MESSAGE2);
				page=MAINJSP;
				RequestDispatcher dispatcher=request.getRequestDispatcher(page);
				dispatcher.forward(request, response);
			}
		} catch (ServiceException e) {
			e.printStackTrace();
			request.setAttribute(ERRORMESSAGE,  MESSAGE3);
			page=ERRORJSP;
			RequestDispatcher dispatcher=request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
		}
			
		
			
	}

}