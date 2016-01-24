package zerodrive.builder;

import zerodrive.StateMachine;
import zerodrive.Transition;
import zerodrive.Vertex;
import zerodrive.behavior.ActionExecutor;

/**
 * @author AdachiHjm
 * @version 1.0.0
 *
 * @create 2011/03/12 23:29:04
 */
public interface TransitionBuilder extends Transition {

    /** @param id 遷移を一意に識別する文字列 */
    void setId(String id);

    /** @param name 遷移名 */
    void setName(String name);

    /** @param source 遷移元の節点 */
    void setSource(Vertex source);

    /** @param target 遷移先の節点 */
    void setTarget(Vertex target);

    /** @param stateMachine 遷移を所有する状態機械 */
    void setStateMachine(StateMachine stateMachine);

    /** @param executor ActionExecutor */
    void setActionExecutor(ActionExecutor executor);
}
