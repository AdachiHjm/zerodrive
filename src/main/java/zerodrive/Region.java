package zerodrive;

import java.util.List;

/**
 * 
 * 状態と遷移を含む領域を表すインタフェースです。
 *
 * @author AdachiHjm
 * @version 1.0.0
 *
 * @create 2011/03/15 23:40:12
 */
public interface Region {
    /** @return 領域を一意に識別する文字列 */
    String getId();

    /** @return 領域名 */
    String getName();

    /** @return この領域を所有する状態機械 */
    StateMachine getStateMachine();

    /** @return この領域によって直接所有される節点 */
    List<Vertex> getVertexList();
}
