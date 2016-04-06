package zerodrive.util.logging.config.element;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.MemoryHandler;

import org.xml.sax.Attributes;

import zerodrive.util.logging.config.handler.ConsoleHandlerBuilder;
import zerodrive.util.logging.config.handler.FileHandlerBuilder;
import zerodrive.util.logging.config.handler.HandlerBuilder;
import zerodrive.util.logging.config.handler.HandlerFactory;
import zerodrive.util.logging.config.handler.MemoryHandlerBuilder;


public class HandlerElement {
    private final Map<String, Class<? extends HandlerBuilder>> builderMap = new HashMap<>();
    private final String name;
    private final String type;
    private final String encoding;
    private final String level;
    private final String builder;

    public HandlerElement(Attributes attributes) {
        this.name = attributes.getValue(AttrNames.NAME);
        this.type = attributes.getValue(AttrNames.CLASS);
        this.encoding = attributes.getValue(AttrNames.ENCODING);
        this.level = attributes.getValue(AttrNames.LEVEL);
        this.builder = attributes.getValue(AttrNames.BUILDER);
        this.builderMap.put(ConsoleHandler.class.getName(), ConsoleHandlerBuilder.class);
        this.builderMap.put(FileHandler.class.getName(), FileHandlerBuilder.class);
        this.builderMap.put(MemoryHandler.class.getName(), MemoryHandlerBuilder.class);
    }

    public HandlerBuilder getHandlerBuilder(final HandlerFactory factory) throws Exception {
        Class<? extends HandlerBuilder> builderClass;
        if (null != this.builder) {
            builderClass = Class.forName(this.builder).asSubclass(HandlerBuilder.class);
        } else {
            builderClass = (this.builderMap.containsKey(this.builder)) ? this.builderMap.get(this.type) : HandlerBuilder.class;
        }
        final Constructor<? extends HandlerBuilder> constructor = builderClass.getConstructor(String.class, String.class, String.class, String.class, HandlerFactory.class);
        return constructor.newInstance(this.name, this.type, this.encoding, this.level, factory);
    }

}
