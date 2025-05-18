<%--
  Created by IntelliJ IDEA.
  User: hyunj
  Date: 2025-05-18
  Time: 오후 8:26
  To change this template use File | Settings | File Templates.
--%>
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
        table {
            width: 90%;
            margin: 40px auto;
            border-collapse: collapse;
        }
        th, td {
            padding: 14px;
            text-align: center;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #f8f8f8;
        }
        td img {
            width: 80px;
            height: 80px;
            object-fit: cover;
            border-radius: 5px;
        }
        .total {
            text-align: right;
            margin: 20px 5%;
            font-size: 18px;
            font-weight: bold;
        }
        .empty-msg {
            text-align: center;
            font-size: 18px;
            margin-top: 60px;
        }
    </style>
</head>
<body>
<jsp:include page="/main/nav.jsp" />
<h2 style="text-align:center;">🛒 장바구니</h2>

<c:if test="${not empty basketItemList}">
    <table>
        <thead>
        <tr>
            <th>상품 이미지</th>
            <th>상품명</th>
            <th>단가</th>
            <th>수량</th>
            <th>금액</th>
            <th>삭제</th>
        </tr>
        </thead>
        <tbody>
        <c:set var="totalAmount" value="0" />
        <c:forEach var="item" items="${basketItemList}">
            <tr>
                <td>
                    <c:choose>
                        <c:when test="${not empty item.product.idFile}">
                            <img src="<%= request.getContextPath() %>/product/image.do?idFile=${item.product.idFile}" alt="상품 이미지" />
                        </c:when>
                        <c:otherwise>
                            <img src="../images/noImage.png" alt="상품 이미지 없음" />
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>${item.product.nmProduct}</td>
                <td><fmt:formatNumber value="${item.qtBasketItemPrice}" type="number" />원</td>
                <td>${item.qtBasketItemQty}
                    <form method="post" action="<%=request.getContextPath()%>/basket/updateQty.do">
                        <input type="hidden" name="nbBasketItem" value="${item.nbBasketItem}" />
                        <input type="number"
                               name="quantity"
                               value="${item.qtBasketItemQty}"
                               min="1"
                               max="${item.product.qtStock}"
                               style="width: 50px; text-align:center;" />
                        <button type="submit">수정</button>
                    </form>
                </td>
                <td>
                    <fmt:formatNumber value="${item.qtBasketItemAmount}" type="number" />원
                </td>
                <td>
                    <form method="post" action="<%=request.getContextPath()%>/basket/delete.do">
                        <input type="hidden" name="nbBasketItem" value="${item.nbBasketItem}" />
                        <button type="submit">삭제</button>
                    </form>
                </td>
            </tr>
            <c:set var="totalAmount" value="${totalAmount + item.qtBasketItemAmount}" />
        </c:forEach>
        </tbody>
    </table>

    <div class="total">
        총 합계: <fmt:formatNumber value="${totalAmount}" type="number" /> 원
    </div>
</c:if>

<c:if test="${empty basketItemList}">
    <div class="empty-msg">
        장바구니에 담긴 상품이 없습니다.
    </div>
</c:if>
<button onclick="location.href='<%=request.getContextPath()%>/basket/deleteAll.do?basketId=${basket.nbBasket}'" style="margin: 20px auto; display: block;">장바구니 비우기</button>
</body>
</html>
