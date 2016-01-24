package zerodrive;

import java.util.List;

import zerodrive.behavior.ActionConfig;
import zerodrive.behavior.ActionExecutor;
import zerodrive.behavior.ActionFailedException;

/**
 * 節点(Vertex)は、状態機械グラフにおけるノードの抽象化です。
 * 節点は、一般的に任意の数の遷移(Transition)の遷移元または遷移先となります。
 *
 * @author AdachiHjm
 * @version 1.0.0
 *
 * Created On : 2009/08/06 1:37:29
 */
public interface Vertex {
    //======================================================================
    // Getter
    /**
     * @return この節点を一意に識別できる文字列
     */
    String getId();

    /**
     * @return この節点の名称
     */
    String getName();

    /**
     * @return この節点を所有する領域の不変リスト
     */
    List<Region> getContainerList();

    /**
     * @return この節点が所有するサブ状態機械
     */
    StateMachine getSubStateMachine();

    /**
     * @return この節点を遷移先とする遷移の不変マップ
     */
    List<Transition> getIncomings();

    /**
     * @return この節点を遷移元とする遷移の不変マップ
     */
    List<Transition> getOutgoings();

    List<ActionConfig> getEntryActionList();

    List<ActionConfig> getInputActionList();

    List<ActionConfig> getExitActionList();

    ActionExecutor getActionExecutor();


    //======================================================================
    // Methods
    /**
     * トークンがこの状態に入場します。
     * もし定義されていれば、
     * どのような遷移でこの節点に至ったかに関わらず、
     * いつでもトークンがこの節点に入ったときに必ず入場動作(ENTRY Action)が実行されます。
     *
     * @param token トークン
     * @param input 入力情報
     * @throws ActionFailedException 動作実行中の例外
     */
    void entry(Token token, Object input) throws ActionFailedException;

    /**
     * トークンがこの状態から退場します。
     * もし定義されていれば、
     * この節点がからどのような遷移が行われるかに関わらず、
     * この節点からトークンが出た時に必ず退場動作(EXIT Action)が実行されます。
     *
     * @param token トークン
     * @param input 入力情報
     * @return 状態遷移に成功したなら true、そうでないなら false
     * @throws TransitionNotFoundException 条件を満たす出力遷移が見つからない
     * @throws ActionFailedException 動作実行中の例外
     */
    boolean exit(Token token, Object input) throws TransitionNotFoundException, ActionFailedException;

    /**
     * 状態遷移を伴わない入力動作を実行します。
     *
     * @param token トークン
     * @param input 入力情報
     * @throws ActionFailedException 動作実行中の例外
     */
    void input(Token token, Object input) throws ActionFailedException;

    /**
     * この状態を詳細化しているサブ状態機械を呼び出します。
     * サブ状態機械のトークンは指定されたトークンを親とします。
     * 
     * @param token トークン
     * @param input 入力情報
     * @return サブ状態機械のトークン
     */
    Token include(Token token, Object input);
}
