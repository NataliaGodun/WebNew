package by.htp.library.service.impl;

import by.htp.library.dao.BookDAO;
import by.htp.library.dao.UserDAO;
import by.htp.library.dao.exception.DAOException;
import by.htp.library.dao.factory.DAOFactory;
import by.htp.library.domain.Book;
import by.htp.library.service.BookService;
import by.htp.service.exception.ServiceException;

public class BookServiceImpl implements BookService {
	private static final String infoMessage1 = "Incorrect nazvanie";
	private static final String infoMessage2 = "Incorrect avtor";
	
	public Book showBooks () throws ServiceException{
		
	//œ–Œ¬≈– ¿????
		DAOFactory daoObjectFactory=DAOFactory.getInstance();
		BookDAO bookDAO=daoObjectFactory.getBookDAO();
		try {
			return bookDAO.showBook();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}	
	
		
	
		
	}

	@Override
	public Book addBook(String nazvanie, String avtor) throws ServiceException {
		if (nazvanie==null||nazvanie.isEmpty()){
			throw new ServiceException( infoMessage1 );
		}
		if (avtor==null||avtor.isEmpty()){
			throw new ServiceException( infoMessage2 );
		}
		
		DAOFactory daoObjectFactory=DAOFactory.getInstance();
		BookDAO bookDAO=daoObjectFactory.getBookDAO();
		try {
			return bookDAO.addBook(nazvanie,avtor);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}	
	}	
}
