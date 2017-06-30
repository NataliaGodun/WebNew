package by.htp.library.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import by.htp.connection.pool.ConnectionPool;
import by.htp.connection.pool.ConnectionPoolException;
import by.htp.connection.pool.ConnectionPoolFactory;
import by.htp.library.dao.BookDAO;
import by.htp.library.dao.exception.DAOException;
import by.htp.library.domain.Book;



public class SQLBookDAO implements BookDAO {
	private static final String ShowBook = "SELECT * FROM BOOK ";
	private static final String addBook = "insert into book (name,nazvanie) values (?,?)";
	private static final String SelectBook = "SELECT * FROM BOOK where name=? and nazvanie=?";
	private static final int first = 1;
	private static final int second = 2;
	private static final int third = 3;

	@Override
	public ArrayList showBook() throws DAOException{
		Connection con = null;
		ResultSet rs = null;
		Book book=null;
		ArrayList <Book> List = new ArrayList<Book>();
		try {
			ConnectionPoolFactory ObjectCPFactory = ConnectionPoolFactory.getInstance();
			ConnectionPool cp = ObjectCPFactory.getConnectionPool();

			con = cp.takeConnection();
			PreparedStatement ps = con.prepareStatement(ShowBook );
			
			rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(first);
				String name = rs.getString(second);
				String nazvanie = rs.getString(third);
				
				book = new Book(id, name, nazvanie);
				List.add(book);
			}
			cp.removeConnection();
		} catch (ConnectionPoolException e) {
			throw new DAOException(e);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		
		return List;
		
		
	}

	@Override
	public Book addBook(String nazvanie, String avtor) throws DAOException {
		Connection con = null;
		ResultSet rs = null;
		Book book= null;
		try {

			ConnectionPoolFactory ObjectCPFactory = ConnectionPoolFactory.getInstance();
			ConnectionPool cp = ObjectCPFactory.getConnectionPool();
			con = cp.takeConnection();

			PreparedStatement ps = con.prepareStatement(addBook);

			ps.setString(first, avtor);
			ps.setString(second, nazvanie);
			
			ps.executeUpdate();

			ps = con.prepareStatement(SelectBook);
			ps.setString(first, avtor);
			ps.setString(second,nazvanie );
			rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(first);
				String NazvanieKnigi = rs.getString(second);
				String AvtorKnigi = rs.getString(third);
				
				book= new Book(id, NazvanieKnigi , AvtorKnigi);
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
