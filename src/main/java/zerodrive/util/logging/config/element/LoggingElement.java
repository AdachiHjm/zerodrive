package zerodrive.util.logging.config.element;

import org.xml.sax.Attributes;

public class LoggingElement {
    private final String name;

    public LoggingElement(Attributes attributes) {
        this.name = attributes.getValue(AttrNames.NAME);
    }

    public String getName() {
        return this.name;
    }
}
