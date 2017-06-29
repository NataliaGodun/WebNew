package by.htp.library.service.impl;

import by.htp.library.dao.BookDAO;

import by.htp.library.dao.exception.DAOException;
import by.htp.library.dao.factory.DAOFactory;
import by.htp.library.domain.Book;
import by.htp.library.service.BookService;
import by.htp.service.exception.ServiceException;

public class BookServiceImpl implements BookService {
	public Book showBooks () throws ServiceException{
		
	//œ–Œ¬≈– ¿!!!!!!!!!!!
		DAOFactory daoObjectFactory=DAOFactory.getInstance();
		BookDAO bookDAO=daoObjectFactory.getBookDAO();
		try {
			return bookDAO.showBook();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}	
	
		
	
		
	}
}
