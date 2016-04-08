package zerodrive.util.logging.config.builder;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import zerodrive.util.reflect.ObjectBuilder;
import zerodrive.util.reflect.converter.ByteConverter;
import zerodrive.util.reflect.converter.DoubleConverter;
import zerodrive.util.reflect.converter.FloatConverter;
import zerodrive.util.reflect.converter.IntegerConverter;
import zerodrive.util.reflect.converter.LongConverter;
import zerodrive.util.reflect.converter.ShortConverter;
import zerodrive.util.reflect.converter.StringConverter;
import zerodrive.util.reflect.converter.TypeConverter;


/**
 * 
 * @author AdachiHjm
 * @created 2016/01/31 23:49:25
 *
 */
public abstract class BaseBuilder<C> {
    //======================================================================
    // Fields
    private final Map<Class<?>, TypeConverter<?>> converters = new HashMap<>();
    private final Map<String, Object> properties = new HashMap<>();
    private final Class<C> type;


    //======================================================================
    // Constructors
    @SuppressWarnings("unchecked")
    protected BaseBuilder(String className) throws ClassNotFoundException {
        this((Class<C>) Class.forName(className));
    }

    protected BaseBuilder(Class<C> _type) {
        this.type = _type;

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
    public C build() {
        try {
            final ObjectBuilder<C> builder = new ObjectBuilder<>(this.getType());

            for (Map.Entry<String, Object> entry : this.getProperties().entrySet()) {
                builder.addProperty(entry.getKey(), entry.getValue());
            }

            return builder.build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public <T> void addProperty(Class<T> type, String name, String value) {
        this.properties.put(name, this.convert(type, value));
    }

    public void addProperty(String name, Object value) {
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

    protected Set<String> getPropertyNames() {
        return this.properties.keySet();
    }

    protected Object getProperty(String name) {
        return this.getProperty(name, null);
    }

    protected Object getProperty(String name, Object defaultValue) {
        Object value = this.properties.get(name);
        return (null != value) ? value : defaultValue;
    }

    private <V> V convert(Class<V> type, String value) {
        return type.cast(this.converters.get(type).convert(value));
    }

    //======================================================================
    // Getters
    protected Class<C> getType() {
        return this.type;
    }

    protected Map<String, Object> getProperties() {
        return this.properties;
    }
}
