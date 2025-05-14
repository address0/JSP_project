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
    .form-container {
      max-width: 720px;
      margin: 30px auto;
      padding: 20px;
    }
    h2 {
      text-align: center;
      font-size: 24px;
      margin-bottom: 30px;
    }
    form {
      display: flex;
      flex-direction: column;
      gap: 14px;
    }
    label {
      font-weight: bold;
      margin-bottom: 6px;
    }
    input[type="text"],
    input[type="number"],
    input[type="file"],
    input[type="date"],
    textarea {
      padding: 10px;
      font-size: 14px;
      border: 1px solid #ccc;
      border-radius: 6px;
    }
    textarea {
      resize: vertical;
    }
    img {
      margin-top: 10px;
      width: 300px;
      border-radius: 10px;
    }
    button[type="submit"] {
      background-color: #111;
      color: white;
      padding: 14px;
      border: none;
      border-radius: 8px;
      font-size: 16px;
      cursor: pointer;
      transition: background 0.3s;
    }
    button[type="submit"]:hover {
      background-color: #333;
    }
  </style>
</head>
<body>
<jsp:include page="/main/nav.jsp" />
<div class="form-container">
  <h2><c:choose>
    <c:when test="${not empty product}">상품 수정</c:when>
    <c:otherwise>상품 등록</c:otherwise>
  </c:choose></h2>
  
  <form action="<c:choose>
        <c:when test='${not empty product}'>update.do</c:when>
        <c:otherwise>create.do</c:otherwise>
    </c:choose>" method="post" enctype="multipart/form-data">
    
    <c:if test="${not empty product}">
      <input type="hidden" name="id" value="${product.noProduct}">
    </c:if>
    
    <label for="nmProduct">상품명</label>
    <input type="text" id="nmProduct" name="nmProduct" required value="${product.nmProduct}">
    
    <label for="nmDetailExplain">상세 설명</label>
    <textarea id="nmDetailExplain" name="nmDetailExplain" rows="4" required>${product.nmDetailExplain}</textarea>
    
    <label for="idFile">첨부 이미지 (jpg / png / jfif 등)</label>
    <input type="file" id="idFile" name="idFile" accept="image/*">
    <c:if test="${not empty product.idFile}">
      <p>현재 이미지:</p>
      <img src="${pageContext.request.contextPath}${image.nmFilePath}" alt="기존 이미지">
    </c:if>
    
    <label for="dtStartDate">판매 시작일 (yyyy-mm-dd)</label>
    <input type="date" id="dtStartDate" name="dtStartDate" value="${product.dtStartDate}">
    
    <label for="dtEndDate">판매 종료일 (yyyy-mm-dd)</label>
    <input type="date" id="dtEndDate" name="dtEndDate" value="${product.dtEndDate}">
    
    <label for="qtCustomer">소비자가</label>
    <input type="number" id="qtCustomer" name="qtCustomer" required value="${product.qtCustomer}">
    
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
</div>
</body>
</html>
