package zerodrive.util.reflect.converter;


/**
 * 文字列を {@linkplain Double} に変換する {@linkplain TypeConverter} の実装です。
 * 
 * @author AdachiHjm
 * @created 2016/01/31 17:16:04
 *
 */
public class DoubleConverter implements TypeConverter<Double> {

    /**
     * @see zerodrive.util.reflect.converter.TypeConverter#convert(java.lang.String)
     */
    @Override
    public Double convert(String value) {
        return null != value ? Double.valueOf(value) : null;
    }

}
