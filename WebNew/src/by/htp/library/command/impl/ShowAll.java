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

public class ShowAll implements Command {
	private static final String LIST = "List";
	private static final String MESSAGE1 = " Thre are no available books";
	private static final String MESSAGE2 = "Sorry, it is impossible to display the page";
    private static final String TAKEALLJSP= "WEB-INF/jsp/takeAllBook.jsp";
	private static final String ERRORMESSAGE= "errorMessage";
	private static final String MAINJSP = "WEB-INF/jsp/main.jsp";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServiceFactory factory=ServiceFactory.getInstance();
		BookService bookService=factory.getBookService();
	
		String page = null;
		
		try {
			ArrayList <Book> List  = bookService.showBooks();
			if (List.size()==0)	{
				
			   request.setAttribute(ERRORMESSAGE, MESSAGE1);
			   page=MAINJSP;
			}
			else{
				request.setAttribute(LIST, List);
			    page=TAKEALLJSP;
				
			}
		} catch (ServiceException e) {
			e.printStackTrace();
			request.setAttribute(ERRORMESSAGE, MESSAGE2);
			page=MAINJSP;
		}
		RequestDispatcher dispatcher=request.getRequestDispatcher(page);
		
			dispatcher.forward(request, response);

	}

}
