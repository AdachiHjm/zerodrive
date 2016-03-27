package zerodrive;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

import org.apache.commons.collections4.trie.PatriciaTrie;

import zerodrive.behavior.ActionFailedException;


/**
 * トークン(Token)は、状態機械によって状態管理される対象を表すインタフェースです。
 *
 * @author AdachiHjm
 * @version 1.0.0
 *
 * @create 2009/08/06 1:31:26
 */
public class Token extends Observable implements Serializable {
    private static final long serialVersionUID = 5744659537128648191L;

    public static enum State {
        /** 開始されていない状態 */
        NEW,

        /** 状態遷移を実行中 */
        RUNNING,

        /** 遷移を待機中 */
        WAITING,

        /** 状態機械を終了 */
        FINISHED
    }

    //======================================================================
    // Fields
    // FIXME: 辿ってきた節点をスタック(Deque)に保持する。分岐した場合は、分岐元が辿った履歴を継承する。

    private final String id;

	private final Token parent;

	private final TokenGroup group;

	private final StateMachine stateMachine;

	private final Map<String, Token> children = new PatriciaTrie<Token>();

	private Vertex state;

	private Transition transition;


    //======================================================================
    // Constructors
	protected Token(String _id, StateMachine _stateMachine, TokenGroup _group) {
        this.id = _id;
        this.parent = null;
        this.stateMachine = _stateMachine;
        this.group = _group;

        if (null == this.id) {
            throw new NullPointerException("'id' must not be null.");
        }
        if (null == this.stateMachine) {
            throw new NullPointerException("'stateMachine' must not be null.");
        }
        if (null == this.group) {
            throw new NullPointerException("'group' must not be null.");
        }
	}

	protected Token(String _id, Token _parent) {
		this.id = _id;
		this.parent = _parent;
		if (null == this.id) {
			throw new NullPointerException("'id' must not be null.");
		}
		if (null == this.parent) {
			throw new NullPointerException("'parent' must not be null.");
		}

		this.stateMachine = this.parent.stateMachine;
		this.group = this.parent.group;
        this.parent.addChild(this);
	}


    //======================================================================
    // Methods
	/**
	 * 
	 * @param event イベント
	 * @return 状態遷移に成功したなら true、そうでないなら false
	 * @throws TransitionNotFoundException 遷移が見つからない
	 * @throws ActionFailedException 動作の実行に失敗
	 */
    public boolean signal(Object input) throws TransitionNotFoundException, ActionFailedException {
    	if (null == this.state) {
    		throw new IllegalStateException("'state' must not be null.");
    	}

    	return this.state.exit(this, input);
    }

    public void input(Object input) throws ActionFailedException {
        // FIXME: 未実装！
        throw new UnsupportedOperationException();
    }

    protected void addChild(Token child) {
    	if (null == child) {
    		throw new NullPointerException("'child' must not be null.");
    	}

    	this.children.put(child.id, child);
    }


    //======================================================================
    // Getter
    /** @return トークンを一意に識別できる文字列 */
    public String getId() {
		return this.id;
	}

    /** @return 親トークンを一意に識別できる文字列 */
    public Token getParent() {
		return this.parent;
	}

    /** @return このトークンが所属する状態機械 */
    public StateMachine getStateMachine() {
		return this.stateMachine;
	}

    /** @return このトークンの現在の状態 */
    public Vertex getState() {
		return this.state;
	}

    /** @return このトークンが最後に通過した遷移 */
    public Transition getTransition() {
        return this.transition;
    }

    /** @return このトークンを親とする子トークンの不変リスト */
    public Map<String, Token> getChildren() {
		return Collections.unmodifiableMap(new HashMap<String, Token>(this.children));
	}


    //======================================================================
    // Setter
    /** @param state このトークンの現在の状態として設定する節点 */
    public void setState(Vertex _state) {
		this.state = _state;
	}

    public void setTransition(Transition _transition) {
        this.transition = _transition;
    }
}
