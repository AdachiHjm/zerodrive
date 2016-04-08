package zerodrive.util.logging.config.builder.handler;

import java.util.logging.ConsoleHandler;


/**
 * ConsoleHandler を構築する {@link HandlerBuilder} のサブクラスです。
 * 
 * @author AdachiHjm
 * @created 2016/01/31 18:16:02
 *
 */
public class ConsoleHandlerBuilder extends HandlerBuilder {
    //======================================================================
    // Constructors
    public ConsoleHandlerBuilder(String name, String encoding, String level, HandlerFactory factory)
            throws ClassNotFoundException {
        super(name, ConsoleHandler.class.getName(), encoding, level, factory);
    }

}
