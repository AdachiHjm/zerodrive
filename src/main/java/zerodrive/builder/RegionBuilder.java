package zerodrive.builder;

import zerodrive.Region;
import zerodrive.StateMachine;
import zerodrive.Vertex;
import zerodrive.behavior.ActionExecutor;

/**
 * 
 *
 * @author AdachiHjm
 * @version 1.0.0
 *
 * @create 2011/03/16 0:05:31
 */
public interface RegionBuilder extends Region {

    /**
     * 指定された節点をこの領域に追加します。引数に null を許可しません。
     * 
     * @param subVertex この領域によって所有される節点
     */
    void addSubVertex(Vertex subVertex);


    //======================================================================
    // Setter
    /** @param 領域を一意に識別する文字列 */
    void setId(String _id);

    /** @param 領域名 */
    void setName(String _name);

    /** @param stateMachine この領域を所有する状態機械 */
    void setStateMachine(StateMachine _stateMachine);

    /** @param _executor {@linkplain ActionExecutor} */
    void setActionExecutor(ActionExecutor _executor);
}
