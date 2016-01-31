package zerodrive.util.reflect.converter;


/**
 * 文字列を {@linkplain Byte} に変換する {@linkplain TypeConverter} の実装です。
 * 
 * @author AdachiHjm
 * @created 2016/01/31 17:14:25
 *
 */
public class ByteConverter implements TypeConverter<Byte> {

    @Override
    public Byte convert(String value) {
        return null != value ? Byte.valueOf(value) : null;
    }

}
