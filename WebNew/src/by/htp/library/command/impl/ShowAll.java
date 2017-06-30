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
	private static final String ErrorMessage = "ErrorMessage";
	private static final String infoMessage = " Thre are no available books";
	private static final String infoMessageError = "Sorry, it is impossible to display the page";
    private static final String ShowAlljsp = "WEB-INF/jsp/ShowAll.jsp";
	private static final String mainjsp = "WEB-INF/jsp/main.jsp";
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServiceFactory factory=ServiceFactory.getInstance();
		BookService bookService=factory.getBookService();
		//Book book = null;
		String page = null;
		
		try {
			ArrayList <Book> List  = bookService.showBooks();
			if (List.size()==0)	{
				
			     request.setAttribute(ErrorMessage, infoMessage);
					page=mainjsp;
			}
			else{
				request.setAttribute(LIST, List);
			     page=ShowAlljsp;
				
			}
		} catch (ServiceException e) {
			e.printStackTrace();
			request.setAttribute(ErrorMessage, infoMessageError);
			page=mainjsp;
		}
		RequestDispatcher dispatcher=request.getRequestDispatcher(page);
		
			dispatcher.forward(request, response);

	}

}
