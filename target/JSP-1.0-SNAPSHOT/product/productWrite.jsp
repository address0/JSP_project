<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
  <title><c:choose>
    <c:when test="${not empty product}">상품 수정</c:when>
    <c:otherwise>상품 등록</c:otherwise>
  </c:choose></title>
</head>
<body>
<h2><c:choose>
  <c:when test="${not empty product}">상품 수정</c:when>
  <c:otherwise>상품 등록</c:otherwise>
</c:choose></h2>

<form action="<c:choose>
        <c:when test='${not empty product}'>update.do</c:when>
        <c:otherwise>create.do</c:otherwise>
    </c:choose>" method="post" enctype="multipart/form-data">
  
  <!-- 상품 번호: 수정 시에만 포함 -->
  <c:if test="${not empty product}">
    <input type="hidden" name="id" value="${product.noProduct}">
  </c:if>
  
  <label for="nmProduct">상품명</label>
  <input type="text" id="nmProduct" name="nmProduct" required value="${product.nmProduct}">
  
  <label for="nmDetailExplain">상세 설명</label>
  <textarea id="nmDetailExplain" name="nmDetailExplain" rows="4" required>${product.nmDetailExplain}</textarea>
  
  <label for="idFile">첨부 이미지 (jpg / png / jfif 등)</label>
  <input type="file" id="idFile" name="idFile" accept="image/*" value="${product.idFile}">
  
  <label for="dtStartDate">판매 시작일 (yyyy-mm-dd)</label>
  <input type="date" id="dtStartDate" name="dtStartDate" value="${product.dtStartDate}">
  
  <label for="dtEndDate">판매 종료일 (yyyy-mm-dd)</label>
  <input type="date" id="dtEndDate" name="dtEndDate" value="${product.dtEndDate}">
  
  <label for="qtCustomerPrice">소비자가</label>
  <input type="number" id="qtCustomerPrice" name="qtCustomerPrice" required value="${product.qtCustomerPrice}">
  
  <label for="qtSalePrice">판매가</label>
  <input type="number" id="qtSalePrice" name="qtSalePrice" required value="${product.qtSalePrice}">
  
  <label for="qtStock">재고 수량</label>
  <input type="number" id="qtStock" name="qtStock" required value="${product.qtStock}">
  
  <label for="qtDeliveryFee">배송비</label>
  <input type="number" id="qtDeliveryFee" name="qtDeliveryFee" required value="${product.qtDeliveryFee}">
  
  <button type="submit">
    <c:choose>
      <c:when test="${not empty product}">수정</c:when>
      <c:otherwise>등록</c:otherwise>
    </c:choose>
  </button>
</form>
</body>
</html>
