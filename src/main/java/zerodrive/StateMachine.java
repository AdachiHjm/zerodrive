package zerodrive;

import java.util.List;


/**
 * TODO: JPA の EntityManager っぽいのを想定。
 * TODO: 状態機械とトークンの種類は 1：1 とする。→ いや、1：n も可能じゃね？
 * TODO: createToken() でトークンを作成。ID は自動採番。
 * TODO: createToken() されたトークンは状態機械の管理下(managed)に置かれる。
 * TODO: → new で生成したトークンは状態機械に管理されない。
 * TODO: Token が signal すると、Token→領域*n→状態機械の順に Token の変更が通知される？
 * TODO: 通知は、exit, input, enter など。
 *
 * @author AdachiHjm
 * @since 2009/01/09 16:29:49
 */
public interface StateMachine {
    //======================================================================
    // Getter
    /** @return この状態機械を一意に識別する文字列 */
    String getId();

    /** @return この状態機械の名称 */
    String getName();

    /** @return この状態機械に直接所有される１つ以上の領域の不変リスト */
    List<Region> getRegionList();

    /** @return この状態機械の開始状態 */
    Vertex getStart();


    //======================================================================
    // Methods
    /**
     * この状態機械のトークンを新規に作成します。
     * トークンの初期状態はこの状態機械の開始状態です。
     * このメソッドによって作成されたトークンは状態機械の管理下に置かれ、
     * トークンの更新がイベントとして状態機械に通知されます。
     *
     * @return 新しいトークン
     */
    <T extends Token> T createToken(Class<T> type);

    /**
     * 指定されたトークンの型と ID に該当するトークンを返します。
     * 該当するトークンが無い場合は null を返します。
     * 
     * @param type トークンの型
     * @param tokenId トークンを一意に識別可能な文字列
     * @return トークンのインスタンス、または null
     */
    <T extends Token> T findToken(Class<T> type, String tokenId);
}
