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
	private static final String ALLBOOKSSELECT = "SELECT * FROM BOOK ";
	private static final String ADDBOOK = "INSERT INTO BOOK (NAME,NAZVANIE) VALUES(?,?)";
	private static final String BOOKSELECT = "SELECT * FROM BOOK WHERE NAME=? AND NAZVANIE=?";
	private static final String IDSELECT = "SELECT * FROM BOOK WHERE ID=? ";
	private static final int FIRST= 1;
	private static final int SECOND = 2;
	private static final int THIRD = 3;

	@Override
	public ArrayList<Book> showBook() throws DAOException{
		Connection con = null;
		ResultSet rs = null;
		Book book=null;
		
		ArrayList <Book> List = new ArrayList<Book>();
		
		ConnectionPoolFactory ObjectCPFactory = ConnectionPoolFactory.getInstance();
		ConnectionPool cp = ObjectCPFactory.getConnectionPool();

		try {
			
			con = cp.takeConnection();
			PreparedStatement ps = con.prepareStatement(ALLBOOKSSELECT );
			rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(FIRST);
				String name = rs.getString(SECOND);
				String nazvanie = rs.getString(THIRD);
				
				book = new Book(id, name, nazvanie);
				List.add(book);
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException(e);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		finally{
			try {
				cp.removeConnection();
			} catch (ConnectionPoolException e) {
				// Log.ERROR
				e.printStackTrace();
			}
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

			PreparedStatement ps = con.prepareStatement(ADDBOOK);

			ps.setString(FIRST, avtor);
			ps.setString(SECOND, nazvanie);
			
			ps.executeUpdate();

			ps = con.prepareStatement(BOOKSELECT);
			ps.setString(FIRST, avtor);
			ps.setString(SECOND,nazvanie );
			rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(FIRST);
				String NazvanieKnigi = rs.getString(SECOND);
				String AvtorKnigi = rs.getString(THIRD);
				
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

	@Override
	public Book viewBook(String id) throws DAOException {
		
		Connection con = null;
		ResultSet rs = null;
		Book book= null;
		try {

			ConnectionPoolFactory ObjectCPFactory = ConnectionPoolFactory.getInstance();
			ConnectionPool cp = ObjectCPFactory.getConnectionPool();
			con = cp.takeConnection();

			PreparedStatement ps = con.prepareStatement(IDSELECT);

			ps.setString(FIRST, id);
			
			
			rs = ps.executeQuery();
			while (rs.next()) {
				int idBD = rs.getInt(FIRST);
				String NazvanieKnigi = rs.getString(SECOND);
				String AvtorKnigi = rs.getString(THIRD);
				
				book= new Book(idBD, NazvanieKnigi , AvtorKnigi);
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
