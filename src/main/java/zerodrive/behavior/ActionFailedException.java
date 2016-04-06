package zerodrive.behavior;

import zerodrive.StateMachineException;
import zerodrive.Token;
import zerodrive.Transition;
import zerodrive.Vertex;


/**
 * @author AdachiHjm
 * @created 2015/03/31 23:51:25
 */
public class ActionFailedException extends StateMachineException {
    private static final long serialVersionUID = 4354165190988071458L;

    //======================================================================
    // Fields
    private final Token token;
    private final Vertex vertex;
    private final Transition transition;


    //======================================================================
    // Constructors
    /**
     * 
     */
    public ActionFailedException() {
        super();
        this.token = null;
        this.vertex = null;
        this.transition = null;
    }

    /**
     * @param message
     */
    public ActionFailedException(String message) {
        super(message);
        this.token = null;
        this.vertex = null;
        this.transition = null;
    }

    public ActionFailedException(String message, Token _token, Vertex _vertex) {
        super(message);
        this.token = _token;
        this.vertex = _vertex;
        this.transition = null;
    }

    public ActionFailedException(String message, Token _token, Transition _transition) {
        super(message);
        this.token = _token;
        this.vertex = null;
        this.transition = _transition;
    }

    /**
     * @param cause
     */
    public ActionFailedException(Throwable cause) {
        super(cause);
        this.token = null;
        this.vertex = null;
        this.transition = null;
    }

    /**
     * @param message
     * @param cause
     */
    public ActionFailedException(String message, Throwable cause) {
        super(message, cause);
        this.token = null;
        this.vertex = null;
        this.transition = null;
    }

    /**
     * @param message
     * @param cause
     * @param _token
     * @param _vertex
     */
    public ActionFailedException(String message, Throwable cause, Token _token, Vertex _vertex) {
        super(message, cause);
        this.token = _token;
        this.vertex = _vertex;
        this.transition = null;
    }

    /**
     * @param message
     * @param cause
     * @param _token
     * @param _transition
     */
    public ActionFailedException(String message, Throwable cause, Token _token, Transition _transition) {
        super(message, cause);
        this.token = _token;
        this.vertex = null;
        this.transition = _transition;
    }


    //======================================================================
    // Getter
    public Token getToken() {
        return this.token;
    }

    public Vertex getVertex() {
        return this.vertex;
    }

    public Transition getTransition() {
        return this.transition;
    }

}
