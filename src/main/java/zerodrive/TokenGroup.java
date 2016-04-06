package zerodrive;

import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.collections4.trie.PatriciaTrie;


/**
 * トークングループはトークンの集合を表します。
 * トークンは、それ自体のトークングループについての情報にアクセスすることを許可されていますが、
 * その他のトークングループについての情報にアクセスすることはできません。
 * 
 * @author AdachiHjm
 * @created 2015/03/27 21:49:05
 *
 */
public class TokenGroup implements Observer {
	//======================================================================
	// Fields
	private static final AtomicLong ID = new AtomicLong(0L);
	private final String id;
	private final Map<String, Token> tokens = new PatriciaTrie<Token>();


	//======================================================================
	// Constructors
	public TokenGroup() {
        this.id = System.currentTimeMillis() + "-" + ID.getAndIncrement();
	}


	//======================================================================
	// Methods
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}


	//======================================================================
	// Getter
	public String getId() {
        return this.id;
    }
}
