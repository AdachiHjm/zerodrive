package zerodrive.util.logging.config.handler;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.MemoryHandler;


/**
 * {@linkplain MemoryHandler} を構築する {@linkplain HandlerBuilder} のサブクラスです。
 * 
 * @author AdachiHjm
 * @created 2016/01/31 18:16:02
 *
 */
public class MemoryHandlerBuilder extends HandlerBuilder {
    //======================================================================
    // Constatns
    private static final String INITARG_TARGET = "target";
    private static final String INITARG_SIZE = "size";
    private static final String INITARG_PUSH = "push";


    //======================================================================
    // Constructors
    public MemoryHandlerBuilder(String name, String type, String encoding, String level, HandlerFactory container) {
        super(name, type, encoding, level, container);
    }


    //======================================================================
    // Methods
    /**
     * @see zerodrive.util.logging.config.handler.HandlerBuilder#build()
     */
    @Override
    public Handler build() {
        try {
            final MemoryHandler handler =
                new MemoryHandler(
                    this.getPropertyAs(INITARG_TARGET),
                    this.getPropertyAs(INITARG_SIZE, Integer.class, 1000),
                    this.getPropertyAsLevel(INITARG_PUSH, Level.SEVERE));
            handler.setEncoding(this.getEncoding());
            handler.setLevel(this.getLevel());
            handler.setFilter(this.getFilter());

            return this.setProperties(MemoryHandler.class, handler, INITARG_TARGET, INITARG_SIZE, INITARG_PUSH);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
