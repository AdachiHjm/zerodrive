package zerodrive.behavior.impl;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import zerodrive.behavior.ActionConfig;
import zerodrive.behavior.ActionExecutor;


/**
 * @author AdachiHjm
 * @created 2015/11/19 23:01:40
 *
 */
public class SimpleActionExecutor extends ActionExecutor {
    //======================================================================
    // Fields
    private final ConcurrentMap<Class<?>, Object> cache = new ConcurrentHashMap<Class<?>, Object>();


    //======================================================================
    // Methods
    /**
     * @see zerodrive.behavior.ActionExecutor#create(zerodrive.behavior.ActionConfig)
     */
    @Override
    protected Object create(ActionConfig config) throws Exception {
        if (this.cache.containsKey(config.getActionClass())) {
            return this.cache.get(config.getActionClass());
        }

        Object action = config.getActionClass().newInstance();

        for (Class<?> type = action.getClass(); !Object.class.equals(type); type = type.getSuperclass()) {
            Field[] fields = type.getDeclaredFields();
            for (Field field : fields) {
                if (config.getProperties().containsKey(field.getName())) {
                    if (Modifier.isPublic(field.getModifiers())) {
                        field.set(action, config.getProperties().get(field.getName()));
                    } else {
                        PropertyDescriptor desc = new PropertyDescriptor(field.getName(), type);
                        Method setter = desc.getWriteMethod();
                        setter.invoke(action, config.getProperties().get(field.getName()));
                    }
                }
            }
        }

        if (config.isOnce()) {
            action = this.cache.putIfAbsent(config.getActionClass(), action);
        }
        return action;
    }

}
