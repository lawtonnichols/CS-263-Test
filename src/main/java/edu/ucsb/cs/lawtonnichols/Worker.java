package edu.ucsb.cs.lawtonnichols;

import java.io.IOException;
import java.util.Date;

import javax.servlet.*;
import javax.servlet.http.*;

import com.google.appengine.api.datastore.*;

// The Worker servlet should be mapped to the "/worker" URL.
public class Worker extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String key = request.getParameter("key");
        String value = request.getParameter("value");
        // Do something with key.
        
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        Entity e = new Entity("TaskData", key);
        e.setProperty("value", value);
        e.setProperty("date", new Date());
        datastore.put(e);
        
        
    }
}
