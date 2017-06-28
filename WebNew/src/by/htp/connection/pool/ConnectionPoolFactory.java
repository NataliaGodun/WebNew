package by.htp.connection.pool;

public final class ConnectionPoolFactory {
	private static final ConnectionPoolFactory instance = new ConnectionPoolFactory();
	private final ConnectionPool a = new ConnectionPoolimpl();

	private ConnectionPoolFactory() {

	}

	public static ConnectionPoolFactory getInstance() {
		return instance;
	}

	public ConnectionPool getConnectionPool() {
		return a;
	}
}
