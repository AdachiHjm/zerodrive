package zerodrive.util.logging.config.filter;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.logging.Filter;

import zerodrive.util.logging.config.AbstractBuilder;


/**
 * {@linkplain java.util.logging.Filter} を構築するビルダクラスです。
 * 
 * @author AdachiHjm
 * @created 2016/01/31 23:26:45
 *
 */
public class FilterBuilder extends AbstractBuilder<Filter> {
    //======================================================================
    // Fields


    //======================================================================
    // Methods
    public FilterBuilder (String className) {
        super(className);
    }


    //======================================================================
    // Methods
    public Filter build() {
        try {
            final Class<? extends Filter> cls = Class.forName(this.getClassName()).asSubclass(Filter.class);
            final Filter filter = cls.newInstance();

            this.getPropertyNames().forEach(name -> {
                try {
                    PropertyDescriptor desc = new PropertyDescriptor(name, cls);
                    Method setter = desc.getWriteMethod();
                    if (null != setter) {
                        Class<?>[] types = setter.getParameterTypes();
                        if (1 == types.length) {
                            setter.invoke(filter, this.getPropertyAs(name, types[0]));
                        }
                    }
                } catch (Exception ignore) {
                    // Ignore.
                }
            });
            
            return filter;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
