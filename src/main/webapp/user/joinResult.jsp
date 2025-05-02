<%--
  Created by IntelliJ IDEA.
  User: kopo
  Date: 2025-05-02
  Time: 오후 1:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Join Result</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>../css/user.css?v=<%=System.currentTimeMillis()%>">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&family=Gowun+Dodum&family=Hahmlet:wght@100..900&family=Noto+Sans+KR:wght@100..900&family=Noto+Serif+KR:wght@200..900&display=swap" rel="stylesheet">
</head>
<body>
<div class="user-container">
    <%
        String result = request.getParameter("result");
        if (result == null) result = "none";
    %>
    <h1>회원가입 신청 결과</h1>
    <%
        if (result.equals("success")) {
    %>
    <p>회원가입 신청을 완료했습니다.</p>
    <p><a href="login.jsp">로그인 하러 가기</a></p>
    <%
    } else if (result.equals("fail")) {
    %>
    <p>회원가입에 실패했습니다.</p>
    <p><a href="join.jsp">다시 시도하기</a></p>
    <%
    } else if (result.equals("duplicate")) {
    %>
    <p>이미 가입된 이메일입니다.</p>
    <p><a href="join.jsp">다시 시도하기</a></p>
    <%
    } else if (result.equals("error")) {
    %>
    <p>서버 오류가 발생했습니다. 관리자에게 문의하세요.</p>
    <p><a href="join.jsp">다시 시도하기</a></p>
    <%
    } else {
    %>
    <p>알 수 없는 오류가 발생했습니다.</p>
    <p><a href="join.jsp">다시 시도하기</a></p>
    <%
        }
    %>
</div>
</body>
</html>
