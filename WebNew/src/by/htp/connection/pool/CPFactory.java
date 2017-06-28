package by.htp.connection.pool;

public final class CPFactory {
	private static final CPFactory instance = new CPFactory();
	private final ConnectionPool a = new ConnectionPoolimpl();

	private CPFactory() {

	}

	public static CPFactory getInstance() {
		return instance;
	}

	public ConnectionPool getConPool() {
		return a;
	}
}
