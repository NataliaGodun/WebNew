package by.htp.connection.pool;

public final class CPFactory {
	private static final CPFactory instance = new CPFactory();
	private final ConPool a = new ConnectionPool();

	private CPFactory() {

	}

	public static CPFactory getInstance() {
		return instance;
	}

	public ConPool getConPool() {
		return a;
	}
}
