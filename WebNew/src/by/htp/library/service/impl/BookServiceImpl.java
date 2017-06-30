package by.htp.library.service.impl;

import java.util.ArrayList;

import by.htp.library.dao.BookDAO;
import by.htp.library.dao.exception.DAOException;
import by.htp.library.dao.factory.DAOFactory;
import by.htp.library.domain.Book;
import by.htp.library.service.BookService;
import by.htp.service.exception.ServiceException;

public class BookServiceImpl implements BookService {
	private static final String MESSAGE1 = "Incorrect nazvanie";
	private static final String MESSAGE2 = "Incorrect avtor";
	private static final String MESSAGE3 = "Incorrect avtor";
	
	public ArrayList<Book> showBooks () throws ServiceException{
		
	//œ–Œ¬≈– ¿????
		DAOFactory daoObjectFactory=DAOFactory.getInstance();
		BookDAO bookDAO=daoObjectFactory.getBookDAO();
		ArrayList <Book> List =null;
		try {
		  List=bookDAO.showBook();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return List;	
	
		
	
		
	}

	@Override
	public Book addBook(String nazvanie, String avtor) throws ServiceException {
		if (nazvanie==null||nazvanie.isEmpty()){
			throw new ServiceException( MESSAGE1 );
		}
		if (avtor==null||avtor.isEmpty()){
			throw new ServiceException( MESSAGE2 );
		}
		
		
		DAOFactory daoObjectFactory=DAOFactory.getInstance();
		BookDAO bookDAO=daoObjectFactory.getBookDAO();
		try {
			return bookDAO.addBook(nazvanie,avtor);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}	
	}

	@Override
	public Book viewBook(String id) throws ServiceException {
		if (id==null||id.isEmpty()){
			throw new ServiceException(MESSAGE3 );	
		}
			DAOFactory daoObjectFactory=DAOFactory.getInstance();
			BookDAO bookDAO=daoObjectFactory.getBookDAO();
			try {
				return bookDAO.viewBook(id);
			} catch (DAOException e) {
				throw new ServiceException(e);
			}	
	}
		 
	
}

