package by.htp.library.service;

import by.htp.library.domain.User;
import by.htp.service.exception.ServiceException;

public interface UserService {
	User authorization(String login, String password) throws ServiceException;

	User registration(String name, String login, String password) throws ServiceException;

	User editProfileName(String name, String login)throws ServiceException;
}
