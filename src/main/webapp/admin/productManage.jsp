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
	<link rel="stylesheet" href="<%= request.getContextPath() %>/css/productList.css?v=<%= System.currentTimeMillis() %>">
</head>
<body>
<jsp:include page="/main/nav.jsp" />
<h1>상품 관리</h1>
<div class="product-manage-toolbar">
	<button onclick="location.href='<%= request.getContextPath() %>/product/createForm.do'">상품 등록</button>
	<button onclick="location.href='<%= request.getContextPath() %>/categoryMap/mapForm.do'">상품 카테고리 편집</button>
</div>

<div class="product-manage-list">
	<c:choose>
		<c:when test="${not empty productList}">
			<c:forEach var="product" items="${productList}">
				<div class="product-card">
					<div class="product-image">
						<img src="<%= request.getContextPath() %>/product/image.do?idFile=${product.idFile}" alt="${product.nmProduct}" />
					</div>
					<div class="product-info">
						<p class="product-title">
							<a href="detail.do?id=${product.noProduct}">${product.nmProduct}</a>
						</p>
						<p class="product-stock">재고: ${product.qtStock}개</p>
						<p class="product-price">
							<fmt:formatNumber value="${product.qtSalePrice}" type="number" />원
						</p>
						<p class="product-status">
							<fmt:formatDate value="${now}" pattern="yyyy-MM-dd" var="today"/>
							<c:choose>
								<c:when test="${product.dtEndDate > today}">판매 중</c:when>
								<c:otherwise>판매 종료</c:otherwise>
							</c:choose>
						</p>
					</div>
					<div class="product-actions">
						<button onclick="location.href='updateForm.do?id=${product.noProduct}'">수정</button>
						<button onclick="location.href='delete.do?id=${product.noProduct}'">삭제</button>
						<button onclick="location.href='update.do?id=${product.noProduct}&updateStatus=end'">판매종료</button>
						<button onclick="location.href='update.do?id=${product.noProduct}&updateStatus=stock'">품절처리</button>
					</div>
				</div>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<p class="no-products">등록된 상품이 없습니다.</p>
		</c:otherwise>
	</c:choose>
</div>

<script src="../script/admin.js?v=<%= System.currentTimeMillis() %>"></script>
</body>
</html>
