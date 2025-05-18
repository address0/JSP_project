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
          <button onclick="checkLoginAndOpenCart()">장바구니 담기</button>
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
<div id="cart-modal" style="display:none; position:fixed; top:0; left:0; width:100%; height:100%; background:rgba(0,0,0,0.4);">
  <div style="width:400px; margin:100px auto; background:white; padding:20px; border-radius:10px; position:relative;">
    <h3>상품을 장바구니에 담기</h3>
    <p><b>${product.nmProduct}</b></p>
    <p>수량:
      <button type="button" onclick="adjustQty(-1)">-</button>
      <input type="text" id="qtyInput" value="1" style="width:30px; text-align:center;" readonly />
      <button type="button" onclick="adjustQty(1)">+</button>
    </p>
    <button onclick="addToCart()">장바구니 담기</button>
    <button onclick="closeCartModal()">닫기</button>
  </div>
</div>
<div id="cart-result-modal" style="display:none; position:fixed; top:20%; left:50%; transform:translateX(-50%);
  width:300px; background:white; border-radius:10px; padding:20px; box-shadow:0 2px 8px rgba(0,0,0,0.3); z-index:1000;">
  <p id="cart-result-message" style="font-size:16px; font-weight:bold; text-align:center;"></p>
  <p style="text-align:center; margin-top:10px;">장바구니로 이동하시겠습니까?</p>
  <div style="display:flex; justify-content:space-between; margin-top:20px;">
    <button onclick="goToCart()" style="flex:1; margin-right:5px; padding:10px;">확인</button>
    <button onclick="closeResultModal()" style="flex:1; margin-left:5px; padding:10px;">더 둘러보기</button>
  </div>
</div>
<script>
  function checkLoginAndOpenCart() {
    const isLoggedIn = '${sessionScope.status}' === 'user';
    if (!isLoggedIn) {
      alert('로그인 후 이용 가능합니다.');
      window.location.href= '<%=request.getContextPath()%>/user/loginForm.do';
      return;
    }
    document.getElementById('cart-modal').style.display = 'block';
  }

  function closeCartModal() {
    document.getElementById('cart-modal').style.display = 'none';
  }

  function adjustQty(change) {
    const qtyInput = document.getElementById('qtyInput');
    let qty = parseInt(qtyInput.value);
    const stock = Number('${product.qtStock}'); // 재고 수량

    qty = Math.min(stock, Math.max(1, qty + change));
    const newQty = qty + change;

    if (newQty > stock) {
      alert('재고 수량을 초과했습니다.');
      return;
    }

    if (newQty < 1) {
      alert('최소 수량은 1개입니다.');
      return;
    }
    qtyInput.value = qty;
  }

  function addToCart() {
    const qty = document.getElementById('qtyInput').value;
    const productId = '${product.noProduct}';

    fetch('<%=request.getContextPath()%>/cart/add.do', {
      method: 'POST',
      headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
      body: 'productId=' + encodeURIComponent(productId) + '&quantity=' + encodeURIComponent(qty)
    })
    .then(res => res.text())
    .then(msg => {
      showCartResultModal(msg);
    });
  }
  function showCartResultModal(message) {
    document.getElementById('cart-result-message').innerText = message;
    document.getElementById('cart-result-modal').style.display = 'block';
  }

  function closeResultModal() {
    document.getElementById('cart-result-modal').style.display = 'none';
    closeCartModal();
  }

  function goToCart() {
    window.location.href = '<%=request.getContextPath()%>/basket/list.do?id=${sessionScope.userId}';
  }
</script>
</body>
</html>
