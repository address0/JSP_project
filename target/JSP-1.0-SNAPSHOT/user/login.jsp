<%--
  Created by IntelliJ IDEA.
  User: hyunj
  Date: 2025-04-27
  Time: 오후 2:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
<%--    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/user.css">--%>
    <link rel="stylesheet" href="<%=request.getContextPath()%>../css/user.css?v=<%=System.currentTimeMillis()%>">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap" rel="stylesheet">
</head>
<body>
    <div class="user-container">
        <h1>로그인 페이지</h1>
        <hr />
        <form action="<%= request.getContextPath() %>/login" method="post">
            <div>
                <label for="email">아이디(이메일): </label>
                <input type="text" id="email" name="email" placeholder="가입 이메일 입력: ex. abc@kopo.com" required />
            </div>
            <div>
                <label for="password">비밀번호: </label>
                <input type="password" id="password"    name="password" placeholder="비밀번호 입력" required />
            </div>
            <div class="button-container">
                <button type="submit" class="login-button">로그인</button>
                <button type="button" class="register-button" onclick="location.href='join.jsp'">회원가입</button>
            </div>
        </form>
    </div>
</body>
</html>
