package zerodrive.net.http;

import java.net.HttpURLConnection;


/**
 * @author AdachiHjm
 * @created 2015/06/30 0:00:23
 *
 */
public class HttpConnection implements AutoCloseable {
    private final HttpURLConnection conn;

    public HttpConnection(HttpURLConnection _conn) {
        this.conn = _conn;
        if (null == this.conn) {
            // FIXME: i18n 対応
            throw new IllegalStateException("'conn' must not be null.");
        }
    }


    @Override
    public void close() throws Exception {
        if (null != this.conn) {
            this.conn.disconnect();
        }
    }

}
