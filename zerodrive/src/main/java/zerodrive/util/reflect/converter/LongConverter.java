package zerodrive.util.reflect.converter;


/**
 * 文字列を {@linkplain Long} に変換する {@linkplain TypeConverter} の実装です。
 *
 * @author AdachiHjm
 * @created 2016/01/31 17:29:29
 *
 */
public class LongConverter implements TypeConverter<Long> {

    /**
     * @see zerodrive.util.reflect.converter.TypeConverter#convert(java.lang.String)
     */
    @Override
    public Long convert(String value) {
        return null != value ? Long.valueOf(value) : null;
    }

}
