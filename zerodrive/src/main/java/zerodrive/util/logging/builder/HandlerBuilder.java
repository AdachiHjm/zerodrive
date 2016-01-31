package zerodrive.util.logging.builder;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Handler;
import java.util.logging.Level;

import zerodrive.util.reflect.converter.ByteConverter;
import zerodrive.util.reflect.converter.DoubleConverter;
import zerodrive.util.reflect.converter.FloatConverter;
import zerodrive.util.reflect.converter.IntegerConverter;
import zerodrive.util.reflect.converter.LongConverter;
import zerodrive.util.reflect.converter.ShortConverter;
import zerodrive.util.reflect.converter.StringConverter;
import zerodrive.util.reflect.converter.TypeConverter;


/**
 * {@linkplain java.util.logging.Handler} を構築するビルダクラスです。
 * 
 * @author AdachiHjm
 * @created 2016/01/31 0:24:28
 *
 */
public abstract class HandlerBuilder {
    private static final String ATTR_ENCODING = "encoding";
    private static final String ATTR_LEVEL = "level";

    //======================================================================
    // Fields
    private final Map<Class<?>, TypeConverter<?>> converters = new HashMap<>();
    private final HandlerBuilderContainer container;

    private final String name;
    private final String type;
    private final Map<String, String> attributes = new HashMap<>();
    private final Map<String, String> properties = new HashMap<>();


    //======================================================================
    // Constructors
    protected HandlerBuilder(String _name, String _type, HandlerBuilderContainer _container) {
        this.name = _name;
        this.type = _type;
        this.container = _container;
        if (null == this.name) {
            throw new IllegalStateException("'name' must not be null.");
        }
        if (null == this.type) {
            throw new IllegalStateException("'type' must not be null.");
        }
        if (null == this.container) {
            throw new IllegalStateException("'container' must not be null.");
        }

        this.container.add(this);

        this.converters.put(Byte.class, new ByteConverter());
        this.converters.put(Double.class, new DoubleConverter());
        this.converters.put(Float.class, new FloatConverter());
        this.converters.put(Integer.class, new IntegerConverter());
        this.converters.put(Long.class, new LongConverter());
        this.converters.put(Short.class, new ShortConverter());
        this.converters.put(String.class, new StringConverter());
    }


    //======================================================================
    // Methods
    /**
     * @return {@linkplain Handler} instance.
     */
    public abstract Handler build();

    public void addAttribute(String name, String value) {
        this.attributes.put(name, value);
    }

    public void addProperty(String name, String value) {
        this.properties.put(name, value);
    }

    protected <T> void addConverter(Class<T> type, TypeConverter<T> converter) {
        if (null == type) {
            throw new NullPointerException("Argument 'type' must not be null.");
        }
        if (null == converter) {
            throw new NullPointerException("Argument 'converter' must not be null.");
        }
        this.converters.put(type, converter);
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

    protected Set<String> getPropertyNames() {
        return this.properties.keySet();
    }

    protected <T> Object getPropertyAs(String _name, Class<T> type) {
        return this.getPropertyAs(_name, type, null);
    }

    protected <T> T getPropertyAs(String _name, Class<T> type, T defaultValue) {
        return this.converters.containsKey(type) ? type.cast(this.converters.get(type).convert(this.properties.get(_name))) : defaultValue;
    }

    protected Handler getPropertyAsHandler(String _name) {
        return this.container.getHandler(_name);
    }

    protected Level getPropertyAsLevel(String _name) {
        return this.getPropertyAsLevel(_name, null);
    }

    protected Level getPropertyAsLevel(String _name, Level defaultLevel) {
        return this.properties.containsKey(_name) ? Level.parse(this.properties.get(_name)) : defaultLevel;
    }


    //======================================================================
    // Getters
    /**
     * @return handler name.
     */
    protected String getName() {
        return this.name;
    }

    /**
     * @return handler class name.
     */
    protected String getType() {
        return this.type;
    }
}
