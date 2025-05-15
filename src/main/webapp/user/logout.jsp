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
	<title>JOOZAG</title>
	<link rel="icon" type="image/png" sizes="96x96" href="<%=request.getContextPath()%>/favicon/favicon-96x96.png">
	<link rel="icon" type="image/png" sizes="32x32" href="<%=request.getContextPath()%>/favicon/favicon-32x32.png">
	<link rel="icon" type="image/png" sizes="16x16" href="<%=request.getContextPath()%>/favicon/favicon-16x16.png">
	<link rel="shortcut icon" href="<%=request.getContextPath()%>/favicon/favicon.ico">
	<link rel="stylesheet" href="<%=request.getContextPath()%>../css/user.css?v=<%=System.currentTimeMillis()%>">
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&family=Gowun+Dodum&family=Hahmlet:wght@100..900&family=Noto+Sans+KR:wght@100..900&family=Noto+Serif+KR:wght@200..900&display=swap" rel="stylesheet">
</head>
<body>
<div class="user-container">
  <h1>로그아웃 성공</h1>
  <p>로그아웃되었습니다. 다시 로그인 해주세요.</p>
  <button class="login-button" onclick="location.href='${pageContext.request.contextPath}/user/loginForm.do'">로그인 페이지로 돌아가기</button>
</div>
</body>
</html>
