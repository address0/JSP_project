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
<button onclick="location.href='<%= request.getContextPath() %>/user/logout.do'">로그아웃</button>
<nav>
	<ul>
		<li><a href="<%=request.getContextPath()%>/user/list.do">회원 관리</a></li>
		<li><a href="<%=request.getContextPath()%>/product/list.do">상품 관리</a></li>
		<li><a href="<%=request.getContextPath()%>/category/topList.do">카테고리 관리</a></li>
	</ul>
</nav>
<div class="manage-container">
<%
	String managePage = request.getParameter("managePage");
	if ("productManage".equals(managePage)) {;
%> <jsp:include page="productManage.jsp" /> <%
	} else if ("userManage".equals(managePage)) { %> <jsp:include page="userManage.jsp" /> <%
	} else { %> <jsp:include page="categoryManage.jsp" /> <%}
    %>
</div>
</body>
</html>
