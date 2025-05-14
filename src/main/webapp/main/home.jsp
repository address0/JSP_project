<%--
  Created by IntelliJ IDEA.
  User: kopo
  Date: 2025-05-02
  Time: 오후 1:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<html>
<head>
	<title>JOOZAG</title>
	<link rel="icon" type="image/png" sizes="96x96" href="<%=request.getContextPath()%>/favicon/favicon-96x96.png">
	<link rel="icon" type="image/png" sizes="32x32" href="<%=request.getContextPath()%>/favicon/favicon-32x32.png">
	<link rel="icon" type="image/png" sizes="16x16" href="<%=request.getContextPath()%>/favicon/favicon-16x16.png">
	<link rel="shortcut icon" href="<%=request.getContextPath()%>/favicon/favicon.ico">
	<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined" />
	<link rel="stylesheet" href="<%= request.getContextPath() %>/css/nav.css?v=<%= System.currentTimeMillis() %>">
	<link rel="stylesheet" href="<%= request.getContextPath() %>/css/productList.css?v=<%= System.currentTimeMillis() %>">
	<link href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap" rel="stylesheet">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css" />
	<style>
		.main {
			width: 60%;
		}
        /* 캐러셀 전체 래퍼 */
        .swiper-container-wrapper {
            max-width: 1024px;
            margin: 30px auto; /* 가운데 정렬 */
            padding: 0 16px;
            box-sizing: border-box;
        }

        /* Swiper 기본 스타일 */
        .swiper {
            width: 100%;
            aspect-ratio: 3 / 2; /* 비율 유지: 3:2 */
            border-radius: 12px;
            overflow: hidden;
            position: relative;
        }

        /* 슬라이드 내부 */
        .swiper-slide {
            display: flex;
            align-items: center;
            justify-content: center;
            background: #fff;
        }

        /* 슬라이드 이미지 */
        .swiper-slide img {
            width: 100%;
            height: 100%;
            object-fit: cover;
        }

        /* Swiper 버튼 스타일 개선 (선택) */
        .swiper-button-next,
        .swiper-button-prev {
            color: #F6D8C6;
            transform: scale(0.8);
        }
		h2 {
			margin-top: 20px;
			font-size: 20px;
			color: #333;
		}
	</style>
</head>
<body>
<jsp:include page="/main/nav.jsp" />
<div class="main">
	<div class="swiper">
		<div class="swiper-wrapper">
			<div class="swiper-slide"><img src="<%=request.getContextPath()%>images/carousel1.png" alt="이벤트1"></div>
			<div class="swiper-slide"><img src="<%=request.getContextPath()%>images/carousel2.png" alt="이벤트2"></div>
			<div class="swiper-slide"><img src="<%=request.getContextPath()%>images/carousel3.png" alt="이벤트3"></div>
		</div>
		<div class="swiper-pagination"></div>
		<div class="swiper-button-prev"></div>
		<div class="swiper-button-next"></div>
	</div>
	<h2>당신을 위한 추천 아이템</h2>
	<div class="sort-container">
		<form method="get" action="main.do">
			<label for="sort">정렬 기준: </label>
			<select name="sort" id="sort" onchange="this.form.submit()">
				<option value="latest" ${param.sort == 'latest' ? 'selected' : ''}>최신순</option>
				<option value="priceAsc" ${param.sort == 'priceAsc' ? 'selected' : ''}>낮은 가격순</option>
				<option value="priceDesc" ${param.sort == 'priceDesc' ? 'selected' : ''}>높은 가격순</option>
				<option value="name" ${param.sort == 'name' ? 'selected' : ''}>상품명순</option>
				<option value="discount" ${param.sort == 'discount' ? 'selected' : ''}>할인율순</option>
			</select>
			
			<label style="margin-left:20px;">
				<input type="checkbox" name="onlySale" value="true"
				${param.onlySale == 'true' ? 'checked' : ''} onchange="this.form.submit()" />
				판매중인 상품만 보기
			</label>
		</form>
	</div>
	<div class="product-list">
		<c:forEach var="product" items="${productList}">
			<div class="product" onclick="location.href='${pageContext.request.contextPath}/product/detail.do?id=${product.noProduct}'">
				<img src="<%= request.getContextPath() %>/product/image.do?idFile=${product.idFile}" alt="${product.nmProduct}">
				<p class="product-name">${product.nmProduct}</p>
				<p class="product-desc">${product.nmDetailExplain}</p>
				
				<!-- 가격 표시 로직 -->
				<div class="product-price">
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
			</div>
		</c:forEach>
	</div>
</div>
<script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
<script>
    const swiper = new Swiper('.swiper', {
        loop: true,
        autoplay: {
            delay: 4000,
        },
        pagination: {
            el: '.swiper-pagination',
            clickable: true,
        },
        navigation: {
            nextEl: '.swiper-button-next',
            prevEl: '.swiper-button-prev',
        }
    });
</script>

</body>
</html>
