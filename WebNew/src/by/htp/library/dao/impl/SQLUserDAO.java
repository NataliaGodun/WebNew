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
import by.htp.library.domain.User;

public class SQLUserDAO implements UserDAO{

	
	@Override
	public User authorization(String login, String password)  {
		Connection con = null;
		ResultSet rs = null;
		
		User user=null;
		
		try {
			CPFactory ObjectFactory = CPFactory.getInstance();
			ConnectionPool cp =ObjectFactory.getConPool();
			

			try {
				con=cp.takeConnection();
			} catch (ConnectionPoolException e) {
				e.printStackTrace();
			}
			System.out.println(cp);
			//Class.forName("org.gjt.mm.mysql.Driver");
			//con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/site?useSSL=false","root","ER567ghm");
		//	st = con.createStatement();
			String sql="SELECT * FROM USERS where login=? and password=?";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, login);
			ps.setString(2, password);
			rs = ps.executeQuery();
			while (rs.next()) {
			int id=rs.getInt(1);
			String name=rs.getString(2);
			String surname=rs.getString(3);
			String loginBD=rs.getString(4);
			String passwordBD=rs.getString(5);
			 user=new User(id,name,surname,loginBD,passwordBD);
			
			}
			try {
				cp.removeConnection();
			} catch (ConnectionPoolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		
		return user;

		
	}

	@Override
	public User registration(String name, String surname, String login, String password) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		User user=null;
		int id=15;
		try {
			
			Class.forName("org.gjt.mm.mysql.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/site?useSSL=false","root","ER567ghm");
			st = con.createStatement();
			String sql="insert into USERS (id,name,surname,login,password) values (?,?,?,?,?)";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setString(2, name);
			ps.setString(3, surname);
			ps.setString(4, login);
			ps.setString(5, password);
			ps.executeUpdate();
			System.out.println("Doshla suda");
			String sql2="SELECT * FROM USERS where login=? and password=?";
			 ps=con.prepareStatement(sql2);
			ps.setString(1, login);
			ps.setString(2, password);
			rs = ps.executeQuery();
			while (rs.next()) {
			int idDB=rs.getInt(1);
			String nameDB=rs.getString(2);
			String surnameDB=rs.getString(3);
			String loginBD=rs.getString(4);
			String passwordBD=rs.getString(5);
			 user=new User(idDB,nameDB,surnameDB,loginBD,passwordBD);
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
