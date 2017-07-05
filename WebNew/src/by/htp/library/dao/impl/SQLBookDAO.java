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
	private static final String SELECT_ALL_BOOK = "SELECT * FROM BOOK WHERE STATUS='EXIST'";
	private static final String ADD_BOOK = "INSERT INTO BOOK (NAME,NAZVANIE,STATUS) VALUES(?,?,'EXIST')";
	private static final String BOOK_SELECT = "SELECT * FROM BOOK WHERE NAME=? AND NAZVANIE=? AND STATUS='EXIST'";
	private static final String SELECT_BOOK_ID = "SELECT * FROM BOOK WHERE ID=? AND STATUS='EXIST' ";
	private static final String DELETE_BOOK_NAME_WRITER = "UPDATE BOOK SET STATUS='DELETE' WHERE NAME=? AND NAZVANIE=?";
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
			PreparedStatement ps = con.prepareStatement(SELECT_ALL_BOOK);
			rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(FIRST);
				String writer = rs.getString(SECOND);
				String NameBook = rs.getString(THIRD);
				
				book = new Book(id,writer, NameBook);
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
	public Book addBook(String  nameBook, String writer) throws DAOException {
		Connection con = null;
		ResultSet rs = null;
		Book book= null;
		ConnectionPoolFactory ObjectCPFactory = ConnectionPoolFactory.getInstance();
		ConnectionPool cp = ObjectCPFactory.getConnectionPool();

		try {
			con = cp.takeConnection();

			PreparedStatement ps = con.prepareStatement(ADD_BOOK);

			ps.setString(FIRST, writer);
			ps.setString(SECOND,  nameBook);
			ps.executeUpdate();

			ps = con.prepareStatement(BOOK_SELECT);
			ps.setString(FIRST, writer);
			ps.setString(SECOND, nameBook );
			rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(FIRST);
				String NameBook = rs.getString(SECOND);
				String Writer = rs.getString(THIRD);
				
				book= new Book(id, NameBook , Writer);
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
		return book;
	}

	@Override
	public Book viewBook(String id) throws DAOException {
		
		Connection con = null;
		ResultSet rs = null;
		Book book= null;

		ConnectionPoolFactory ObjectCPFactory = ConnectionPoolFactory.getInstance();
		ConnectionPool cp = ObjectCPFactory.getConnectionPool();
		try {

			con = cp.takeConnection();

			PreparedStatement ps = con.prepareStatement(SELECT_BOOK_ID);

			ps.setString(FIRST, id);
			
			
			rs = ps.executeQuery();
			while (rs.next()) {
				int idBD = rs.getInt(FIRST);
				String NameBook= rs.getString(SECOND);
				String Writer = rs.getString(THIRD);
				
				book= new Book(idBD, NameBook , Writer);
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
		return book;
	}

	@Override
	public Book deleteBook(String  nameBook, String writer) throws DAOException {
		Connection con = null;
		ResultSet rs = null;
		Book book= null;
		
		ConnectionPoolFactory ObjectCPFactory = ConnectionPoolFactory.getInstance();
		ConnectionPool cp = ObjectCPFactory.getConnectionPool();
		try {
			con = cp.takeConnection();
			
			PreparedStatement ps = con.prepareStatement(DELETE_BOOK_NAME_WRITER );

			ps.setString(FIRST, writer);
			ps.setString(SECOND, nameBook);
			
			ps.executeUpdate();

			ps = con.prepareStatement(BOOK_SELECT);
			ps.setString(FIRST, writer);
			ps.setString(SECOND, nameBook);
			rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(FIRST);
				String  NameBook = rs.getString(SECOND);
				String Writer = rs.getString(THIRD);
				
				book= new Book(id,  NameBook , Writer);
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
		}return book;
	}
}
