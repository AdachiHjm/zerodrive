package zerodrive.util.logging.config.handler;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Filter;
import java.util.logging.Handler;
import java.util.logging.Level;

import zerodrive.util.logging.config.AbstractBuilder;


/**
 * {@linkplain java.util.logging.Handler} を構築する {@linkplain AbstractBuilder} のサブクラスです。
 * 
 * @author AdachiHjm
 * @created 2016/01/31 0:24:28
 *
 */
public class HandlerBuilder extends AbstractBuilder<Handler> {
    private static final String ATTR_ENCODING = "encoding";
    private static final String ATTR_LEVEL = "level";

    //======================================================================
    // Fields
    private final HandlerBuilderContainer container;
    private final String name;
    private final Map<String, String> attributes = new HashMap<>();
    private Filter filter;


    //======================================================================
    // Constructors
    protected HandlerBuilder(String _name, String _className, HandlerBuilderContainer _container) {
        super(_className);

        this.name = _name;
        this.container = _container;
        if (null == this.name) {
            throw new IllegalStateException("'name' must not be null.");
        }
        if (null == this.container) {
            throw new IllegalStateException("'container' must not be null.");
        }

        this.container.add(this);
    }


    //======================================================================
    // Methods
    /**
     * @return {@linkplain Handler} instance.
     */
    @Override
    public Handler build() {
        try {
            final Class<? extends Handler> cls = Class.forName(this.getClassName()).asSubclass(Handler.class);
            final Handler handler = cls.newInstance();
            handler.setEncoding(this.getEncoding());
            handler.setLevel(this.getLevel());
            handler.setFilter(this.getFilter());

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

    public void addAttribute(String name, String value) {
        this.attributes.put(name, value);
    }

    /**
     * @return encoding name or null
     */
    protected String getEncoding() {
        return this.attributes.get(ATTR_ENCODING);
    }

    /**
     * @return message level
     */
    protected Level getLevel() {
        return this.attributes.containsKey(ATTR_LEVEL) ? Level.parse(this.attributes.get(ATTR_LEVEL)) : null;
    }

    protected Handler getPropertyAsHandler(String _name) {
        if (this.getName().equals(_name)) {
            throw new IllegalArgumentException("Handler name " + _name + " is itself.");
        }
        return this.container.getHandler(_name);
    }

    protected Level getPropertyAsLevel(String _name) {
        return this.getPropertyAsLevel(_name, null);
    }

    protected Level getPropertyAsLevel(String _name, Level defaultLevel) {
        return this.getProperties().containsKey(_name) ? Level.parse(this.getProperties().get(_name)) : defaultLevel;
    }


    //======================================================================
    // Getters
    /**
     * @return handler name.
     */
    protected String getName() {
        return this.name;
    }

    protected Filter getFilter() {
        return this.filter;
    }


    //======================================================================
    // Setters
    public void setFilter(Filter filter) {
        this.filter = filter;
    }
}
