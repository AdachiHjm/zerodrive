package zerodrive.util.logging.config.builder.handler;

import java.util.Map;
import java.util.logging.Filter;
import java.util.logging.Handler;
import java.util.logging.Level;

import zerodrive.util.logging.config.AbstractBuilder;
import zerodrive.util.reflect.ObjectBuilder;
import zerodrive.util.reflect.converter.TypeConverter;


/**
 * Handler を構築する {@link AbstractBuilder} のサブクラスです。
 * 
 * @author AdachiHjm
 * @created 2016/01/31 0:24:28
 *
 */
public class HandlerBuilder extends AbstractBuilder<Handler> {
    //======================================================================
    // Fields
    private final HandlerFactory factory;
    private final String name;


    //======================================================================
    // Constructors
    protected HandlerBuilder(String _name, String _className, String _encoding, String _level, HandlerFactory _factory) {
        super(_className);
        this.addConverter(Level.class, new LevelConverter());


        this.name = _name;
        this.factory = _factory;
        if (null == name) {
            throw new IllegalStateException("'name' must not be null.");
        }
        if (null == factory) {
            throw new IllegalStateException("'factory' must not be null.");
        }
        this.factory.add(this);

        this.addProperty(String.class, "encoding", _encoding);
        this.addProperty(Level.class, "level", _level);
    }


    //======================================================================
    // Methods
    /**
     * @return java.util.logging.Handler instance.
     */
    @Override
    public Handler build() {
        try {
            final Class<? extends Handler> type = Class.forName(this.getClassName()).asSubclass(Handler.class);
            final ObjectBuilder<? extends Handler> builder = new ObjectBuilder<>(type);

            for (Map.Entry<String, Object> entry : this.getProperties().entrySet()) {
                builder.addProperty(entry.getKey(), entry.getValue());
            }

            return builder.build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected Handler getPropertyAs(String _name) {
        if (this.getName().equals(_name)) {
            throw new IllegalArgumentException("Handler name " + _name + " is itself.");
        }
        return this.factory.getHandler(_name);
    }


    //======================================================================
    // Getters
    public String getName() {
        return this.name;
    }


    //======================================================================
    // Setters
    public void setFilter(Filter filter) {
        this.addProperty("filter", filter);
    }


    //======================================================================
    // Inner Classes
    private static class LevelConverter implements TypeConverter<Level> {
        @Override
        public Level convert(String level) {
            return null != level ? Level.parse(level) : Level.ALL;
        }
    }
}
