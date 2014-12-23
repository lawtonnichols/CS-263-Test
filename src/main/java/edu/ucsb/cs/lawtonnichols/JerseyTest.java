package edu.ucsb.cs.lawtonnichols;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.google.appengine.api.datastore.*;

@Path("/jerseyws")
public class JerseyTest {
	@GET
	@Path("/test/{ip}")
	public String testMethod(@PathParam("ip") String ip) {
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Entity e = new Entity("Main", "main");
		e.setProperty("ip", ip);
		datastore.put(e);
	    return ip;
	}
	
	@GET
	@Path("/test2")
	public String testMethod2() {
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Entity main = null;
		try {
			main = datastore.get(KeyFactory.createKey("Main", "main"));
			String ip = (String) main.getProperty("ip");
			return ip;
		} catch (Exception e) {
		    return "this is a test";
		}
	}
}
