package zerodrive.util.logging.config.handler;

import java.util.Map;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;

import zerodrive.util.reflect.ObjectBuilder;


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
    public ConsoleHandlerBuilder(String name, String encoding, String level, HandlerFactory factory) {
        super(name, ConsoleHandler.class.getName(), encoding, level, factory);
    }


    /**
     * @see zerodrive.util.logging.config.handler.HandlerBuilder#build()
     */
    @Override
    public Handler build() {
        try {
            final ObjectBuilder<ConsoleHandler> builder = new ObjectBuilder<>(ConsoleHandler.class);

            for (Map.Entry<String, Object> entry : this.getProperties().entrySet()) {
                builder.addProperty(entry.getKey(), entry.getValue());
            }

            return builder.build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
