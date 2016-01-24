package zerodrive.config;

import zerodrive.StateMachine;


/**
 * @author AdachiHjm
 * @created 2015/07/03 1:50:08
 *
 */
public interface Configurator {

    /**
     * {@linkplain StateMachine} を構成します。
     * 
     * @return {@linkplain StateMachine}
     */
    StateMachine configure();
}
