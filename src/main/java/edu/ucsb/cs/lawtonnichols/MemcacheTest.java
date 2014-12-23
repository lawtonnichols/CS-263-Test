package edu.ucsb.cs.lawtonnichols;

import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;

import javax.servlet.*;
import javax.servlet.http.*;

import com.google.appengine.api.datastore.*;
import com.google.appengine.api.datastore.Query.*;
import com.google.appengine.api.memcache.*;

// The Worker servlet should be mapped to the "/worker" URL.
public class MemcacheTest extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String output = "<html><body>";
        output += "<p>Hello, this is a memcache testing servlet. Refresh the page and watch the counter eventually increase!</p>";
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        
        output += "<p>";
        
        MemcacheService syncCache = MemcacheServiceFactory.getMemcacheService();
        syncCache.setErrorHandler(ErrorHandlers.getConsistentLogAndContinue(Level.INFO));
        Integer value = (Integer) syncCache.get("num"); // read from cache
        if (value == null) {
          value = 0;
          syncCache.put("num", value); // populate cache
        } else {
        	value++;
        	syncCache.put("num", value);
        	value--;
        }
        
        output += "value: " + value;
        
        output += "</p>";
        output += "</body></html>";
        response.getWriter().println(output);
    }
}
