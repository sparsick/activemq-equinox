package broker.service.test;

import java.util.Dictionary;
import java.util.Hashtable;

import org.apache.log4j.Logger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;

public class Activator implements BundleActivator {
	
	private static final Logger LOG = Logger.getLogger("BrokerConfigLogger");

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		try {
		LOG.info("Start broker test");
		Activator.context = bundleContext;
		
		
		ServiceReference<ConfigurationAdmin> configAdminServiceReference = bundleContext.getServiceReference(ConfigurationAdmin.class);
		
		
		ConfigurationAdmin configAdmin = bundleContext.getService(configAdminServiceReference);
		Configuration config = configAdmin.createFactoryConfiguration("org.apache.activemq.server");
		
		Dictionary<String, String> configProps = new Hashtable<>();
		configProps.put("config", "path/to/your/activemq.xml");
		configProps.put("broker-name", "test-broker");
		configProps.put("data", "path/to/your/activemq-data");
		
		config.update(configProps);
		LOG.info("Config Props" + configProps);
		} catch(Exception e) {
			LOG.error("Exceptions:", e);
			throw e;
		}
		
		
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}

}
