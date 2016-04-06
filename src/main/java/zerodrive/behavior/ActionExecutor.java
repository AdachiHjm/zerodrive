package zerodrive.behavior;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import zerodrive.Token;
import zerodrive.Transition;
import zerodrive.Vertex;


public abstract class ActionExecutor {

    /**
     * 節点の動作を実行します。
     * 
     * @param token トークン
     * @param input 入力情報
     * @param vertex 節点
     * @param config 動作構成
     * @throws ActionFailedException 動作実行中の例外
     */
	public void execute(Token token, Object input, Vertex vertex, ActionConfig config)
	        throws ActionFailedException {
	    try {
	        Object action = this.create(config);

	        Method method = this.findActionMethod(action);
	        if (null != method) {
                this.invoke(action, method, token, input, vertex);
	        } else {
	            // FIXME: エラーメッセージの i18n 対応
	            throw new ActionFailedException("action method not found.", token, vertex);
	        }
	    } catch (Exception e) {
            // FIXME: エラーメッセージの i18n 対応
            throw new ActionFailedException("action method not found.", e, token, vertex);
	    }
	}

	/**
	 * 遷移動作を実行します。
	 * 
	 * @param token トークン
	 * @param input 入力情報
	 * @param transition 遷移
	 * @param config 動作構成
	 * @throws ActionFailedException 動作実行中の例外
	 */
	public void execute(Token token, Object input, Transition transition, ActionConfig config)
	        throws ActionFailedException {
	    try {
	        Object action = this.create(config);

	        Method method = this.findActionMethod(action);
	        if (null != method) {
                this.invoke(action, method, token, input, transition);
	        } else {
	            // FIXME: エラーメッセージの i18n 対応
	            throw new ActionFailedException("action method not found.", token, transition);
	        }
	    } catch (Exception e) {
            // FIXME: エラーメッセージの i18n 対応
            throw new ActionFailedException("action method not found.", e, token, transition);
	    }
	}

	protected abstract Object create(ActionConfig config) throws Exception;

    protected void invoke(Object action, Method method, Object... params)
            throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Class<?>[] paramTypes = method.getParameterTypes();
        Object[] args = new Object[paramTypes.length];
        TYPES: for (int i = 0, length = paramTypes.length; i < length; i++) {
            for (Object param : params) {
                if (null != param) {
                    if (paramTypes[i].isAssignableFrom(param.getClass())) {
                        args[i] = param;
                        continue TYPES;
                    }
                }
            }
        }

        method.invoke(action, args);
    }

    private Method findActionMethod(Object action) {
        for (Class<?> type = action.getClass(); !Object.class.equals(type); type = type.getSuperclass()) {
            Method[] methods = type.getMethods();
            for (Method method : methods) {
                if (null != method.getAnnotation(Action.class)) {
                    return method;
                }
            }
        }
        return null;
    }
}
