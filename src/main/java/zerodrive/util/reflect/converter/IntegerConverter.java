package zerodrive.util.reflect.converter;


/**
 * 文字列を {@linkplain Integer} に変換する {@linkplain TypeConverter} の実装です。
 *
 * @author AdachiHjm
 * @created 2016/01/31 17:27:45
 *
 */
public class IntegerConverter implements TypeConverter<Integer> {

    /**
     * @see zerodrive.util.reflect.converter.TypeConverter#convert(java.lang.String)
     */
    @Override
    public Integer convert(String value) {
        return null != value ? Integer.valueOf(value) : null;
    }

}
