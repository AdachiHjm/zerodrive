package zerodrive.jdbc.config;

import java.io.InputStream;

import org.junit.Assert;
import org.junit.Test;


public class ConnectionProviderConfigTest {

    @Test(expected = NullPointerException.class)
    public void testLoad_NullArg() throws Exception {
        ConnectionProviderConfig.load(null);
    }

    @Test
    public void testLoad_001() throws Exception {
        ConnectionProviderConfig config;
        try (InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("provider-config_test.xml")) {
            config = ConnectionProviderConfig.load(in);
        }

        Assert.assertNotNull(config);
        Assert.assertEquals("testLoad_001", config.getName());
        Assert.assertEquals("Hoge", config.getDriverClassName());
        Assert.assertEquals("jdbc:hoge://localhost/test", config.getUrl());
        Assert.assertEquals("testuser", config.getUser());
        Assert.assertEquals("testpassword", config.getPassword());
        Assert.assertEquals("Any", config.getVendor());

        Assert.assertFalse(config.getDriverProperties().isEmpty());
        Assert.assertEquals("propValue1", config.getDriverProperties().get("propName1"));

        Assert.assertFalse(config.getProviderProperties().isEmpty());
        Assert.assertEquals("propValue2", config.getProviderProperties().get("propName2"));
    }
}
