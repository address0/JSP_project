<%--
  Created by IntelliJ IDEA.
  User: kopo
  Date: 2025-05-14
  Time: 오전 9:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.example.model.*" %>
<html>
<head>
  <title>JOOZAG</title>
  <link rel="icon" type="image/png" sizes="96x96" href="<%=request.getContextPath()%>/favicon/favicon-96x96.png">
  <link rel="icon" type="image/png" sizes="32x32" href="<%=request.getContextPath()%>/favicon/favicon-32x32.png">
  <link rel="icon" type="image/png" sizes="16x16" href="<%=request.getContextPath()%>/favicon/favicon-16x16.png">
  <link rel="shortcut icon" href="<%=request.getContextPath()%>/favicon/favicon.ico">
  <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined" />
  <link href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="<%= request.getContextPath() %>/css/nav.css?v=<%= System.currentTimeMillis() %>">
  <style>
    .category-bar {
      display: flex;
      flex-wrap: wrap;
      background-color: #fff;
      padding: 15px 20px;
      border-bottom: 1px solid #eee;
    }

    .category-link {
      margin-right: 15px;
      text-decoration: none;
      color: #333;
      font-weight: bold;
      padding: 5px 10px;
      border-radius: 5px;
    }

    .category-link:hover {
      background-color: #f0f0f0;
    }

    .product-grid {
      display: grid;
      grid-template-columns: repeat(auto-fill, minmax(180px, 1fr));
      gap: 20px;
      padding: 20px;
    }

    .product-card {
      background-color: white;
      border: 1px solid #ddd;
      border-radius: 8px;
      overflow: hidden;
      box-shadow: 0 2px 5px rgba(0,0,0,0.05);
      transition: box-shadow 0.3s ease;
    }

    .product-card:hover {
      box-shadow: 0 4px 12px rgba(0,0,0,0.1);
    }

    .product-image {
      width: 100%;
      height: 200px;
      object-fit: cover;
    }

    .product-info {
      padding: 10px;
    }

    .product-name {
      font-size: 15px;
      color: #333;
      margin: 5px 0;
      white-space: nowrap;
      overflow: hidden;
      text-overflow: ellipsis;
    }

    .product-price {
      font-size: 14px;
      color: #DF41B0;
    }

    .original-price {
      text-decoration: line-through;
      color: #aaa;
      margin-right: 6px;
    }

    .category-tabs {
      display: flex;
      gap: 10px;
      padding: 15px 20px;
      background-color: #fff;
      border-bottom: 1px solid #eee;
    }

    .category-tabs.sub {
      background-color: #f9f9f9;
    }

    .category-tabs a {
      text-decoration: none;
      color: #444;
      font-weight: 500;
      padding: 5px 10px;
      border-radius: 5px;
    }

    .category-tabs a.selected {
      background-color: #000;
      color: #fff;
    }

    .tab-title {
      margin-right: 15px;
      font-weight: bold;
    }

    .no-product {
      width: 100%;
      text-align: center;
      padding: 50px 0;
      color: #888;
      font-size: 18px;
    }

  </style>
</head>
<body>
<jsp:include page="/main/nav.jsp" />
<!-- ✅ 대분류 탭 -->
<div class="category-tabs">
  <span class="tab-title">카테고리</span>
  <a href="<c:url value='/product/categoryList.do'/>" class="${empty selectedCategory ? 'selected' : ''}">전체</a>
  <c:forEach var="cat" items="${topCategories}">
    <a href="<c:url value='/product/categoryList.do?nb_category=${cat.nbCategory}'/>"
       class="${selectedCategory.nbCategory == cat.nbCategory ? 'selected' : ''}">
        ${cat.nmCategory}
    </a>
  </c:forEach>
</div>

<!-- 중분류 탭 -->
<div class="category-tabs sub">
  <c:forEach var="cat" items="${middleCategories}">
    <a href="javascript:void(0);"
       onclick="loadSubCategory(${cat.nbCategory}, 'subTab')"
       class="${selectedCategory.nbCategory == cat.nbCategory ? 'selected' : ''}">
        ${cat.nmCategory}
    </a>
  </c:forEach>
</div>

<!-- 소분류 탭 (AJAX 대상) -->
<div class="category-tabs sub" id="subTab">
  <c:if test="${not empty subCategories}">
    <c:forEach var="cat" items="${subCategories}">
      <a href="<c:url value='/product/categoryList.do?nb_category=${cat.nbCategory}'/>"
         class="${selectedCategory.nbCategory == cat.nbCategory ? 'selected' : ''}">
          ${cat.nmCategory}
      </a>
    </c:forEach>
  </c:if>
</div>

<!-- ✅ 상품 리스트 -->
<div class="product-grid">
  <c:choose>
    <c:when test="${not empty productList}">
      <c:forEach var="product" items="${productList}">
        <div class="product-card" onclick="location.href='<c:url value="/product/detail.do?id=${product.noProduct}"/>'">
          <img src="<%= request.getContextPath() %>/product/image.do?idFile=${product.idFile}" alt="${product.nmProduct}" class="product-image">
          <div class="product-info">
            <p class="product-name">${product.nmProduct}</p>
            <p class="product-price">
              <span class="original-price">${product.qtCustomer}원</span>
              <span class="discount-price">${product.qtSalePrice}원</span>
            </p>
          </div>
        </div>
      </c:forEach>
    </c:when>
    <c:otherwise>
      <div class="no-product">상품이 존재하지 않습니다.</div>
    </c:otherwise>
  </c:choose>
</div>
<script>
  function loadSubCategory(parentId, targetId) {
    // 1. 기존 탭 초기화
    const targetList = document.getElementById(targetId);
    targetList.innerHTML = '';

    // 2. AJAX 요청
    fetch('<c:url value="/category/subListJson.do"/>?parentId=' + parentId)
            .then(res => res.json())
            .then(data => {
              if (data.length === 0) {
                targetList.innerHTML = '<span class="no-sub">하위 카테고리가 없습니다</span>';
                return;
              }

              data.forEach(cat => {
                const a = document.createElement('a');
                a.href = '<c:url value="/product/categoryList.do"/>?nb_category=' + cat.nbCategory;
                a.textContent = cat.nmCategory;
                a.classList.add('category-tab');
                targetList.appendChild(a);
              });
            });
  }
</script>

</body>
</html>
