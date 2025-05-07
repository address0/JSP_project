<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Join Result</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/user.css?v=<%=System.currentTimeMillis()%>">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&family=Gowun+Dodum&family=Hahmlet:wght@100..900&family=Noto+Sans+KR:wght@100..900&family=Noto+Serif+KR:wght@200..900&display=swap" rel="stylesheet">
</head>
<body>
<div class="user-container">
    <h1>회원가입 신청 결과</h1>
    
    <c:choose>
        <c:when test="${param.result eq 'success'}">
            <p>회원가입 신청을 완료했습니다.</p>
            <p><a href="${pageContext.request.contextPath}/user/loginForm.do">로그인 하러 가기</a></p>
        </c:when>
        <c:when test="${param.result eq 'fail'}">
            <p>회원가입에 실패했습니다.</p>
            <p><a href="${pageContext.request.contextPath}/user/join.do">다시 시도하기</a></p>
        </c:when>
        <c:when test="${param.result eq 'duplicate'}">
            <p>이미 가입된 이메일입니다.</p>
            <p><a href="${pageContext.request.contextPath}/user/join.do">다시 시도하기</a></p>
        </c:when>
        <c:when test="${param.result eq 'error'}">
            <p>서버 오류가 발생했습니다. 관리자에게 문의하세요.</p>
            <p><a href="${pageContext.request.contextPath}/user/join.do">다시 시도하기</a></p>
        </c:when>
        <c:otherwise>
            <p>알 수 없는 오류가 발생했습니다.</p>
            <p><a href="${pageContext.request.contextPath}/user/join.do">다시 시도하기</a></p>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
