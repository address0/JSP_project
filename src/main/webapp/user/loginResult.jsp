<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	
	<c:choose>
		<c:when test="${param.result == 'success'}">
			<h1>로그인 완료</h1>
			<p>환영합니다! 로그인에 성공했습니다.</p>
			<button onclick="location.href='${pageContext.request.contextPath}/user/profile.do?id=${sessionScope.id}'">메인 페이지</button>
		</c:when>
		
		<c:when test="${param.result == 'admin'}">
			<c:set var="manage" value="userManage" scope="session"/>
			<h1>로그인 완료</h1>
			<p>관리자님, 환영합니다! 관리자 페이지로 이동합니다.</p>
			<button onclick="location.href='${pageContext.request.contextPath}/user/list.do'">관리자 페이지</button>
		</c:when>
		
		<c:when test="${param.result == 'fail'}">
			<h1>미등록된 사용자</h1>
			<p>로그인에 실패했습니다. 사용자 정보를 확인하세요.</p>
		</c:when>
		
		<c:when test="${param.result == 'inactive'}">
			<h1>비활성화된 사용자</h1>
			<p>회원가입 승인 전입니다. 잠시만 기다려 주세요.</p>
			<button onclick="location.href='${pageContext.request.contextPath}/user/loginForm.do'">로그인 페이지로</button>
		</c:when>
		
		<c:otherwise>
			<h1>로그인 실패</h1>
			<p>알 수 없는 오류가 발생했습니다. 다시 시도하세요.</p>
		</c:otherwise>
	</c:choose>

</div>
</body>
</html>
