package zerodrive.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.LinkedList;
import java.util.List;

import zerodrive.Region;
import zerodrive.StateMachine;
import zerodrive.Transition;
import zerodrive.behavior.ActionConfig;
import zerodrive.behavior.ActionConfig.ActionType;
import zerodrive.behavior.ActionExecutor;
import zerodrive.behavior.impl.SimpleActionExecutor;
import zerodrive.builder.VertexBuilder;

/**
 * このクラスは Vertex インタフェースのスケルトン実装を提供し、
 * このインタフェースを実装するのに必要な作業量を最小限に抑えます。
 *
 * @author AdachiHjm
 * @version 1.0.0
 *
 * @create 2010/05/26 20:17:21
 */
public abstract class AbstractVertex implements VertexBuilder {
    //======================================================================
    // Properties
    /** 節点を一意に識別できる文字列 */
    private String id;

    /** 節点名 */
    private String name;

    /** この節点を含む領域 */
    private final List<Region> containerList = new ArrayList<Region>();

    /** この節点が所有するサブ状態機械。null を許可する。 */
    private StateMachine subStateMachine;

    /** この節点に入ってくる遷移の不変リスト */
    private final List<Transition> incomings = new LinkedList<>();

    /** この節点から出て行く遷移の不変リスト */
    private final List<Transition> outgoings = new LinkedList<>();

    private final EnumMap<ActionType, List<ActionConfig>> actions = new EnumMap<ActionType, List<ActionConfig>>(ActionType.class);

    private ActionExecutor executor = new SimpleActionExecutor();


    //======================================================================
    // Constructors
    protected AbstractVertex() {
        for (ActionType actionType : ActionType.values()) {
            this.actions.put(actionType, new LinkedList<ActionConfig>());
        }
    }


    //======================================================================
    // Methods
    @Override
    public void addAction(ActionConfig config) {
        if (null == config) {
            // FIXME: i18n 対応
            throw new NullPointerException("'config' must not be null.");
        }
        this.actions.get(config.getActionType()).add(config);
    }


    //======================================================================
    // Getter
    /** {@inheritDoc} */
    @Override
    public final String getId() {
        return this.id;
    }

    /** {@inheritDoc} */
    @Override
    public final String getName() {
        return this.name;
    }

    /** {@inheritDoc} */
    @Override
    public final List<Region> getContainerList() {
        return Collections.unmodifiableList(this.containerList);
    }

    /** {@inheritDoc} */
    @Override
    public final StateMachine getSubStateMachine() {
        return this.subStateMachine;
    }

    /** {@inheritDoc} */
    @Override
    public final List<Transition> getIncomings() {
        return Collections.unmodifiableList(this.incomings);
    }

    /** {@inheritDoc} */
    @Override
    public final List<Transition> getOutgoings() {
        return Collections.unmodifiableList(this.outgoings);
    }

    @Override
    public List<ActionConfig> getActions(ActionType type) {
        if (null == type) {
            throw new NullPointerException("'type' must not be null.");
        }
        return Collections.unmodifiableList(this.actions.get(type));
    }

    protected ActionExecutor getExecutor() {
        return this.executor;
    }


    //======================================================================
    // Setter
    /** {@inheritDoc} */
    @Override
    public void addContainer(final Region container) {
        if (null == container) {
            throw new IllegalArgumentException("'container' doesn't allow null.");
        }
        this.containerList.add(container);
    }

    /** {@inheritDoc} */
    @Override
    public void addIncoming(final Transition incoming) {
        if (null == incoming) {
            throw new IllegalArgumentException("'incoming' doesn't allow null.");
        }
        this.incomings.add(incoming);
    }

    /** {@inheritDoc} */
    @Override
    public void addOutgoing(final Transition outgoing) {
        if (null == outgoing) {
            throw new IllegalArgumentException("'outgoing' doesn't allow null.");
        }
        this.outgoings.add(outgoing);
    }

    /** {@inheritDoc} */
    @Override
    public void setId(final String id) {
        if (null == id) {
            throw new IllegalArgumentException("'id' doesn't allow null.");
        }
        this.id = id;
    }

    /** {@inheritDoc} */
    @Override
    public void setName(String name) {
        if (null == name) {
            throw new IllegalArgumentException("'name' doesn't allow null.");
        }
        this.name = name;
    }

    /** {@inheritDoc} */
    @Override
    public void setSubStateMachine(StateMachine subStateMachine) {
        if (null == subStateMachine) {
            throw new IllegalArgumentException();
        }
        this.subStateMachine = subStateMachine;
    }
}
