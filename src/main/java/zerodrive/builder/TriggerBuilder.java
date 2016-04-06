package zerodrive.builder;

import zerodrive.Guard;
import zerodrive.Transition;
import zerodrive.Trigger;

/**
 * @author AdachiHjm
 * @createdOn 2011/09/09 1:00:29
 *
 */
public interface TriggerBuilder extends Trigger {

    /**
     * @param eventName イベント名
     */
    void setEventName(String eventName);

    /**
     * @param guard ガード条件
     * @see zerodrive.Trigger#addGuard(zerodrive.Guard)
     */
    void addGuard(Guard guard);

    /**
     * 
     * @param transition このトリガによって発火される遷移
     */
    void setTransition(Transition transition);
}
