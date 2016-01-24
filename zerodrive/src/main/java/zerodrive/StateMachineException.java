package zerodrive;

public class StateMachineException extends Exception {
    private static final long serialVersionUID = 5556430486363629751L;

    public StateMachineException() {
        super();
    }

    public StateMachineException(String message) {
        super(message);
    }

    public StateMachineException(Throwable cause) {
        super(cause);
    }

    public StateMachineException(String message, Throwable cause) {
        super(message, cause);
    }

    public StateMachineException(String message, Throwable cause,
            boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
