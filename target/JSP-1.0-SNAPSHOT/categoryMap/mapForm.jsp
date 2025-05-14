<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>JOOZAG</title>
	<link rel="icon" type="image/png" sizes="96x96" href="<%=request.getContextPath()%>/favicon/favicon-96x96.png">
	<link rel="icon" type="image/png" sizes="32x32" href="<%=request.getContextPath()%>/favicon/favicon-32x32.png">
	<link rel="icon" type="image/png" sizes="16x16" href="<%=request.getContextPath()%>/favicon/favicon-16x16.png">
	<link rel="shortcut icon" href="<%=request.getContextPath()%>/favicon/favicon.ico">
	<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined" />
	<link rel="stylesheet" href="<%= request.getContextPath() %>/css/nav.css?v=<%= System.currentTimeMillis() %>">
	<link href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap" rel="stylesheet">
	<style>
        h1, h2 {
            color: #333;
            margin-top: 30px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            background: #fff;
            border-radius: 8px;
            overflow: hidden;
            box-shadow: 0 2px 8px rgba(0,0,0,0.1);
        }
        table th, table td {
            border-bottom: 1px solid #eee;
            padding: 12px 16px;
            text-align: center;
        }
        table th {
            background-color: #f8f8f8;
            font-weight: bold;
        }
        form {
            width: 45%;
            margin-top: 30px;
            padding: 20px;
            background-color: #fafafa;
            border: 1px solid #ddd;
            border-radius: 8px;
        }
        ul {
            list-style: none;
            padding-left: 0;
        }
        label {
            display: block;
            margin: 10px 0;
        }
        button {
			width: 100%;
            margin-top: 20px;
            background-color: #111;
            color: white;
            border: none;
            padding: 12px 20px;
            border-radius: 6px;
            cursor: pointer;
            font-size: 14px;
        }
        button:hover {
            background-color: #333;
        }
        #productInfo {
            width: 100%;
            margin-top: 10px;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 8px;
            background-color: #fefefe;
            box-sizing: border-box;
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        #productImage {
            max-width: 100%;
            height: auto;
            border-radius: 8px;
            object-fit: contain;
            display: block;
        }
        #selectedCategories {
			width: 100%;
            margin-top: 10px;
            background-color: #f3f3f3;
            padding: 10px;
            border-radius: 6px;
        }
		.category-mapping-wrapper {
            padding: 20px;
            width: 960px;
		}
		.category-wrapper {
			display: flex;
			margin-top: 20px;
			justify-content: space-between;
		}
		.selected {
			display: flex;
			flex-direction: column;
			margin-left: 20px;
			width: 50%;
		}
	</style>
</head>
<body>
<jsp:include page="/main/nav.jsp" />
<div class="category-mapping-wrapper">
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
	<div class="category-wrapper">
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
							<input type="checkbox" name="categoryList" value="${category.nbCategory}" <c:if test="${isChecked}">checked</c:if> />
								${category.nmFullCategory}
						</label>
					</c:forEach>
				</ul>
			</c:if>
			<c:if test="${empty categories}">
				<p>카테고리가 없습니다.</p>
			</c:if>
			<button type="submit">카테고리 저장</button>
		</form>
		<div class="selected">
			<h2>선택된 상품</h2>
			<div id="productInfo">
				<p>상품명: <span id="productName"></span></p>
				<img id="productImage" src="" alt="상품 이미지">
			</div>
			
			<h2>선택된 카테고리</h2>
			<div id="selectedCategories"></div>
		</div>
	</div>
</div>

<script>
    window.categoryMapData = {
        <c:forEach var="product" items="${products}">
        "${product.noProduct}": [
            <c:forEach var="map" items="${categoryProductMaps}" varStatus="status">
            <c:if test="${map.noProduct eq product.noProduct}">
            ${map.nbCategory}<c:if test="${!status.last}">,</c:if>
            </c:if>
            </c:forEach>
        ],
        </c:forEach>
    };
</script>
<script src="../script/categoryMap.js"></script>
</body>
</html>
