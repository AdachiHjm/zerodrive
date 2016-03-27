package zerodrive.util.logging.config.handler;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Handler;


/**
 * @author AdachiHjm
 * @created 2016/01/31 0:36:38
 *
 */
public class HandlerFactory {
    //======================================================================
    // Fields
    private final Map<String, Handler> handlers = new HashMap<>();
    private final Map<String, HandlerBuilder> builders = new HashMap<>();


    //======================================================================
    // Methods
    void add(HandlerBuilder builder) {
        if (null == builder) {
            throw new NullPointerException("Argument 'builder' must not be null.");
        }
        this.builders.put(builder.getName(), builder);
    }

    public Handler getHandler(String name) {
        if (null == name) {
            throw new NullPointerException("Argument 'name' must not be null.");
        }

        if (this.handlers.containsKey(name)) {
            return this.handlers.get(name);
        } else {
            if (this.builders.containsKey(name)) {
                this.handlers.put(name, this.builders.remove(name).build());
            }
        }
        throw new IllegalStateException("Handler " + name + " wasn't found.");
    }
}
