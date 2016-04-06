package zerodrive.util.logging.config.builder.handler;

import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Handler;

import zerodrive.util.reflect.ObjectBuilder;


/**
 * {@linkplain FileHandler} を構築する {@linkplain HandlerBuilder} のサブクラスです。
 * 
 * @author AdachiHjm
 * @created 2016/01/31 22:18:57
 *
 */
public class FileHandlerBuilder extends HandlerBuilder {
    //======================================================================
    // Constants
    private static final String INITARG_LIMIT = "limit";
    private static final String INITARG_COUNT = "count";
    private static final String INITARG_PATTERN = "pattern";
    private static final String INITARG_APPEND = "append";
    private static final String DEFAULT_PATTERN = "%h/java%u.log";
    private static final int DEFAULT_COUNT = 1;
    private static final int DEFAULT_LIMIT = 0;
    private static final boolean DEFAULT_APPEND = false;


    //======================================================================
    // Constructors
    public FileHandlerBuilder(String name, String encoding, String level, HandlerFactory factory) {
        super(name, FileHandler.class.getName(), encoding, level, factory);
    }


    //======================================================================
    // Methods
    /**
     * @see zerodrive.util.logging.config.handler.HandlerBuilder#build()
     */
    @Override
    public Handler build() {
        try {
            final ObjectBuilder<FileHandler> builder = new ObjectBuilder<>(FileHandler.class, String.class, Integer.class, Integer.class, Boolean.class);
            builder.addInitArg(this.getProperty(INITARG_PATTERN, DEFAULT_PATTERN));
            builder.addInitArg(this.getProperty(INITARG_LIMIT, DEFAULT_LIMIT));
            builder.addInitArg(this.getProperty(INITARG_COUNT, DEFAULT_COUNT));
            builder.addInitArg(this.getProperty(INITARG_APPEND, DEFAULT_APPEND));

            for (Map.Entry<String, Object> entry : this.getProperties().entrySet()) {
                builder.addProperty(entry.getKey(), entry.getValue());
            }

            return builder.build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
