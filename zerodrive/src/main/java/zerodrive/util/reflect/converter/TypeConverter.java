package zerodrive.util.reflect.converter;


/**
 * 文字列を別の型に変換するためのインタフェースです。
 * 
 * @author AdachiHjm
 * @created 2016/01/31 17:10:50
 *
 */
public interface TypeConverter<T> {

    /**
     * 指定された文字列を別の型に変換して返します。
     *
     * @param value String of a convert target
     * @return Converted object or null
     */
    T convert(String value);
}
