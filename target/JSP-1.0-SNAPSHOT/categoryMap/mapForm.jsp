<%--
  Created by IntelliJ IDEA.
  User: kopo
  Date: 2025-05-13
  Time: 오전 9:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Title</title>
</head>
<body>
<h1>상품 카테고리 관리</h1>
<c:if test="${not empty products}">
	<table>
		<tr>
			<th>선택</th>
			<th>상품 ID</th>
			<th>상품명</th>
			<th>가격</th>
			<th>수량</th>
		</tr>
		<c:forEach var="product" items="${products}">
			<tr>
				<td>
					<input type="radio" name="productId" value="${product.noProduct}" onclick="selectProduct('${product.noProduct}', '${product.nmProduct}', '${product.idFile}')" />
				</td>
				<td>${product.noProduct}</td>
				<td>${product.nmProduct}</td>
				<td>${product.qtSalePrice}</td>
				<td>${product.qtStock}</td>
			</tr>
		</c:forEach>
	</table>
</c:if>
<c:if test="${empty products}">
	<p>상품이 없습니다.</p>
</c:if>
<hr>

<form action="${pageContext.request.contextPath}/categoryMap/update.do" method="post" onsubmit="return validateForm()">
	<input type="hidden" name="productId" id="selectedProductId">
	<h2>카테고리 선택</h2>
	<c:if test="${not empty categories}">
		<ul>
			<c:forEach var="category" items="${categories}">
				<c:set var="isChecked" value="false" />
				<c:forEach var="map" items="${categoryProductMaps}">
					<c:if test="${map.noProduct eq selectedProductId and map.nbCategory eq category.nbCategory}">
						<c:set var="isChecked" value="true" />
					</c:if>
				</c:forEach>
				
				<label>
					<input type="checkbox" name="categoryList" value="${category.nbCategory}"
						   <c:if test="${isChecked}">checked</c:if> />
						${category.nmFullCategory}
				</label><br/>
			</c:forEach>
		</ul>
	</c:if>
	<c:if test="${empty categories}">
		<p>카테고리가 없습니다.</p>
	</c:if>
	<button type="submit">OK</button>
</form>
<h2>선택된 상품</h2>
<div id="productInfo">
	<p>상품명: <span id="productName"></span></p>
	<img id="productImage" src="" alt="상품 이미지" width="100">
</div>

<h2>선택된 카테고리</h2>
<div id="selectedCategories"></div>
<script>
    window.categoryMapData = {
        <c:forEach var="product" items="${products}">
        "${product.noProduct}": [
            <c:forEach var="map" items="${categoryProductMaps}" varStatus="status">
            <c:if test="${map.noProduct eq product.noProduct}">
            ${map.nbCategory}<c:if test="${!status.last}">,</c:if>
            </c:if>
            </c:forEach>
        ]<c:if test="${!productStatus.last}">,</c:if>
        </c:forEach>
    };
</script>
<script src="../script/categoryMap.js"></script>
</body>
</html>
