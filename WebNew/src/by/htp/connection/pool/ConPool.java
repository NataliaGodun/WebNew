package by.htp.connection.pool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public interface ConPool {
	public void initPoolData() throws ConnectionPoolException;

	public void dispose();

	public Connection takeConnection() throws ConnectionPoolException;

	public void closeConnection(Connection con, Statement st, ResultSet rs);

	public void closeConnection(Connection con, Statement st);

	public void removeConnection() throws ConnectionPoolException;
}
