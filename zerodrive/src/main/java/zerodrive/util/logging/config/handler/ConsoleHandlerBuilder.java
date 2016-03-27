package zerodrive.util.logging.config.handler;

import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;


/**
 * {@linkplain ConsoleHandler} を構築する {@linkplain HandlerBuilder} のサブクラスです。
 * 
 * @author AdachiHjm
 * @created 2016/01/31 18:16:02
 *
 */
public class ConsoleHandlerBuilder extends HandlerBuilder {
    //======================================================================
    // Constants
    private final String PROPERTY_OUTPUT = "output";


    //======================================================================
    // Constructors
    public ConsoleHandlerBuilder(String name, String className, String encoding, String level, HandlerFactory factory) {
        super(name, className, encoding, level, factory);
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

            return this.setProperties(ConsoleHandler.class, handler, PROPERTY_OUTPUT);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
