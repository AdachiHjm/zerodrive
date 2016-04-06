package zerodrive.util.logging;


public class Level extends java.util.logging.Level {
    private static final long serialVersionUID = 1L;

    private static final String DEFAULT_BUNDLE_NAME = "zerodrive.util.logging.zerodrive-logging";

    protected Level(String name, int value, String resourceBundleName) {
        super(name, value, resourceBundleName);
    }

    public static final java.util.logging.Level FATAL = new Level("FATAL", 975, DEFAULT_BUNDLE_NAME);
    public static final java.util.logging.Level ERROR = new Level("ERROR", 950, DEFAULT_BUNDLE_NAME);
    public static final java.util.logging.Level DEBUG = new Level("DEBUG", 600, DEFAULT_BUNDLE_NAME);

}
