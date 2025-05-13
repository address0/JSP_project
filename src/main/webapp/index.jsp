<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String ctx = request.getContextPath();
    boolean isLoggedIn = session.getAttribute("id") != null;
%>
<html>
<head>
    <title>JOOZAG</title>
    <script src="https://unpkg.com/@lottiefiles/lottie-player@latest/dist/lottie-player.js"></script>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&family=Gowun+Dodum&family=Hahmlet:wght@100..900&family=Noto+Sans+KR:wght@100..900&family=Noto+Serif+KR:wght@200..900&display=swap" rel="stylesheet">
    <link rel="icon" type="image/png" sizes="96x96" href="<%=request.getContextPath()%>/favicon/favicon-96x96.png">
    <link rel="icon" type="image/png" sizes="32x32" href="<%=request.getContextPath()%>/favicon/favicon-32x32.png">
    <link rel="icon" type="image/png" sizes="16x16" href="<%=request.getContextPath()%>/favicon/favicon-16x16.png">
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/favicon/favicon.ico">
    <style>
        body {
            background-color: #F6D8C6;
            margin: 0;
            padding: 20px;
            display: flex;
            justify-content: center;
            align-items: center;
            font-family: "Gowun Dodum", serif;
        }
        .head-container {
            width: 40%;
        }
        h1 {
            font-size: 48px;
            color: #B88E2F;
        }
        h2 {
            display: flex;
            align-items: center;
        }
        #text {
            font-size: 28px;
            color: #B88E2F;
        }
        .head-container > p {
            font-size: 16px;
            color: #333333;
        }
        #cursor {
            display: inline-block;
            width: 6px;
            height: 24px;
            background-color: #686868;
            margin-left: 2px;
            animation: blink 0.7s infinite;
            vertical-align: middle;
        }
        @keyframes blink {
            0% { opacity: 1; }
            50% { opacity: 0; }
            100% { opacity: 1; }
        }
    </style>
</head>
<body>
<div class="head-container">
    <h1>E-Commerce</h1>
    <h2 id="typing-text"><span id="text"></span><span id="cursor"></span></h2>
    <p>E-Commerce 페이지 접속을 환영합니다. 잠시만 기다려 주세요..</p>
</div>
<lottie-player
        src="images/landing.json"
        background="transparent"
        speed="1"
        style="width: 400px; height: 400px;"
        loop
        autoplay>
</lottie-player>

<script>
    document.addEventListener('DOMContentLoaded', function () {
        setTimeout(function () {
            <%--const target = '<%= isLoggedIn ? ctx + "/home.do" : ctx + "/user/loginForm.do" %>';--%>
            window.location.href = '<%= request.getContextPath() %>/main.do';
        }, 2000);
    });
    
    document.addEventListener('DOMContentLoaded', function () {
        const text = "Welcome to our E-Commerce Platform!";
        const typingTarget = document.getElementById('text');
        let index = 0;
        
        function type() {
            if (index < text.length) {
                typingTarget.textContent += text.charAt(index);
                index++;
                setTimeout(type, 50);
            }
        }
        
        type();
    });
</script>
</body>
</html>
