<%--
  Created by IntelliJ IDEA.
  User: kopo
  Date: 2025-05-02
  Time: 오후 1:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Login Result</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>../css/user.css?v=<%=System.currentTimeMillis()%>">
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&family=Gowun+Dodum&family=Hahmlet:wght@100..900&family=Noto+Sans+KR:wght@100..900&family=Noto+Serif+KR:wght@200..900&display=swap" rel="stylesheet">
</head>
<body>
<div class="user-container">
	<%
		String loginResult = request.getParameter("result");
		if ("success".equals(loginResult)) {
	%>
		<h1>로그인 완료</h1>
		<p>환영합니다! 로그인에 성공했습니다.</p>
	<button onclick="location.href='<%= request.getContextPath() %>/modify?action=profile&id=<%= session.getAttribute("id") %>'">메인 페이지</button>
	<% } else if ("admin".equals(loginResult)) {
            session.setAttribute("manage", "userManage");
	%>
		<h1>로그인 완료</h1>
		<p>관리자님, 환영합니다! 관리자 페이지로 이동합니다.</p>
		<button onclick="location.href = '/userList'">관리자 페이지</button>
	<% } else if ("fail".equals(loginResult)) {
	%>
		<h1>미등록된 사용자</h1>
		<p>로그인에 실패했습니다. 사용자 정보를 확인하세요.</p>
	<% } else if ("inactive".equals(loginResult)) {
        	%>
		<h1>비활성화된 사용자</h1>
		<p>회원가입 승인 전입니다. 잠시만 기다려 주세요.</p>
		<button onclick="location.href = 'login.jsp'">로그인 페이지로</button>
	<% } else {
	%>
		<h1>로그인 실패</h1>
		<p>알 수 없는 오류가 발생했습니다. 다시 시도하세요.</p>
	<%
		}
	%>
</div>
</body>
</html>
