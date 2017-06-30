package by.htp.library.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.library.command.Command;
import by.htp.library.domain.Book;
import by.htp.library.domain.User;
import by.htp.library.service.BookService;
import by.htp.library.service.UserService;
import by.htp.library.service.factory.ServiceFactory;
import by.htp.service.exception.ServiceException;

public class ViewBook implements Command {
	private static final String ID = "id";
	private static final String BOOK = "book";
	private static final String VIEWJSP = "WEB-INF/jsp/viewBook.jsp";
	 private static final String SHOWALLJSP = "WEB-INF/jsp/ShowAll.jsp";
	 private static final String ERRORMESSAGE = "ErrorMessage";
		private static final String INFOMESSAGE1 = " Thre are no available books";
		private static final String INFOMESSAGE2 = "Sorry, it is impossible to display the page";
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
			}
			else{
				request.setAttribute(ERRORMESSAGE, INFOMESSAGE1);
				page=SHOWALLJSP;
				
			}
		} catch (ServiceException e) {
			e.printStackTrace();
			request.setAttribute(ERRORMESSAGE, INFOMESSAGE2);
			page=SHOWALLJSP;
		}
			
		RequestDispatcher dispatcher=request.getRequestDispatcher(page);
		
			dispatcher.forward(request, response);
			
	}
}
