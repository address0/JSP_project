<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JOOZAG</title>
    <link rel="icon" type="image/png" sizes="96x96" href="<%=request.getContextPath()%>/favicon/favicon-96x96.png">
    <link rel="icon" type="image/png" sizes="32x32" href="<%=request.getContextPath()%>/favicon/favicon-32x32.png">
    <link rel="icon" type="image/png" sizes="16x16" href="<%=request.getContextPath()%>/favicon/favicon-16x16.png">
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/favicon/favicon.ico">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/user.css?v=<%= System.currentTimeMillis() %>">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap" rel="stylesheet">
</head>
<body>
<div class="user-container">
    <img src="<%= request.getContextPath() %>/images/JOOZAG_icon.png" alt="icon" id="icon" />
    <img src="<%= request.getContextPath() %>/images/logo.png" alt="logo" id="logo" />
<%--    <h1>로그인 페이지</h1>--%>
<%--    <hr />--%>
    <form action="<%= request.getContextPath() %>/user/login.do" method="post">
        <div>
            <label for="email">아이디(이메일): </label>
            <input type="text" id="email" name="email" placeholder="가입 이메일 입력: ex. abc@kopo.com" required />
        </div>
        <div>
            <label for="password">비밀번호: </label>
            <input type="password" id="password" name="password" placeholder="비밀번호 입력" required />
        </div>
        <div class="button-container">
            <button type="submit" class="login-button">로그인</button>
            <button type="button" class="register-button"
                    onclick="location.href='<%= request.getContextPath() %>/user/join.do'">회원가입</button>
        </div>
    </form>
</div>
</body>
</html>
