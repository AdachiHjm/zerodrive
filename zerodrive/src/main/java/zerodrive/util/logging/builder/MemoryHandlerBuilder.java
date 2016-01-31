package zerodrive.util.logging.builder;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
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
    private static final String PROPERTY_TARGET = "target";
    private static final String PROPERTY_SIZE = "size";
    private static final String PROPERTY_PUSH = "push";

    //======================================================================
    // Fields
    private final Set<String> ignoreNameSet = new HashSet<>();


    //======================================================================
    // Constructors
    public MemoryHandlerBuilder(String name, String type, HandlerBuilderContainer container) {
        super(name, type, container);

        this.ignoreNameSet.add(PROPERTY_TARGET);
        this.ignoreNameSet.add(PROPERTY_SIZE);
        this.ignoreNameSet.add(PROPERTY_PUSH);
    }


    //======================================================================
    // Methods
    /**
     * @see zerodrive.util.logging.builder.HandlerBuilder#build()
     */
    @Override
    public Handler build() {
        try {
            final MemoryHandler handler =
                new MemoryHandler(
                    this.getPropertyAsHandler(PROPERTY_TARGET),
                    this.getPropertyAs(PROPERTY_SIZE, Integer.class, 1000),
                    this.getPropertyAsLevel(PROPERTY_PUSH, Level.SEVERE));
            handler.setEncoding(this.getEncoding());
            handler.setLevel(this.getLevel());

            this.getPropertyNames().stream()
                .filter(name -> !this.ignoreNameSet.contains(name))
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
