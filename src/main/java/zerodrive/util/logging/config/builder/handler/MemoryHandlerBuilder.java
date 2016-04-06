package zerodrive.util.logging.config.builder.handler;

import java.util.Map;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.MemoryHandler;

import zerodrive.util.reflect.ObjectBuilder;


/**
 * MemoryHandler を構築する {@link HandlerBuilder} のサブクラスです。
 * 
 * @author AdachiHjm
 * @created 2016/01/31 18:16:02
 *
 */
public class MemoryHandlerBuilder extends HandlerBuilder {
    //======================================================================
    // Constructors
    public MemoryHandlerBuilder(String name, String encoding, String level, HandlerFactory container) {
        super(name, MemoryHandler.class.getName(), encoding, level, container);
    }


    //======================================================================
    // Methods
    /**
     * @see zerodrive.util.logging.config.handler.HandlerBuilder#build()
     */
    @Override
    public Handler build() {
        try {
            final ObjectBuilder<MemoryHandler> builder = new ObjectBuilder<>(MemoryHandler.class, Handler.class, int.class, Level.class);
            builder.addInitArg(this.getProperty("target"));
            builder.addInitArg(this.getProperty("size", 1000));
            builder.addInitArg(this.getProperty("push", Level.SEVERE));

            for (Map.Entry<String, Object> entry : this.getProperties().entrySet()) {
                builder.addProperty(entry.getKey(), entry.getValue());
            }

            return builder.build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
