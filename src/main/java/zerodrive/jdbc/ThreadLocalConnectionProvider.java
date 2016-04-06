package zerodrive.jdbc;

import java.sql.Connection;

import zerodrive.jdbc.spi.ConnectionProvider;

/**
 * @author AdachiHjm
 * @created 2015/03/30 21:16:18
 */
public class ThreadLocalConnectionProvider implements ConnectionProvider {
	private static final ThreadLocal<Connection> LOCAL_CONNECTION = new ThreadLocal<Connection>() {
		protected Connection initialValue() {
			return null;
		};
	};
	private final ConnectionProvider origin;


	//======================================================================
	// Constructors
	public ThreadLocalConnectionProvider(ConnectionProvider _origin) {
		this.origin = _origin;
		if (null == this.origin) {
			throw new NullPointerException("'origin' must not be null.");
		}
	}


	//======================================================================
	// Methods
	@Override
	public synchronized Connection getConnection() {
		Connection conn = LOCAL_CONNECTION.get();
		if (null == conn) {
			conn = this.origin.getConnection();
			LOCAL_CONNECTION.set(conn);
		}
		return conn;
	}

	@Override
	public void initialize() {
        this.origin.initialize();
	}

	@Override
	public void shutdown() {
        this.origin.shutdown();
	}

}
