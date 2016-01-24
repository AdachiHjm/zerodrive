package zerodrive.util.logging;


public class Level extends java.util.logging.Level {
    private static final long serialVersionUID = 1L;

    private static final String DEFAULT_BUNDLE_NAME = "zerodrive.util.logging.zerodrive-logging";

    protected Level(String name, int value) {
        super(name, value, DEFAULT_BUNDLE_NAME);
    }

    protected Level(String name, int value, String resourceBundleName) {
        super(name, value, resourceBundleName);
    }

    public static final Level FATAL = new Level("FATAL", 975);

    public static final Level ERROR = new Level("ERROR", 950);

    public static final Level DEBUG = new Level("DEBUG", 600);
}
