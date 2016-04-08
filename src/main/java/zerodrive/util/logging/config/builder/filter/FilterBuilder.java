package zerodrive.util.logging.config.builder.filter;

import java.util.Map;
import java.util.logging.Filter;

import zerodrive.util.logging.config.builder.BaseBuilder;
import zerodrive.util.reflect.ObjectBuilder;


/**
 * Filter を構築する {@link BaseBuilder} のサブクラスです。
 * 
 * @author AdachiHjm
 * @created 2016/01/31 23:26:45
 *
 */
public class FilterBuilder extends BaseBuilder<Filter> {
    //======================================================================
    // Constructors
    public FilterBuilder (String className) throws ClassNotFoundException {
        super(className);
    }


    //======================================================================
    // Methods
    public Filter build() {
        try {
            final ObjectBuilder<? extends Filter> builder = new ObjectBuilder<>(this.getType());

            for (Map.Entry<String, Object> entry : this.getProperties().entrySet()) {
                builder.addProperty(entry.getKey(), entry.getValue());
            }

            return builder.build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
