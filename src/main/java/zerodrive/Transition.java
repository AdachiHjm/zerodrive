package zerodrive;

import zerodrive.behavior.ActionFailedException;

/**
 * 遷移を表すインタフェースです。
 * 遷移(Transition)は、遷移元節点と遷移先節点の間の有向関係です。
 * 1 つの状態構成から他の状態構成へ状態機械を遷移させます。
 *
 * @author AdachiHjm
 * @version 1.0.0
 *
 * @create 2011/03/12 16:12:43
 */
public interface Transition {
    /**
     * トークンを遷移先の状態へ遷移させます。
     * 定義されている場合は遷移動作が実行されます。
     * 
     * @param token トークン
     * @param input 入力情報
     * @throws ActionFailedException 遷移動作を実行中の例外
     */
    void effect(Token token, Object input) throws ActionFailedException;

    /** @return 遷移を一意に識別する文字列 */
    String getId();

    /** @retrun 遷移名 */
    String getName();

    /** @return この遷移を所有する状態機械 */
    StateMachine getStateMachine();

    /** @return 遷移元の節点 */
    Vertex getSource();

    /** @return 遷移先の節点 */
    Vertex getTarget();
}
