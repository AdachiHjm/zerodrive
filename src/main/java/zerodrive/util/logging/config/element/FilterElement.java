package zerodrive.util.logging.config.element;

import java.util.HashMap;
import java.util.Map;

import org.xml.sax.Attributes;

import zerodrive.util.logging.config.builder.filter.FilterBuilder;
import zerodrive.util.logging.config.builder.handler.HandlerBuilder;


public class FilterElement {
    private final Map<String, Class<? extends HandlerBuilder>> builderMap = new HashMap<>();
    private final String type;
    private final String builder;

    public FilterElement(Attributes attributes) {
        this.type = attributes.getValue(AttrNames.CLASS);
        this.builder = attributes.getValue(AttrNames.BUILDER);
    }

    public FilterBuilder getFilterBuilder() throws ClassNotFoundException {
        return new FilterBuilder(this.type);
    }
}
