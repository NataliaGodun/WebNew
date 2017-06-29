package by.htp.library.dao;

import by.htp.library.dao.exception.DAOException;
import by.htp.library.domain.Book;

public interface BookDAO {
Book showBook () throws DAOException;
}
