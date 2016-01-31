package zerodrive.util.reflect.converter;


/**
 * 変換を行わない {@linkplain TypeConverter} の実装です。
 * 
 * @author AdachiHjm
 * @created 2016/01/31 17:38:56
 *
 */
public class StringConverter implements TypeConverter<String> {

    /**
     * @see zerodrive.util.reflect.converter.TypeConverter#convert(java.lang.String)
     */
    @Override
    public String convert(String value) {
        return value;
    }

}
