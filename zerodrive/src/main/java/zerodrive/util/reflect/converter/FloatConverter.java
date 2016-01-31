package zerodrive.util.reflect.converter;


/**
 * 文字列を {@linkplain Float} に変換する {@linkplain TypeConverter} の実装です。
 * 
 * @author AdachiHjm
 * @created 2016/01/31 17:21:09
 *
 */
public class FloatConverter implements TypeConverter<Float> {

    /**
     * @see zerodrive.util.reflect.converter.TypeConverter#convert(java.lang.String)
     */
    @Override
    public Float convert(String value) {
        return null != value ? Float.valueOf(value) : null;
    }

}
