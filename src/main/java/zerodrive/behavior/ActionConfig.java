package zerodrive.behavior;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class ActionConfig {
	//======================================================================
	// Enums
    public static enum ActionType {
        /** 入場動作 */
        ENTRY,

        /** 入力動作 */
        INPUT,

        /** 退場動作 */
        EXIT
    }


    //======================================================================
	// Fields
	private final Class<?> actionClass;

	private final ActionType actionType;

	private final Map<String, String> properties;

	private final boolean once;


	//======================================================================
	// Constructors
	public ActionConfig(ActionType _actionType, Class<?> _actionClass, Map<String, String> _properties, boolean _once) {
	    this.actionType = _actionType;
		this.actionClass = _actionClass;
		this.properties = Collections.unmodifiableMap(new HashMap<String, String>(_properties));
		this.once = _once;

		// FIXME: エラーメッセージの i18n 対応
		if (null == this.actionType) {
		    throw new NullPointerException("'actionType' must not be null.");
		}
		if (null == this.actionClass) {
		    throw new NullPointerException("'actionClass' must not be null.");
		}
	}


	//======================================================================
	// Getter
	public ActionType getActionType() {
        return this.actionType;
    }

	public Class<?> getActionClass() {
		return this.actionClass;
	}

	public Map<String, String> getProperties() {
		return this.properties;
	}

    public boolean isOnce() {
        return this.once;
    }

}
