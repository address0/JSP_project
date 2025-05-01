<%--
  Created by IntelliJ IDEA.
  User: hyunj
  Date: 2025-04-26
  Time: 오후 5:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Landing Page</title>
    <script src="https://unpkg.com/@lottiefiles/lottie-player@latest/dist/lottie-player.js"></script>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&family=Gowun+Dodum&family=Hahmlet:wght@100..900&family=Noto+Sans+KR:wght@100..900&family=Noto+Serif+KR:wght@200..900&display=swap" rel="stylesheet">    <style>
        body {
            background-color: #F6D8C6;
            margin: 0;
            padding: 20px;
            display: flex;
            justify-content: center;
            align-items: center;
            font-family: "Gowun Dodum", serif;
            font-optical-sizing: auto;
            font-style: normal;
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
        src="images/Animation - 1745678753765.json"
        background="transparent"
        speed="1"
        style="width: 400px; height: 400px;"
        loop
        autoplay>
    </lottie-player>
    <script>
        document.addEventListener('DOMContentLoaded', function() {
            <%--var isLoggedIn = <%= isLoggedIn %>;--%>

            setTimeout(function() {
                window.location.href = 'login.jsp';
                <%--if (isLoggedIn) {--%>
                <%--    window.location.href = '<%= request.getContextPath() %>/home';--%>
                <%--} else {--%>
                <%--    window.location.href = '<%= request.getContextPath() %>/login';--%>
                <%--}--%>
            }, 4500);
        });
        document.addEventListener('DOMContentLoaded', function() {
            const text = "Welcome to our E-Commerce Platform!";
            const typingTarget = document.getElementById('text');
            let index = 0;

            function type() {
                if (index < text.length) {
                    typingTarget.textContent += text.charAt(index);
                    index++;
                    setTimeout(type, 100);
                }
            }

            type();
        });
    </script>

</body>
</html>
