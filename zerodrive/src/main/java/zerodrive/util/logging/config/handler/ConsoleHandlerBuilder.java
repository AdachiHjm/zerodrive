package zerodrive.util.logging.config.handler;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.MemoryHandler;


/**
 * {@linkplain ConsoleHandler} を構築する {@linkplain HandlerBuilder} のサブクラスです。
 * 
 * @author AdachiHjm
 * @created 2016/01/31 18:16:02
 *
 */
public class ConsoleHandlerBuilder extends HandlerBuilder {
    private final String PROPERTY_OUTPUT = "output";

    public ConsoleHandlerBuilder(String name, String type, HandlerBuilderContainer container) {
        super(name, type, container);
    }

    /**
     * @see zerodrive.util.logging.config.handler.HandlerBuilder#build()
     */
    @Override
    public Handler build() {
        try {
            final String output = this.getPropertyAs(PROPERTY_OUTPUT, String.class);
            final ConsoleHandler handler = new ConsoleHandler() {
                private static final String STDOUT = "stdout";
                private static final String STDERR = "stderr";
                {
                    switch (null != output ? output : STDERR) {
                    case STDOUT:
                        this.setOutputStream(System.out);
                        break;
                    case STDERR:
                        this.setOutputStream(System.err);
                        break;
                    default:
                        this.setOutputStream(System.err);
                        break;
                    }
                }
            };
            handler.setEncoding(this.getEncoding());
            handler.setLevel(this.getLevel());
            handler.setFilter(this.getFilter());

            this.getPropertyNames().stream()
            .filter(name -> !PROPERTY_OUTPUT.equals(name))
            .forEach(name -> {
                try {
                    PropertyDescriptor desc = new PropertyDescriptor(name, MemoryHandler.class);
                    Method setter = desc.getWriteMethod();
                    if (null != setter) {
                        Class<?>[] types = setter.getParameterTypes();
                        if (1 == types.length) {
                            setter.invoke(handler, this.getPropertyAs(name, types[0]));
                        }
                    }
                } catch (Exception ignore) {
                    // Ignore.
                }
            });

            return handler;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
