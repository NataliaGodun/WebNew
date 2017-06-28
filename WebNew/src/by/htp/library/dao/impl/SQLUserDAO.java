package by.htp.library.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import by.htp.connection.pool.CPFactory;
import by.htp.connection.pool.ConnectionPool;
import by.htp.connection.pool.ConnectionPoolException;
import by.htp.library.dao.UserDAO;
import by.htp.library.dao.exception.DAOException;
import by.htp.library.domain.User;

public class SQLUserDAO implements UserDAO {
private static final String SelectUser="SELECT * FROM PEOPLE where login=? and password=?";
private static final int first=1;
private static final int second=2;
private static final int third=3;
private static final int fourth=4;
private static final int fifth=5;
	@Override
	public User authorization(String login, String password) throws DAOException {
		Connection con = null;
		ResultSet rs = null;

		User user = null;

		try {
			CPFactory ObjectFactory = CPFactory.getInstance();
			ConnectionPool cp = ObjectFactory.getConPool();

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
				user = new User(id, name, loginBD, passwordBD,role);
				System.out.println(role);
				
			}
			cp.removeConnection();
			
		} catch (ConnectionPoolException e) {
			throw new DAOException(e);
		}
		catch (SQLException e) {
			System.out.println(" DAOException");
			throw new DAOException(e);
		}
		return user;	
	}

	@Override
	public User registration(String name, String surname, String login, String password) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		User user = null;
		int id = 15;
		try {

			Class.forName("org.gjt.mm.mysql.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/site?useSSL=false", "root", "ER567ghm");
			st = con.createStatement();
			String sql = "insert into USERS (id,name,surname,login,password) values (?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setString(2, name);
			ps.setString(3, surname);
			ps.setString(4, login);
			ps.setString(5, password);
			ps.executeUpdate();
			System.out.println("Doshla suda");
			String sql2 = "SELECT * FROM USERS where login=? and password=?";
			ps = con.prepareStatement(sql2);
			ps.setString(1, login);
			ps.setString(2, password);
			rs = ps.executeQuery();
			while (rs.next()) {
				int idDB = rs.getInt(1);
				String nameDB = rs.getString(2);
				String surnameDB = rs.getString(3);
				String loginBD = rs.getString(4);
				String passwordBD = rs.getString(5);
				user = new User(idDB, nameDB, surnameDB, loginBD, passwordBD);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;
	}

}
