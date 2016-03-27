package zerodrive.util.logging;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.MemoryHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import zerodrive.util.logging.config.filter.FilterBuilder;
import zerodrive.util.logging.config.handler.ConsoleHandlerBuilder;
import zerodrive.util.logging.config.handler.FileHandlerBuilder;
import zerodrive.util.logging.config.handler.HandlerBuilder;
import zerodrive.util.logging.config.handler.HandlerFactory;
import zerodrive.util.logging.config.handler.MemoryHandlerBuilder;

/**
 * @author AdachiHjm
 * @created 2015/12/01 23:21:05
 *
 */
public class LoggingConfig {
    private static final String CONFIG = "logging.xml";
    private static final String DEFAULT_CONFIG = "zerodrive/util/logging/logging.xml";


    //======================================================================
    // Consutrucors
    public LoggingConfig() {
        InputStream input = null;
        try {
            input = this.findConfig();
            if (null == input) {
                throw new IllegalStateException("logging config is not found.");
            }

            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            XMLReader reader = parser.getXMLReader();

            LoggingConfigHandler handler = new LoggingConfigHandler();
            reader.setContentHandler(handler);
            reader.setEntityResolver(new LoggingConfigEntityResolver());
            reader.parse(new InputSource(input));
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException(e);
        } finally {
            if (null != input) {
                try { 
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private InputStream findConfig() throws IOException {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        URL url = loader.getResource(CONFIG);
        if (null == url) {
            url = loader.getResource(DEFAULT_CONFIG);
        }
        if (null != url) {
            return url.openStream();
        }
        return null;
    }


    private static class LoggingConfigHandler extends DefaultHandler {
        private final Map<String, Class<? extends HandlerBuilder>> builderMap = new HashMap<>();
        private LoggingConfigHandler() {
            this.builderMap.put(ConsoleHandler.class.getName(), ConsoleHandlerBuilder.class);
            this.builderMap.put(FileHandler.class.getName(), FileHandlerBuilder.class);
            this.builderMap.put(MemoryHandler.class.getName(), MemoryHandlerBuilder.class);
        }

        private static class Element {
            private static final String LOGGING = "logging";
            private static final String HANDLER = "handler";
            private static final String FILTER = "filter";
            private static final String FORMATTER = "formatter";
            private static final String LOGGER = "logger";
            private static final String ROOT = "root";
            private static final String HANDLER_REF = "handler-ref";
            private static final String PROPERTY = "property";
        }

        private final HandlerFactory factory = new HandlerFactory();
        private HandlerBuilder handlerBuilder;
        private FilterBuilder filterBuilder;

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes)
                throws SAXException {
            try {
                switch (qName) {
                case Element.LOGGING:
                    // do nothing.
                    break;
                case Element.HANDLER:
                    String handlerName = attributes.getValue("name");
                    String handlerClassName = attributes.getValue("class");
                    String encoding = attributes.getValue("encoding");
                    String level = attributes.getValue("level");
                    String builderClassName = attributes.getValue("builder");

                    Class<? extends HandlerBuilder> builderClass;
                    if (null != builderClassName) {
                        builderClass = Class.forName(builderClassName).asSubclass(HandlerBuilder.class);
                    } else {
                        builderClass = (this.builderMap.containsKey(handlerClassName)) ? this.builderMap.get(handlerClassName) : HandlerBuilder.class;
                    }
                    Constructor<? extends HandlerBuilder> constructor = builderClass.getConstructor(String.class, String.class, String.class, String.class, HandlerFactory.class);
                    this.handlerBuilder = constructor.newInstance(handlerName, handlerClassName, encoding, level, this.factory);
                    break;
                case Element.FILTER:
                    break;
                case Element.FORMATTER:
                    break;
                case Element.LOGGER:
                    break;
                case Element.ROOT:
                    break;
                case Element.HANDLER_REF:
                    break;
                case Element.PROPERTY:
                    break;
                default:
                    // do nothing.
                    break;
                }
            } catch (Exception e) {
                throw new SAXException(e);
            }





            // FIXME: 未実装！
            throw new UnsupportedOperationException("未実装！");
        }
    }

    private static class LoggingConfigEntityResolver implements EntityResolver {
        private static final String DTD_1_1 = "logging_1_0.dtd";

        @Override
        public InputSource resolveEntity(String publicId, String systemId)
                throws SAXException, IOException {
            if (systemId.endsWith(DTD_1_1)) {
                return new InputSource(Thread.currentThread().getContextClassLoader().getResourceAsStream(DTD_1_1));
            } else {
                return null;
            }
        }
    }
}
