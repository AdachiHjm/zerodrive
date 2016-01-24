package zerodrive.util.logging;

import java.text.MessageFormat;
import java.util.logging.Handler;
import java.util.logging.LogRecord;


/**
 * @author AdachiHjm
 * @created 2015/11/19 23:17:52
 *
 */
public class Logger {
    private final java.util.logging.Logger origin;


    //======================================================================
    // Constructors
    private Logger(String name) {
        this.origin = java.util.logging.Logger.getLogger(name);
    }

    private Logger(String name, String resourceBundleName) {
        this.origin = java.util.logging.Logger.getLogger(name, resourceBundleName);
    }


    //======================================================================
    // Methods
    public static Logger getLogger(Class<?> cls) {
        return new Logger(cls.getName());
    }

    public static Logger getLogger(String name) {
        return new Logger(name);
    }

    public static Logger getLogger(Class<?> cls, String resourceBundleName) {
        return new Logger(cls.getName(), resourceBundleName);
    }

    public static Logger getLogger(String name, String resourceBundleName) {
        return new Logger(name, resourceBundleName);
    }

    public void addHandler(Handler handler) {
        this.origin.addHandler(handler);
    }

    public void info(String msg) {
        this.origin.log(this.newRecord(Level.INFO, null, msg));
    }

    public void info(Throwable thrown, String msg) {
        this.origin.log(this.newRecord(Level.INFO, thrown, msg));
    }

    public void info(String msg, Object firstParam, Object... remainingParams) {
        this.origin.log(this.newRecord(Level.INFO, null, this.buildMsg(msg, firstParam, remainingParams)));
    }





    private LogRecord newRecord(java.util.logging.Level level, Throwable thrown, String msg) {
        final LogRecord record = new LogRecord(level, msg);
        record.setThrown(thrown);

        StackTraceElement[] frames = new Throwable().getStackTrace();
        for (int i = 0; i < frames.length && i < 10; i++) {
            String className = frames[i].getClassName();
            if (!className.equals(this.getClass().getName()) &&
                !className.equals("java.util.logging.Logger") &&
                !className.startsWith("java.util.logging.LoggingProxyImpl") &&
                !className.startsWith("sun.util.logging.")) {
                record.setSourceClassName(className);
                record.setSourceMethodName(frames[i].getMethodName());
            }
        }
        return record;
    }

    private String buildMsg(String msg, Object firstParam, Object... remainingParams) {
        Object[] arguments = new Object[remainingParams.length + 1];
        arguments[0] = firstParam;
        for (int i = 0, length = remainingParams.length; i < length; i++) {
            arguments[i + 1] = remainingParams[i];
        }

        return MessageFormat.format(msg, arguments);
    }
}
