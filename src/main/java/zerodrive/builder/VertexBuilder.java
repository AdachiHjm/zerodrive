package zerodrive.builder;

import java.util.List;

import zerodrive.Region;
import zerodrive.StateMachine;
import zerodrive.Transition;
import zerodrive.Vertex;
import zerodrive.behavior.ActionConfig;
import zerodrive.behavior.ActionConfig.ActionType;
import zerodrive.behavior.ActionExecutor;

/**
 * 節点を構築するためのインタフェースです。
 *
 * @author AdachiHjm
 * @version 1.0.0
 *
 * @create 2011/02/15 0:59:50
 */
public interface VertexBuilder extends Vertex {

    //======================================================================
    // Setters
    /**
     * @param id この節点を一意に識別できる文字列。null は許可しない。
     */
    void setId(final String id);

    /**
     * @param name この節点の名称。null は許可しない。
     */
    void setName(final String name);

    /**
     * @param subStateMachine この節点が所有するサブ状態機械
     */
    void setSubStateMachine(final StateMachine subStateMachine);


    //======================================================================
    // Getters
    List<ActionConfig> getActions(ActionType type);


    //======================================================================
    // Methods
    /**
     * @param container この節点を所有する領域
     */
    void addContainer(final Region container);

    /**
     * @param incoming この節点を遷移先とする遷移
     */
    void addIncoming(final Transition incoming);

    /**
     * @param outgoing この節点を遷移元とする遷移
     */
    void addOutgoing(final Transition outgoing);

    /**
     * @param config 入場動作の定義
     */
    void addAction(ActionConfig config);

    /**
     * @param executor {@linkplain ActionExecutor}
     */
    void setActionExecutor(ActionExecutor executor);
}
