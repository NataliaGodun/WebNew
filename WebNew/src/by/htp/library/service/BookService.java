package by.htp.library.service;

import by.htp.library.domain.Book;
import by.htp.service.exception.ServiceException;

public interface BookService {
Book showBooks () throws ServiceException;
}
