<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>JOOZAG</title>
	<link rel="icon" type="image/png" sizes="96x96" href="<%=request.getContextPath()%>/favicon/favicon-96x96.png">
	<link rel="icon" type="image/png" sizes="32x32" href="<%=request.getContextPath()%>/favicon/favicon-32x32.png">
	<link rel="icon" type="image/png" sizes="16x16" href="<%=request.getContextPath()%>/favicon/favicon-16x16.png">
	<link rel="shortcut icon" href="<%=request.getContextPath()%>/favicon/favicon.ico">
	<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined" />
	<link href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap" rel="stylesheet">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/admin.css?v=<%=System.currentTimeMillis()%>">
	<link rel="stylesheet" href="<%= request.getContextPath() %>/css/nav.css?v=<%= System.currentTimeMillis() %>">
</head>
<body>
<jsp:include page="/main/nav.jsp" />
<h1>회원 관리</h1>
<table>
	<thead>
	<tr>
		<th>아이디</th>
		<th>이름</th>
		<th>이메일</th>
		<th>휴대폰</th>
		<th>상태</th>
		<th>관리</th>
	</tr>
	</thead>
	<tbody>
	<c:choose>
		<c:when test="${not empty userList}">
			<c:forEach var="user" items="${userList}">
				<tr>
					<td>${user.idUser}
					<c:if test="${user.cdUserType == '20'}">
						<span style="color: red;">[관리자]</span>
					</c:if>
					</td>
					<td>${user.nmUser}</td>
					<td>${user.nmEmail}</td>
					<td>${user.noMobile}</td>
					<td>
						<c:choose>
							<c:when test="${user.stStatus == 'ST00'}">가입 요청</c:when>
							<c:when test="${user.stStatus == 'ST01'}">정상</c:when>
							<c:when test="${user.stStatus == 'ST02'}">탈퇴 요청</c:when>
							<c:when test="${user.stStatus == 'ST03'}">일시정지</c:when>
							<c:otherwise>알 수 없음</c:otherwise>
						</c:choose>
					</td>
					<td>
						<c:if test="${user.cdUserType != '20'}">
							<c:choose>
								<c:when test="${user.stStatus == 'ST00'}">
									<button onclick="location.href='/user/action.do?action=approve&idUser=${user.idUser}'">승인</button>
								</c:when>
								<c:when test="${user.stStatus == 'ST01'}">
									<button onclick="location.href='/user/action.do?action=suspend&idUser=${user.idUser}'">일시정지</button>
								</c:when>
								<c:when test="${user.stStatus == 'ST02'}">
									<button onclick="location.href='/user/action.do?action=delete&idUser=${user.idUser}'">탈퇴</button>
								</c:when>
								<c:when test="${user.stStatus == 'ST03'}">
									<button onclick="location.href='/user/action.do?action=activate&idUser=${user.idUser}'">활성화</button>
								</c:when>
							</c:choose>
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<tr>
				<td colspan="6">등록된 사용자가 없습니다.</td>
			</tr>
		</c:otherwise>
	</c:choose>
	</tbody>
</table>
</body>
</html>
