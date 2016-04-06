package zerodrive.jdbc.spi;

import java.sql.Connection;


/**
 * @author AdachiHjm
 * @created 2015/03/30 21:13:31
 */
public interface ConnectionProvider {

	Connection getConnection();

	void initialize();

	void shutdown();
}
