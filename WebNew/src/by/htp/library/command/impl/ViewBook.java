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
	private static final String VIEWJSP = "WEB-INF/jsp/viewBook.jsp";
	private static final String SHOWALLJSP = "WEB-INF/jsp/ShowAll.jsp";
	private static final String ERRORMESSAGE = "ErrorMessage";
	private static final String MESSAGE = "Message";
	private static final String MESSAGE1 = " Thre are no available books";
	private static final String MESSAGE2 = "Sorry, it is impossible to display the page";
	private static final String MESSAGE3 = "Книга успешно добавлена!";
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
			     page=VIEWJSP;
			     String mes=request.getParameter(MESSAGE);
			     if (mes==null||mes.isEmpty()){
			    	  }
			     else {
			    	 request.setAttribute(MESSAGE, MESSAGE3);
			     }
			}
			else{
				request.setAttribute(ERRORMESSAGE, MESSAGE1);
				page=SHOWALLJSP;
				
			}
		} catch (ServiceException e) {
			e.printStackTrace();
			request.setAttribute(ERRORMESSAGE, MESSAGE2);
			page=SHOWALLJSP;
		}
			
		RequestDispatcher dispatcher=request.getRequestDispatcher(page);
		
			dispatcher.forward(request, response);
			
	}
}
