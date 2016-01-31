package zerodrive.util.logging.filter;

import java.util.logging.Filter;
import java.util.logging.LogRecord;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * This class is a {@linkplain Filter} implementation that controls the log by regular expression.
 *
 * @author AdachiHjm
 * @created 2016/01/24 17:52:54
 */
public class RegexFilter implements Filter {
    //======================================================================
    // Fields
    private Pattern pattern;


    //======================================================================
    // Methods
    @Override
    public boolean isLoggable(LogRecord record) {
        if (null == this.pattern) {
            throw new IllegalStateException("pattern must not be null.");
        }
        String message = record.getMessage();
        if (null == message) {
            return true;
        } else {
            Matcher matcher = this.pattern.matcher(message);
            return matcher.matches();
        }
    }

    public void setPattern(String pattern) {
        this.pattern = Pattern.compile(null != pattern ? pattern : "^.*$");
    }
}
