package zerodrive.util.reflect;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 * 
 * @author AdachiHjm
 * @created 2016/04/03 0:02:20
 */
public class ObjectBuilder<T> {
    //======================================================================
    // Fields
    private final Class<T> type;
    private final Constructor<T> constructor;
    private final List<Object> parameters = new LinkedList<>();
    private final Map<String, Object> properties = new HashMap<>();


    //======================================================================
    // Constructors
    public ObjectBuilder(final Class<T> _type, final Class<?>... parameterTypes) throws NoSuchMethodException, SecurityException {
        this.type = _type;
        if (null == this.type) {
            throw new IllegalStateException("'type' must not be null.");
        }

        this.constructor = this.type.getConstructor(parameterTypes);
    }


    //======================================================================
    // Methods
    public ObjectBuilder<T> addInitArg(final Object value) {
        this.parameters.add(value);

        return this;
    }

    public ObjectBuilder<T> addProperty(final String name, final Object value) {
        if (null == name) {
            throw new NullPointerException("Argument 'name' must not be null.");
        }
        this.properties.put(name, value);

        return this;
    }

    public T build() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        final T obj = this.constructor.newInstance(this.parameters.toArray(new Object[this.parameters.size()]));

        this.properties.keySet().stream()
        .forEach(propertyName -> {
            try {
                PropertyDescriptor desc = new PropertyDescriptor(propertyName, this.type);
                Method setter = desc.getWriteMethod();
                if (null != setter) {
                    Class<?>[] types = setter.getParameterTypes();
                    if (1 == types.length) {
                        setter.invoke(obj, this.properties.get(propertyName));
                    }
                }
            } catch (Exception ignore) {
                // Ignore.
            }
        });
        return obj;
    }
}
