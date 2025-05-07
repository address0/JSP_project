<%--
  Created by IntelliJ IDEA.
  User: kopo
  Date: 2025-05-02
  Time: 오후 1:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Title</title>
</head>
<body>
<h1>Main page~</h1>
<%
	String id = request.getParameter("id");
	String name = request.getParameter("name");
%>
<p>Welcome, <%= id %>!</p>
<p>Your name is <%= name %>.</p>
</body>
</html>
