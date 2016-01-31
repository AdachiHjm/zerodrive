package zerodrive.util.logging.builder;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.logging.Handler;


/**
 * {@linkplain HandlerBuilder} のデフォルト実装です。
 * 
 * @author AdachiHjm
 * @created 2016/01/31 1:04:57
 *
 */
public class DefaultHandlerBuilder extends HandlerBuilder {

    //======================================================================
    // Constructors
    public DefaultHandlerBuilder(String name, String type, HandlerBuilderContainer container) {
        super(name, type, container);
    }


    //======================================================================
    // Methods
    @Override
    public Handler build() {
        try {
            final Class<? extends Handler> cls = Class.forName(this.getType()).asSubclass(Handler.class);
            final Handler handler = cls.newInstance();
            handler.setEncoding(this.getEncoding());
            handler.setLevel(this.getLevel());

            this.getPropertyNames().forEach(name -> {
                try {
                    PropertyDescriptor desc = new PropertyDescriptor(name, cls);
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
