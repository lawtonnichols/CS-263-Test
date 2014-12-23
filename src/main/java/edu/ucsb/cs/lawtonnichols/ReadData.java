package edu.ucsb.cs.lawtonnichols;

import java.io.IOException;
import java.util.Date;

import javax.servlet.*;
import javax.servlet.http.*;

import com.google.appengine.api.datastore.*;
import com.google.appengine.api.datastore.Query.*;

// The Worker servlet should be mapped to the "/worker" URL.
public class ReadData extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String output = "<html><body>";
        output += "<p>Hello, this is a testing servlet.</p>";
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        
        Query q = new Query("TaskData");
        PreparedQuery pq = datastore.prepare(q);
        for (Entity result : pq.asIterable()) {
        	  String value = (String) result.getProperty("value");
        	  value = value.toString().replaceAll("<", "&lt;").replaceAll(">", "&gt;");
        	  String name = (String) result.getKey().getName();
        	  name = name.toString().replaceAll("<", "&lt;").replaceAll(">", "&gt;");
        	  Date date = (Date) result.getProperty("date");
        	  output += "<p>TaskData(\"" + name + "\") " + date + " " + value + "</p>";
        }
        output += "</body></html>";
        response.getWriter().println(output);
    }
}
