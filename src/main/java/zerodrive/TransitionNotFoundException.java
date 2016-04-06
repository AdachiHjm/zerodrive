package zerodrive;


/**
 * @author AdachiHjm
 * @created 2015/03/31 23:52:34
 */
public class TransitionNotFoundException extends StateMachineException {
    private static final long serialVersionUID = -2596110193988427002L;

    /**
     * 
     */
    public TransitionNotFoundException() {
        super();
    }

    /**
     * @param message
     */
    public TransitionNotFoundException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public TransitionNotFoundException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public TransitionNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     */
    public TransitionNotFoundException(String message, Throwable cause,
            boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
