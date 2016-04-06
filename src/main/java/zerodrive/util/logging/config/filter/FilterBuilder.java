package zerodrive.util.logging.config.filter;

import java.util.Map;
import java.util.logging.Filter;

import zerodrive.util.logging.config.AbstractBuilder;
import zerodrive.util.reflect.ObjectBuilder;


/**
 * Filter を構築する {@link AbstractBuilder} のサブクラスです。
 * 
 * @author AdachiHjm
 * @created 2016/01/31 23:26:45
 *
 */
public class FilterBuilder extends AbstractBuilder<Filter> {
    //======================================================================
    // Constructors
    public FilterBuilder (String className) {
        super(className);
    }


    //======================================================================
    // Methods
    public Filter build() {
        try {
            final Class<? extends Filter> type = Class.forName(this.getClassName()).asSubclass(Filter.class);
            final ObjectBuilder<? extends Filter> builder = new ObjectBuilder<>(type);

            for (Map.Entry<String, Object> entry : this.getProperties().entrySet()) {
                builder.addProperty(entry.getKey(), entry.getValue());
            }

            return builder.build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
