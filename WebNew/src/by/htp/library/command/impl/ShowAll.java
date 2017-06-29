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

public class ShowAll implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServiceFactory factory=ServiceFactory.getInstance();
		BookService bookService=factory.getBookService();
	Book book = null;
		String page = null;
		try {
			book = bookService.showBooks();
			if (book!=null)	{
				request.setAttribute("book", book);
			     page="WEB-INF/jsp/ShowAll.jsp";
			}
			else{
				request.setAttribute("errorMessage", "net");
				page="WEB-INF/jsp/main.jsp";
				
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		RequestDispatcher dispatcher=request.getRequestDispatcher(page);
		
			dispatcher.forward(request, response);

	}

}
