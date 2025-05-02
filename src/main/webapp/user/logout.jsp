<%--
  Created by IntelliJ IDEA.
  User: kopo
  Date: 2025-05-02
  Time: 오후 8:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Logout</title>
</head>
<body>
<%
  session.invalidate();
%>
<div class="container">
  <h1>로그아웃 성공</h1>
  <p>로그아웃되었습니다. 다시 로그인 해주세요.</p>
  <button onclick="location.href='login.jsp'">로그인 페이지로 돌아가기</button>
</div>
</html>
