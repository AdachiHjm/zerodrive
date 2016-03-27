package zerodrive.util.logging.config.handler;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.logging.Filter;
import java.util.logging.Handler;
import java.util.logging.Level;

import zerodrive.util.logging.config.AbstractBuilder;
import zerodrive.util.reflect.converter.TypeConverter;


/**
 * Handler を構築する {@link AbstractBuilder} のサブクラスです。
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
    private final HandlerFactory factory;
    private final String name;
    private final String encoding;
    private final Level level;
    private Filter filter;


    //======================================================================
    // Constructors
    protected HandlerBuilder(String _name, String _className, String _encoding, String _level, HandlerFactory _factory) {
        super(_className);

        if (null == _name) {
            throw new IllegalStateException("Argument '_name' must not be null.");
        }
        if (null == _factory) {
            throw new IllegalStateException("Argument '_factory' must not be null.");
        }

        this.name = _name;
        this.encoding = _encoding;
        this.level = null != _level ? Level.parse(_level) : null;
        this.factory = _factory;

        this.factory.add(this);

        this.addConverter(Level.class, new LevelConverter());
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

            return this.setProperties(cls, handler);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @return encoding name or null
     */
    protected String getEncoding() {
        return this.encoding;
    }

    /**
     * @return message level
     */
    protected Level getLevel() {
        return this.level;
    }

    protected Handler getPropertyAs(String _name) {
        if (this.getName().equals(_name)) {
            throw new IllegalArgumentException("Handler name " + _name + " is itself.");
        }
        return this.factory.getHandler(_name);
    }

    protected Level getPropertyAsLevel(String _name) {
        return this.getPropertyAsLevel(_name, null);
    }

    protected Level getPropertyAsLevel(String _name, Level defaultLevel) {
        return this.getProperties().containsKey(_name) ? Level.parse(this.getProperties().get(_name)) : defaultLevel;
    }

    protected Handler setProperties(final Class<? extends Handler> cls, final Handler handler, String... ignoreFieldNames) {
        this.getPropertyNames().stream()
        .filter(name -> !Arrays.asList(ignoreFieldNames).contains(name))
        .forEach(name -> {
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

    private static class LevelConverter implements TypeConverter<Level> {
        @Override
        public Level convert(String level) {
            return null != level ? Level.parse(level) : Level.ALL;
        }
    }
}
