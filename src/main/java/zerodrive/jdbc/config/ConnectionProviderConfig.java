package zerodrive.jdbc.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;


public class ConnectionProviderConfig {
	//======================================================================
	// Fields
    private final String name;
	private final String driverClassName;
	private final String url;
	private final String user;
	private final String password;
	private final String vendor;
	private final Map<String, String> driverProperties;
	private final Map<String, String> providerProperties;


	//======================================================================
	// Constructors
	private ConnectionProviderConfig(String _name, String _driverClassName, String _url, String _user, String _password, String _vendor, Map<String, String> _driverProperties, Map<String, String> _providerProperties) {
	    this.name = _name;
		this.driverClassName = _driverClassName;
		this.url = _url;
		this.user = _user;
		this.password = _password;
		this.vendor = _vendor;
		this.driverProperties = Collections.unmodifiableMap(new HashMap<String, String>(_driverProperties));
		this.providerProperties = Collections.unmodifiableMap(new HashMap<String, String>(_providerProperties));

		if (null == this.name) {
		    // FIXME: i18n
		    throw new IllegalStateException("'name' must not be null.");
		}
		if (null == this.driverClassName) {
		    // FIXME: i18n
		    throw new IllegalStateException("'driverClassName' must not be null.");
		}
		if (null == this.url) {
		    // FIXME: i18n
		    throw new IllegalStateException("'url' must not be null.");
		}
		if (null == this.user) {
		    // FIXME: i18n
		    throw new IllegalStateException("'user' must not be null.");
		}
		if (null == this.password) {
		    // FIXME: i18n
		    throw new IllegalStateException("'password' must not be null.");
		}
		if (null == this.vendor) {
		    // FIXME: i18n
		    throw new IllegalStateException("'vendor' must not be null.");
		}
	}


	//======================================================================
	// Methods
    public static ConnectionProviderConfig load(InputStream input)
            throws IOException, InvalidConfigException {
        if (null == input) {
            // FIXME: i18n
            throw new NullPointerException("'input' must not be null.");
        }
        try {
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            XMLReader reader = parser.getXMLReader();

            ConfigHandler handler = new ConfigHandler();
            reader.setContentHandler(handler);
            reader.setEntityResolver(new ConfigEntityResolver());
            reader.parse(new InputSource(input));

            return new ConnectionProviderConfig(
                handler.name,
                handler.driverClassName,
                handler.url,
                handler.user,
                handler.password,
                handler.vendor,
                handler.driverProperties,
                handler.providerProperties);
        } catch (Exception e) {
            throw new InvalidConfigException(e);
        }
    }

	//======================================================================
	// Getter
    public String getName() {
        return this.name;
    }

	public String getDriverClassName() {
		return this.driverClassName;
	}

	public String getUrl() {
		return this.url;
	}

	public String getUser() {
		return this.user;
	}

	public String getPassword() {
		return this.password;
	}

	public String getVendor() {
		return this.vendor;
	}

	public Map<String, String> getDriverProperties() {
		return this.driverProperties;
	}

	public Map<String, String> getProviderProperties() {
		return this.providerProperties;
	}

	private static class ConfigHandler extends DefaultHandler {
	    private String name;
		private String driverClassName;
		private String url;
		private String user;
		private String password;
		private String vendor;
		private final Map<String, String> driverProperties = new HashMap<String, String>();
		private final Map<String, String> providerProperties = new HashMap<String, String>();
		private Map<String, String> current;

		@Override
		public void startElement(String uri, String localName, String qName, Attributes attributes)
				throws SAXException {
			if ("configuration".equals(qName)) {
                this.name = attributes.getValue("name");
			} else if ("driver".equals(qName)) {
				this.driverClassName = attributes.getValue("class");
				this.url = attributes.getValue("url");
				this.user = attributes.getValue("user");
				this.password = attributes.getValue("password");
				this.vendor = attributes.getValue("vendor");

				this.current = this.driverProperties;
			} else if ("provider".equals(qName)) {
				this.current = this.providerProperties;
			} else if ("property".equals(qName)) {
				this.current.put(attributes.getValue("name"), attributes.getValue("value"));
			}
		}
	}

	private static class ConfigEntityResolver implements EntityResolver {
        @Override
        public InputSource resolveEntity(String publicId, String systemId)
                throws SAXException, IOException {
            if (systemId.endsWith("connection-provider-config_1_0.dtd")) {
                return new InputSource(Thread.currentThread().getContextClassLoader().getResourceAsStream("connection-provider-config_1_0.dtd"));
            } else {
                return null;
            }
        }
	}
}
