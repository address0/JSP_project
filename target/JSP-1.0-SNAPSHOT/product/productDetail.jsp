<%--
  Created by IntelliJ IDEA.
  User: kopo
  Date: 2025-05-09
  Time: 오후 7:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
	<title>Title</title>
</head>
<body>
<h2>상품 상세</h2>
<c:if test="${not empty product}">
  <table>
    <tr>
      <th>상품번호</th>
      <td>${product.noProduct}</td>
    </tr>
    <tr>
      <th>상품명</th>
      <td>${product.nmProduct}</td>
    </tr>
    <tr>
      <th>상세 설명</th>
      <td>${product.nmDetailExplain}</td>
    </tr>
    <tr>
      <th>상품 이미지</th>
      <td>
        <c:if test="${not empty product.idFile}">
          <p><strong>상품 이미지:</strong></p>
          <img src="<%= request.getContextPath() %>/product/image.do?idFile=${product.idFile}" width="300" alt="상품 이미지" />
        </c:if>
        <c:if test="${empty product.idFile}">
          <p>상품 이미지가 없습니다.</p>
          <img src="../images/noImage.png" width="300" alt="상품 이미지 없음" />
        </c:if>
      </td>
    </tr>
    <tr>
      <th>판매 시작일</th>
      <td>${product.dtStartDate}</td>
    </tr>
    <tr>
      <th>판매 종료일</th>
      <td>${product.dtEndDate}</td>
    </tr>
    <tr>
      <th>소비자가</th>
      <td><fmt:formatNumber value="${product.qtCustomerPrice}" type="number" /> 원</td>
    </tr>
    <tr>
      <th>판매가</th>
      <td><fmt:formatNumber value="${product.qtSalePrice}" type="number" /> 원</td>
    </tr>
    <tr>
      <th>재고 수량</th>
      <td>${product.qtStock}</td>
    </tr>
    <tr>
      <th>배송비</th>
      <td><fmt:formatNumber value="${product.qtDeliveryFee}" type="number" /> 원</td>
    </tr>
    <tr>
      <th>등록자 ID</th>
      <td>${product.noRegister}</td>
    </tr>
    <tr>
      <th>등록일시</th>
      <td><fmt:formatDate value="${product.daFirstDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
    </tr>
  </table>
</c:if>

<c:if test="${empty product}">
  <p>해당 상품 정보를 찾을 수 없습니다.</p>
</c:if>
<button onclick="location.href='list.do'">목록으로</button>
</body>
</html>
