<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
  <link rel="stylesheet" href="<%= request.getContextPath() %>/css/nav.css?v=<%= System.currentTimeMillis() %>">
  <link href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap" rel="stylesheet">
  <style>
    .product-detail-wrapper {
      width: 720px;
      margin: 20px auto;
      padding: 20px;
    }
    .product-detail-wrapper img {
      width: 100%;
      margin-bottom: 20px;
    }
    .product-title {
      font-size: 20px;
      font-weight: bold;
      margin-bottom: 10px;
    }
    .product-info {
      font-size: 15px;
      color: #333;
      margin-bottom: 20px;
    }
    .price-box {
      background: #f8f8f8;
      border-radius: 10px;
      padding: 16px;
      margin-bottom: 20px;
    }
    .price-box .sale-price {
      font-size: 24px;
      color: #DF41B0;
      font-weight: bold;
    }
    .price-box .original-price {
      text-decoration: line-through;
      color: #aaa;
      margin-left: 10px;
    }
    .sale-price.only {
      font-size: 16px;
      color: #000000;
    }
    .action-buttons {
      display: flex;
      flex-direction: column;
      gap: 10px;
      margin-top: 30px;
    }
    .action-buttons button {
      background: #111;
      color: #fff;
      border: none;
      padding: 14px;
      font-size: 16px;
      border-radius: 8px;
      cursor: pointer;
      transition: 0.2s;
    }
    .action-buttons button:hover {
      background: #333;
    }
    .discount-rate {
      color: #DF41B0;
      font-weight: bold;
      font-size: 16px;
    }
  </style>
</head>
<body>
<jsp:include page="/main/nav.jsp" />
<div class="product-detail-wrapper">
  <c:if test="${not empty product}">
    <c:if test="${not empty product.idFile}">
      <img src="<%= request.getContextPath() %>/product/image.do?idFile=${product.idFile}" alt="상품 이미지" />
    </c:if>
    <c:if test="${empty product.idFile}">
      <img src="../images/noImage.png" alt="상품 이미지 없음" />
    </c:if>
    
    <div class="product-title">${product.nmProduct}</div>
    <div class="product-info">${product.nmDetailExplain}</div>
    
    <div class="price-box">
      <c:choose>
        <c:when test="${product.qtCustomer > product.qtSalePrice}">
            <span class="original-price">
              <fmt:formatNumber value="${product.qtCustomer}" type="number" />원
            </span>
          <span class="sale-price">
              <fmt:formatNumber value="${product.qtSalePrice}" type="number" />원
            </span>
          <span class="discount-rate">
              (
              <fmt:formatNumber value="${(1 - product.qtSalePrice / product.qtCustomer) * 100}" maxFractionDigits="0" />%
              )
            </span>
        </c:when>
        <c:otherwise>
            <span class="sale-price only">
              <fmt:formatNumber value="${product.qtSalePrice}" type="number" />원
            </span>
        </c:otherwise>
      </c:choose>
    </div>
    
    <div class="product-info">
      배송비 <fmt:formatNumber value="${product.qtDeliveryFee}" type="number" />원
      <br/>재고: ${product.qtStock}개
      <br/>판매기간: ${product.dtStartDate} ~ ${product.dtEndDate}
    </div>
    
    <div class="action-buttons">
      <c:choose>
        <c:when test="${sessionScope.status == 'admin'}">
          <button onclick="location.href='updateForm.do?id=${product.noProduct}'">상품 수정</button>
          <button onclick="location.href='delete.do?id=${product.noProduct}'">상품 삭제</button>
          <button onclick="location.href='update.do?id=${product.noProduct}&updateStatus=end'">판매 종료</button>
          <button onclick="location.href='update.do?id=${product.noProduct}&updateStatus=stock'">품절 처리</button>
        </c:when>
        <c:otherwise>
          <button onclick="alert('장바구니에 담겼습니다.')">장바구니 담기</button>
          <button onclick="alert('주문 페이지로 이동합니다.')">구매하기</button>
        </c:otherwise>
      </c:choose>
      <button onclick="location.href='<%= request.getContextPath() %>/product/categoryList.do'">목록으로</button>
    </div>
  </c:if>
  <c:if test="${empty product}">
    <p>해당 상품 정보를 찾을 수 없습니다.</p>
  </c:if>
</div>
</body>
</html>
