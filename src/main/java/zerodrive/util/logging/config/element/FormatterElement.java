package zerodrive.util.logging.config.element;

import org.xml.sax.Attributes;

import zerodrive.util.logging.config.filter.FilterBuilder;


public class FormatterElement {
    private final String type;
    private final String builder;

    public FormatterElement(Attributes attributes) {
        this.type = attributes.getValue(AttrNames.CLASS);
        this.builder = attributes.getValue(AttrNames.BUILDER);
    }

    public FilterBuilder getFilterBuilder() {
        return new FilterBuilder(this.type);
    }
}
