package by.htp.library.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import by.htp.connection.pool.ConnectionPoolFactory;
import by.htp.connection.pool.ConnectionPool;
import by.htp.connection.pool.ConnectionPoolException;
import by.htp.library.dao.UserDAO;
import by.htp.library.dao.exception.DAOException;
import by.htp.library.domain.User;

public class SQLUserDAO implements UserDAO {
	private static final String USER_SELECT_LOGIN_PASSWORD  = "SELECT * FROM USERS WHERE LOGIN=? AND PASSWORD=? AND STATUS='EXIST'";
	private static final String USER_SELECT_LOGIN = "SELECT * FROM USERS WHERE LOGIN=? AND STATUS='EXIST'";
	private static final String USER_ADD = "INSERT INTO USERS (NAME,LOGIN,PASSWORD,ROLE,STATUS) VALUES (?,?,?,?,?)";
	private static final String GUEST ="guest";
	private static final String EXIST ="exist";
	private static final int FIRST = 1;
	private static final int SECOND= 2;
	private static final int THIRD = 3;
	private static final int FOURTH = 4;
	private static final int FIFTH = 5;

	@Override
	public User authorization(String login, String password) throws DAOException {
		Connection con = null;
		ResultSet rs = null;
		User user = null;
		
		ConnectionPoolFactory ObjectCPFactory = ConnectionPoolFactory.getInstance();
		ConnectionPool cp = ObjectCPFactory.getConnectionPool();
		try {
			con = cp.takeConnection();
			PreparedStatement ps = con.prepareStatement(USER_SELECT_LOGIN_PASSWORD);
			ps.setString(FIRST, login);
			ps.setString(SECOND, password);
			rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(FIRST);
				String name = rs.getString(SECOND);
				String loginBD = rs.getString(THIRD);
				String passwordBD = rs.getString(FOURTH);
				String role = rs.getString(FIFTH);
				user = new User(id, name, loginBD, passwordBD, role);
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
		return user;
	}

	@Override
	public User registration(String name, String login, String password) throws DAOException {
		Connection con = null;
		ResultSet rs = null;
		User user = null;
		
		ConnectionPoolFactory ObjectCPFactory = ConnectionPoolFactory.getInstance();
		ConnectionPool cp = ObjectCPFactory.getConnectionPool();
		try {
			con = cp.takeConnection();
			
			PreparedStatement ps = con.prepareStatement(USER_SELECT_LOGIN );
			ps.setString(FIRST, login);
			rs = ps.executeQuery();
			
			int i=0;
			while (rs.next()) {
				 i =rs.getInt("id");
			}
			if (i==0){
				ps = con.prepareStatement(USER_ADD);

				ps.setString(FIRST, name);
				ps.setString(SECOND, login);
				ps.setString(THIRD, password);
				ps.setString(FOURTH,GUEST );
				ps.setString(FIFTH,EXIST );
				ps.executeUpdate();

				ps = con.prepareStatement(USER_SELECT_LOGIN_PASSWORD);
				ps.setString(FIRST, login);
				ps.setString(SECOND, password);
				rs = ps.executeQuery();
				while (rs.next()) {
					int id = rs.getInt(FIRST);
					String nameBD = rs.getString(SECOND);
					String loginBD = rs.getString(THIRD);
					String passwordBD = rs.getString(FOURTH);
					String role = rs.getString(FIFTH);
					user = new User(id, nameBD, loginBD, passwordBD, role);
				  }
			   }
			}catch (ConnectionPoolException e) {
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
		return user;
	}

}
