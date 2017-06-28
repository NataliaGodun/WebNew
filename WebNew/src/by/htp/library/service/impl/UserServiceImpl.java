package by.htp.library.service.impl;



import by.htp.library.dao.UserDAO;
import by.htp.library.dao.exception.DAOException;
import by.htp.library.dao.factory.DAOFactory;
import by.htp.library.domain.User;
import by.htp.library.service.UserService;
import by.rdtc.service.exception.ServiceException;

public class UserServiceImpl implements UserService {

	@Override
	public User authorization(String login, String password) throws ServiceException{
		if (login==null||login.isEmpty()){
			throw new ServiceException("Incorrect login");
		}
		
		DAOFactory daoObjectFactory=DAOFactory.getInstance();
		UserDAO userDAO=daoObjectFactory.getUserDAO();
		try {
			return userDAO.authorization(login,password);
		} catch (DAOException e) {
			System.out.println(" ServiceException");
			throw new ServiceException(e);
		}	
	}

	@Override
	public User registration(String name, String surname, String login, String password) throws ServiceException {
		if (login==null||login.isEmpty()){
			throw new ServiceException("Incorrect login");
		}
		DAOFactory daoObjectFactory=DAOFactory.getInstance();
		UserDAO userDAO=daoObjectFactory.getUserDAO();
		return userDAO.registration(name,surname,login,password);
		
	}

	
}
