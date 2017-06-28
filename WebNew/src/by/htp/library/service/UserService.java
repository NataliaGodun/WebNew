package by.htp.library.service;


import by.htp.library.domain.User;
import by.rdtc.service.exception.ServiceException;

public interface UserService {
User authorization(String login, String password) throws ServiceException;
User registration(String name, String surname,String login, String password) throws ServiceException;
}
