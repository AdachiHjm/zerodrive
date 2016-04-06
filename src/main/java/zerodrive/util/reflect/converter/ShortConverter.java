package zerodrive.util.reflect.converter;


/**
 * 文字列を {@linkplain Short} に変換する {@linkplain TypeConverter} の実装です。
 * 
 * @author AdachiHjm
 * @created 2016/01/31 17:32:26
 *
 */
public class ShortConverter implements TypeConverter<Short> {

    /**
     * @see zerodrive.util.reflect.converter.TypeConverter#convert(java.lang.String)
     */
    @Override
    public Short convert(String value) {
        return null != value ? Short.valueOf(value) : null;
    }

}
