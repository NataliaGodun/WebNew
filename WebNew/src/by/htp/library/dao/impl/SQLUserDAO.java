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
	private static final String SelectUser = "SELECT * FROM PEOPLE where login=? and password=?";
	private static final String addUser = "insert into people (name,login,password,role) values (?,?,?,?)";
	private static final String Guest ="guest";
	private static final int first = 1;
	private static final int second = 2;
	private static final int third = 3;
	private static final int fourth = 4;
	private static final int fifth = 5;

	@Override
	public User authorization(String login, String password) throws DAOException {
		Connection con = null;
		ResultSet rs = null;

		User user = null;

		try {
			ConnectionPoolFactory ObjectCPFactory = ConnectionPoolFactory.getInstance();
			ConnectionPool cp = ObjectCPFactory.getConnectionPool();

			con = cp.takeConnection();
			PreparedStatement ps = con.prepareStatement(SelectUser);
			ps.setString(first, login);
			ps.setString(second, password);
			rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(first);
				String name = rs.getString(second);
				String loginBD = rs.getString(third);
				String passwordBD = rs.getString(fourth);
				String role = rs.getString(fifth);
				user = new User(id, name, loginBD, passwordBD, role);
			}
			cp.removeConnection();

		} catch (ConnectionPoolException e) {
			throw new DAOException(e);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return user;
	}

	@Override
	public User registration(String name, String login, String password) throws DAOException {
		Connection con = null;
		ResultSet rs = null;
		User user = null;
		try {

			ConnectionPoolFactory ObjectCPFactory = ConnectionPoolFactory.getInstance();
			ConnectionPool cp = ObjectCPFactory.getConnectionPool();
			con = cp.takeConnection();

			PreparedStatement ps = con.prepareStatement(addUser);

			ps.setString(first, name);
			ps.setString(second, login);
			ps.setString(third, password);
			ps.setString(fourth,Guest );
			ps.executeUpdate();

			ps = con.prepareStatement(SelectUser);
			ps.setString(first, login);
			ps.setString(second, password);
			rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(first);
				String nameBD = rs.getString(second);
				String loginBD = rs.getString(third);
				String passwordBD = rs.getString(fourth);
				String role = rs.getString(fifth);
				user = new User(id, nameBD, loginBD, passwordBD, role);
			}
			cp.removeConnection();

		} catch (ConnectionPoolException e) {
			throw new DAOException(e);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return user;
	}

}
