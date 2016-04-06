package zerodrive.builder;

import zerodrive.Region;
import zerodrive.StateMachine;
import zerodrive.Vertex;

/**
 * @author AdachiHjm
 * @createdOn 2011/08/23 0:07:38
 *
 */
public interface StateMachineBuilder extends StateMachine {

    /** @param id この状態機械を一意に識別する文字列 */
    void setId(final String id);

    /** @param name この状態機械の名称 */
    void setName(final String name);

    /** @param start この状態機械の開始状態 */
    void setStart(final Vertex start);

    /** @param region この状態機械に直接所有される領域 */
    void addRegion(Region region);
}
