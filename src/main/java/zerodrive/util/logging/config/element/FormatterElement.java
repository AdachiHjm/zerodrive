package zerodrive.util.logging.config.element;

import java.lang.reflect.Constructor;

import org.xml.sax.Attributes;

import zerodrive.util.logging.config.builder.formatter.FormatterBuilder;


public class FormatterElement {
    private final String type;
    private final String builder;

    public FormatterElement(Attributes attributes) {
        this.type = attributes.getValue(AttrNames.CLASS);
        this.builder = attributes.getValue(AttrNames.BUILDER);
    }

    public FormatterBuilder getFormatterBuilder() throws Exception {
        final Class<? extends FormatterBuilder> builderClass = (null != this.builder) ? Class.forName(this.builder).asSubclass(FormatterBuilder.class) : FormatterBuilder.class;
        final Constructor<? extends FormatterBuilder> constructor = builderClass.getConstructor(String.class);
        return constructor.newInstance(this.type);
    }
}
