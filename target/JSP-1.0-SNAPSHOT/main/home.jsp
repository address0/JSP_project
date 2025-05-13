<%--
  Created by IntelliJ IDEA.
  User: kopo
  Date: 2025-05-02
  Time: 오후 1:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>JOOZAG</title>
	<link rel="icon" type="image/png" sizes="96x96" href="<%=request.getContextPath()%>/favicon/favicon-96x96.png">
	<link rel="icon" type="image/png" sizes="32x32" href="<%=request.getContextPath()%>/favicon/favicon-32x32.png">
	<link rel="icon" type="image/png" sizes="16x16" href="<%=request.getContextPath()%>/favicon/favicon-16x16.png">
	<link rel="shortcut icon" href="<%=request.getContextPath()%>/favicon/favicon.ico">
	<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined" />
	<link rel="stylesheet" href="<%= request.getContextPath() %>/css/main.css?v=<%= System.currentTimeMillis() %>">
	<link href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap" rel="stylesheet">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css" />
	<style>
        /* 캐러셀 전체 래퍼 */
        .swiper-container-wrapper {
            max-width: 1024px;
            margin: 30px auto; /* 가운데 정렬 */
            padding: 0 16px;
            box-sizing: border-box;
        }

        /* Swiper 기본 스타일 */
        .swiper {
            width: 60%;
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
	</style>
</head>
<body>
<jsp:include page="/main/nav.jsp" />
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
