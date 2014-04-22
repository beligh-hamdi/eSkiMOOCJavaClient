package tn.edu.esprit.client;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Locator {
 
	public static Object lookup(String moduleName, String serviceName,Class<?> viewClass) {
		 
		 Properties properties = new Properties();
		 
	     properties.put("java.naming.factory.url.pkgs", "org.jboss.ejb.client.naming");
		 properties.put("java.naming.factory.initial", "org.jboss.naming.remote.client.InitialContextFactory");
		 properties.put("java.naming.provider.url", "remote://localhost:4447");
		 properties.put("jboss.naming.client.ejb.context", "true");
		 properties.put("jboss.naming.client.connect.options.org.xnio.Options.SASL_POLICY_NOPLAINTEXT", "false");
		 	 
		try {
			Context  context = new InitialContext(properties);
			
			String viewClassName = viewClass.getCanonicalName();

			String jndiName = "ejb:/"+moduleName+"/"+serviceName+"!"+ viewClassName;
			 
			return context.lookup(jndiName);
	
		} catch (NamingException e) {
			
			e.printStackTrace();
		}
		 	 
		 return null;
	 }
	
}
