package by.htp.library.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.htp.connection.pool.ConnectionPool;
import by.htp.connection.pool.ConnectionPoolException;
import by.htp.connection.pool.ConnectionPoolFactory;
import by.htp.library.dao.BookDAO;
import by.htp.library.dao.exception.DAOException;
import by.htp.library.domain.Book;


public class SQLBookDAO implements BookDAO {
	private static final String SelectBook = "SELECT * FROM book ";
	private static final int first = 1;
	private static final int second = 2;
	private static final int third = 3;

	@Override
	public Book showBook() throws DAOException{
		Connection con = null;
		ResultSet rs = null;

		Book book = null;
		try {
			ConnectionPoolFactory ObjectCPFactory = ConnectionPoolFactory.getInstance();
			ConnectionPool cp = ObjectCPFactory.getConnectionPool();

			con = cp.takeConnection();
			PreparedStatement ps = con.prepareStatement(SelectBook);
			
			rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(first);
				String name = rs.getString(second);
				String nazvanie = rs.getString(third);
				
				book = new Book(id, name, nazvanie);
			}
			cp.removeConnection();
		} catch (ConnectionPoolException e) {
			throw new DAOException(e);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		

		return book;
		
		
	}

}
