
<%@ page import="com.google.appengine.api.datastore.*" %>
<%
	DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	String key = request.getParameter("keyname");
	String result = "not found";
	if (key != null && key != "") {
        try {
			result = (String) datastore.get(new Entity("TaskData", key).getKey()).getProperty("value");
		} catch (EntityNotFoundException e1) {
			result = "not found";
		}
	}
%>
<html>
<head>
<link type="text/css" rel="stylesheet" href="/stylesheets/main.css"/>
</head>
<body>
<p>The value in keyname <%= key.replaceAll("<", "&lt;").replaceAll(">", "&gt;") %> is <%= result.replaceAll("<", "&lt;").replaceAll(">", "&gt;") %>!</p>
</body>
</html>