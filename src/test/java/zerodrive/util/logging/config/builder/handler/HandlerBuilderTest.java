package zerodrive.util.logging.config.builder.handler;

import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;

import org.junit.Assert;
import org.junit.Test;


public class HandlerBuilderTest {

    @Test
    public void test() throws Exception {
        HandlerBuilder builder = new HandlerBuilder("test", ConsoleHandler.class.getName(), "UTF-8", "INFO", new HandlerFactory());
        Handler handler = builder.build();
        Assert.assertNotNull(handler);
        Assert.assertEquals(ConsoleHandler.class, handler.getClass());
    }
}
