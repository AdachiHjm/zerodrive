package zerodrive;



/**
 * ガード条件は、遷移の発火にきめ細かい制御条件を提供します。
 * ガード条件は、状態機械によってイベント発生が処置された時に評価されます。
 * その際に、ガード条件が真なら遷移は有効であり、偽なら無効となります。
 * ガード条件は、副作用のない純粋式であるべきです。
 *
 * @author AdachiHjm
 * @version 1.0.0
 *
 * @create 2009/08/11 1:25:49
 */
public interface Guard {

    /**
     * 指定された入力情報とトークンでガード条件を評価します。
     * ガード条件を満たすなら true、そうでないなら false を返します。
     * 
     * @param token トークン
     * @param input 入力情報
     * @return ガード条件を満たすなら true、そうでないなら false
     */
    boolean accept(Token token, Object input);
}
