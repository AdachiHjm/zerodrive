package zerodrive.util.logging.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
public abstract class AbstractBuilder<C> {
    //======================================================================
    // Fields
    private final Map<Class<?>, TypeConverter<?>> converters = new HashMap<>();
    private final Map<String, Object> properties = new HashMap<>();
    private final String className;


    //======================================================================
    // Constructors
    protected AbstractBuilder(String _className) {
        this.className = _className;
        if (null == this.className) {
            throw new IllegalStateException("'className' must not be null.");
        }

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
    public abstract C build();

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

//    protected <T> T getPropertyAs(String _name, Class<T> type) {
//        return this.getPropertyAs(_name, type, null);
//    }
//
//    protected <T> T getPropertyAs(String _name, Class<T> type, T defaultValue) {
//        return this.converters.containsKey(type) ? type.cast(this.converters.get(type).convert(this.properties.get(_name))) : defaultValue;
//    }
//
//    protected <R> R setProperties(final Class<R> cls, final R obj, String... ignoreProperties) {
//        return this.setProperties(cls, cls, obj, ignoreProperties);
//    }
//
//    protected <R> R setProperties(final Class<R> resultType, final Class<?> cls, final Object obj, String... ignoreProperties) {
//        this.getPropertyNames().stream()
//        .filter(name -> !Arrays.asList(ignoreProperties).contains(name))
//        .forEach(name -> {
//            try {
//                PropertyDescriptor desc = new PropertyDescriptor(name, cls);
//                Method setter = desc.getWriteMethod();
//                if (null != setter) {
//                    Class<?>[] types = setter.getParameterTypes();
//                    if (1 == types.length) {
//                        setter.invoke(obj, this.getPropertyAs(name, types[0]));
//                    }
//                }
//            } catch (Exception ignore) {
//                // Ignore.
//            }
//        });
//        return resultType.cast(obj);
//    }

    private <V> V convert(Class<V> type, String value) {
        return type.cast(this.converters.get(type).convert(value));
    }

    //======================================================================
    // Getters
    protected String getClassName() {
        return this.className;
    }

    protected Map<String, Object> getProperties() {
        return this.properties;
    }
}
