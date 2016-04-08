package zerodrive.util.logging.config.element;

import java.lang.reflect.Constructor;

import org.xml.sax.Attributes;

import zerodrive.util.logging.config.builder.filter.FilterBuilder;


public class FilterElement {
    private final String type;
    private final String builder;

    public FilterElement(Attributes attributes) {
        this.type = attributes.getValue(AttrNames.CLASS);
        this.builder = attributes.getValue(AttrNames.BUILDER);
    }

    public FilterBuilder getFilterBuilder() throws Exception {
        final Class<? extends FilterBuilder> builderClass = (null != this.builder) ? Class.forName(this.builder).asSubclass(FilterBuilder.class) : FilterBuilder.class;
        final Constructor<? extends FilterBuilder> constructor = builderClass.getConstructor(String.class);
        return constructor.newInstance(this.type);
    }
}
