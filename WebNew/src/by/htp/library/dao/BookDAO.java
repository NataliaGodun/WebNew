package by.htp.library.dao;

import java.util.ArrayList;

import by.htp.library.dao.exception.DAOException;
import by.htp.library.domain.Book;

public interface BookDAO {
ArrayList<Book> showBook () throws DAOException;

Book addBook(String nazvanie, String avtor) throws DAOException;

Book viewBook(String id) throws DAOException;

Book deleteBook(String nazvanie, String avtor)throws DAOException;
}
