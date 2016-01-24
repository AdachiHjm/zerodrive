package zerodrive;



/**
 * @author AdachiHjm
 * @createdOn 2011/08/16 21:53:25
 *
 */
public interface Trigger {

    /**
     * FIXME: トリガの説明...
     * 
     * @param token
     * @param input
     * @return
     */
	boolean fire(Token token, Object input);
}
