package zerodrive.util.logging;

import org.junit.BeforeClass;
import org.junit.Test;


public class LoggingConfigTest {
    private static final Logger LOG = Logger.getLogger(LoggingConfigTest.class);

    @BeforeClass
    public static void beforeClass() throws Exception {
        System.setProperty("java.util.logging.config.class", LoggingConfig.class.getName());
    }

    @Test
    public void test() {
    }
}
