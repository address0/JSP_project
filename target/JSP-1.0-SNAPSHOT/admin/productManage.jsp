<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>상품 관리</h1>
<button onclick="location.href='<%= request.getContextPath() %>/product/createForm.do'">상품 등록</button>

<table>
	<thead>
	<tr>
		<th>상품 ID</th>
		<th>상품명</th>
		<th>가격</th>
		<th>재고</th>
		<th>상태</th>
		<th>관리</th>
	</tr>
	</thead>
	<tbody>
	<c:choose>
		<c:when test="${not empty productList}">
			<c:forEach var="product" items="${productList}">
				<tr>
					<td>${product.noProduct}</td>
					<td>${product.nmProduct}</td>
					<td>${product.qtSalePrice}</td>
					<td>${product.qtStock}</td>
					<td>
						<c:choose>
							<c:when test="${product.qtStock > 0}">판매 중</c:when>
							<c:otherwise>재고 없음</c:otherwise>
						</c:choose>
					</td>
					<td>
						<button onclick="location.href='updateForm.do?id=${product.noProduct}'">수정</button>
						<button onclick="location.href='delete.do?id=${product.noProduct}'">삭제</button>
					</td>
				</tr>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<tr>
				<td colspan="6">등록된 상품이 없습니다.</td>
			</tr>
		</c:otherwise>
	</c:choose>
	</tbody>
</table>
