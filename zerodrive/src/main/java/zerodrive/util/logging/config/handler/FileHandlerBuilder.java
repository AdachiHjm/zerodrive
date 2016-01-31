package zerodrive.util.logging.config.handler;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.MemoryHandler;


/**
 * {@linkplain FileHandler} を構築する {@linkplain HandlerBuilder} のサブクラスです。
 * 
 * @author AdachiHjm
 * @created 2016/01/31 22:18:57
 *
 */
public class FileHandlerBuilder extends HandlerBuilder {
    private static final String PROPERTY_LIMIT = "limit";
    private static final String PROPERTY_COUNT = "count";
    private static final String PROPERTY_PATTERN = "pattern";
    private static final String PROPERTY_APPEND = "append";
    private static final String DEFAULT_PATTERN = "%h/java%u.log";
    private static final int DEFAULT_COUNT = 1;
    private static final int DEFAULT_LIMIT = 0;
    private static final boolean DEFAULT_APPEND = false;

    //======================================================================
    // Fields
    private final Set<String> constructorArgs = new HashSet<>();


    //======================================================================
    // Constructors
    public FileHandlerBuilder(String name, String type, HandlerBuilderContainer container) {
        super(name, type, container);

        this.constructorArgs.add(PROPERTY_LIMIT);
        this.constructorArgs.add(PROPERTY_COUNT);
        this.constructorArgs.add(PROPERTY_PATTERN);
        this.constructorArgs.add(PROPERTY_APPEND);
    }

    /**
     * @see zerodrive.util.logging.config.handler.HandlerBuilder#build()
     */
    @Override
    public Handler build() {
        try {
            final FileHandler handler =
                new FileHandler(
                    this.getPropertyAs(PROPERTY_PATTERN, String.class, DEFAULT_PATTERN),
                    this.getPropertyAs(PROPERTY_LIMIT, Integer.class, DEFAULT_LIMIT),
                    this.getPropertyAs(PROPERTY_COUNT, Integer.class, DEFAULT_COUNT),
                    this.getPropertyAs(PROPERTY_APPEND, Boolean.class, DEFAULT_APPEND));
            handler.setEncoding(this.getEncoding());
            handler.setLevel(this.getLevel());
            handler.setFilter(this.getFilter());

            this.getPropertyNames().stream()
                .filter(name -> !this.constructorArgs.contains(name))
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
