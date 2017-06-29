package by.htp.library.dao.factory;

import by.htp.library.dao.BookDAO;
import by.htp.library.dao.UserDAO;
import by.htp.library.dao.impl.SQLBookDAO;
import by.htp.library.dao.impl.SQLUserDAO;


public final class DAOFactory {
private  static final DAOFactory instance = new DAOFactory();
	
	private final UserDAO sqlUserImpl =  new SQLUserDAO();
	private final BookDAO sqlBookImpl =  new SQLBookDAO();
	
	private DAOFactory(){}
	
	public static DAOFactory getInstance(){
		return instance;
	}	
	
	public UserDAO getUserDAO(){
		return sqlUserImpl;
	}
	public BookDAO getBookDAO(){
		return sqlBookImpl;
	}
	
	}

