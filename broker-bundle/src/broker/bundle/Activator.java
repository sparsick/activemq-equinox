package broker.bundle;

import org.apache.activemq.broker.BrokerService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

	@Override
	public void start(BundleContext context) throws Exception {
	      BrokerService broker = new BrokerService(); 
	        broker.setPersistent(false); 
	        broker.addConnector("tcp://0.0.0.0:61616"); 
	        broker.start(); 
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
