<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Signup</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/user.css?v=<%= System.currentTimeMillis() %>">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&family=Gowun+Dodum&family=Hahmlet:wght@100..900&family=Noto+Sans+KR:wght@100..900&family=Noto+Serif+KR:wght@200..900&display=swap" rel="stylesheet">
</head>
<body>
<div class="user-container">
    <h1>회원가입 페이지</h1>
    <hr />
    <form action="${pageContext.request.contextPath}/user/signup.do" method="post">
        <div>
            <label for="email">아이디(이메일): </label>
            <input type="text" id="email" name="email" placeholder="기본 이메일 입력: ex. abc@kopo.com" required autocomplete="off" />
            <p class="disabled">5 ~ 15자리의 영문자/숫자를 사용하여, 이메일 형식으로 작성해 주세요.</p>
        </div>
        <div>
            <label for="username">이름: </label>
            <input type="text" id="username" name="username" placeholder="사용자 이름 입력: ex. 홍길동" required autocomplete="off" />
            <p class="disabled">이름을 1글자 이상 입력해 주세요.</p>
        </div>
        <div>
            <label for="password">비밀번호: </label>
            <input type="password" id="password" name="password" placeholder="비밀번호 입력" required autocomplete="off" />
            <p class="disabled">5 ~ 15자리의 영문자(대/소문자) 1개 이상, 숫자 1개 이상 포함</p>
        </div>
        <div>
            <label for="confirm-password">비밀번호 확인: </label>
            <input type="password" id="confirm-password" name="confirm-password" placeholder="비밀번호 재입력" required autocomplete="off" />
            <p class="disabled">비밀번호와 일치하지 않습니다.</p>
        </div>
        <div>
            <label for="tel">전화번호: </label>
            <input type="text" id="tel" name="tel" placeholder="- 제외한 전화번호 입력: ex.01012345678" required autocomplete="off" />
            <p class="disabled">하이픈(-) 제외한 숫자 11자리 입력</p>
        </div>
        <input type="submit" class="login-button" value="회원가입">
    </form>
</div>
<script src="${pageContext.request.contextPath}/script/register.js"></script>
</body>
</html>
