<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
	<link rel="stylesheet" href="<%= request.getContextPath() %>/css/categoryList.css?v=<%= System.currentTimeMillis() %>">
</head>
<body>
<jsp:include page="/main/nav.jsp" />
<h1>카테고리 관리</h1>
<button onclick="location.href='/category/form.do'">카테고리 추가</button>

<div class="category-wrapper">
	<div class="category-column">
		<h3>대분류</h3>
		<ul class="category-list" id="top-category">
			<c:forEach var="category" items="${mainCategories}">
				<li onclick="loadSubCategory(this, 'middle-category')" data-id="${category.nbCategory}">
					<span>${category.nmCategory}</span>
					<button class="disabled" id="update-btn-${category.nbCategory}"
					onclick="location.href='updateForm.do?id=${category.nbCategory}'">수정</button>
				</li>
			</c:forEach>
		</ul>
	</div>
	
	<div class="category-column">
		<h3>중분류</h3>
		<ul class="category-list" id="middle-category"></ul>
	</div>
	
	<div class="category-column">
		<h3>소분류</h3>
		<ul class="category-list" id="sub-category"></ul>
	</div>
</div>
<script src="../script/admin.js?v=<%= System.currentTimeMillis() %>"></script>
</body>
</html>

