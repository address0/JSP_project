<%--
  Created by IntelliJ IDEA.
  User: kopo
  Date: 2025-05-02
  Time: 오후 1:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Admin Page</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/admin.css?v=<%=System.currentTimeMillis()%>">
</head>
<body>
<button onclick="location.href='<%= request.getContextPath() %>/user/logout.jsp'">로그아웃</button>
<nav>
	<ul>
		<li><a href="<%=request.getContextPath()%>/userList">회원 관리</a></li>
		<li><a href="<%=request.getContextPath()%>/admin/manage.jsp?managePage=productManage">상품 관리</a></li>
	</ul>
</nav>
<div class="manage-container">
<%
	String managePage = request.getParameter("managePage");
	if ("productManage".equals(managePage)) {;
%> <jsp:include page="productManage.jsp" /> <%
	} else { %> <jsp:include page="userManage.jsp" /> <%
	}
    %>
</div>
</body>
</html>
