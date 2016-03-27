package zerodrive.util.logging.config.handler;

import java.util.logging.FileHandler;
import java.util.logging.Handler;


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
    public FileHandlerBuilder(String name, String className, String encoding, String level, HandlerFactory factory) {
        super(name, className, encoding, level, factory);
    }


    //======================================================================
    // Methods
    /**
     * @see zerodrive.util.logging.config.handler.HandlerBuilder#build()
     */
    @Override
    public Handler build() {
        try {
            final FileHandler handler =
                new FileHandler(
                    this.getPropertyAs(INITARG_PATTERN, String.class, DEFAULT_PATTERN),
                    this.getPropertyAs(INITARG_LIMIT, Integer.class, DEFAULT_LIMIT),
                    this.getPropertyAs(INITARG_COUNT, Integer.class, DEFAULT_COUNT),
                    this.getPropertyAs(INITARG_APPEND, Boolean.class, DEFAULT_APPEND));
            handler.setEncoding(this.getEncoding());
            handler.setLevel(this.getLevel());
            handler.setFilter(this.getFilter());

            return this.setProperties(FileHandler.class, handler, INITARG_PATTERN, INITARG_LIMIT, INITARG_COUNT, INITARG_APPEND);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
