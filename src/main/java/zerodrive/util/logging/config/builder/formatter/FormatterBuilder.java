package zerodrive.util.logging.config.builder.formatter;

import java.util.logging.Filter;

import zerodrive.util.logging.config.builder.BaseBuilder;


/**
 * Filter を構築する {@link BaseBuilder} のサブクラスです。
 * 
 * @author AdachiHjm
 * @created 2016/01/31 23:26:45
 *
 */
public class FormatterBuilder extends BaseBuilder<Filter> {
    //======================================================================
    // Constructors
    public FormatterBuilder (String className) throws ClassNotFoundException {
        super(className);
    }
}
