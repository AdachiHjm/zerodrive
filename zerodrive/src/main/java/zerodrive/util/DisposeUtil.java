package zerodrive.util;

import java.io.Closeable;
import java.io.IOException;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 * @author AdachiHjm
 * @createdOn 2013/04/20 16:58:17
 *
 */
public class DisposeUtil {
    private DisposeUtil() { throw new UnsupportedOperationException(); }


    public static void dispose(Closeable closeable) {
        if (null != closeable) {
            try {
                closeable.close();
            } catch (IOException ignore) {
                // Ignore.
                ignore.printStackTrace();
            }
        }
    }

    public static void dispose(XMLStreamReader reader) {
        if (null != reader) {
            try {
                reader.close();
            } catch (XMLStreamException ignore) {
                // Ignore.
                ignore.printStackTrace();
            }
        }
    }
}
