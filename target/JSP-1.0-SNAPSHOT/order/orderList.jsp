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
        table { width: 100%; border-collapse: collapse; margin-top: 30px; }
        th, td { border: 1px solid #ccc; padding: 10px; text-align: center; }
        th { background-color: #f9f9f9; }
        .modal { display: none; position: fixed; top: 0; left: 0; width: 100%; height: 100%; background: rgba(0,0,0,0.5); }
        .modal-content { background: white; margin: 10% auto; padding: 20px; width: 500px; border-radius: 10px; }
    </style>
</head>
<body>
<jsp:include page="/main/nav.jsp" />
<h2>📋 주문 내역</h2>
<c:if test="${empty orderList}">
    <p>주문 내역이 없습니다.</p>
</c:if>

<table>
    <thead>
    <tr>
        <th>주문번호</th>
        <th>주문자</th>
        <th>금액</th>
        <th>결제상태</th>
        <th>주문상태</th>
        <th>일자</th>
        <th>상세</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="order" items="${orderList}">
        <tr>
            <td>${order.idOrder}</td>
            <td>${order.nmOrderPerson}</td>
            <td><fmt:formatNumber value="${order.qtOrderAmount}" type="number" />원</td>
            <td>
                <c:choose>
                    <c:when test="${order.stPayment == '20'}">결제완료</c:when>
                    <c:otherwise>결제대기</c:otherwise>
                </c:choose>
            </td>
            <td>
                <c:choose>
                    <c:when test="${order.stOrder == '10'}">주문완료</c:when>
                    <c:otherwise>배송중</c:otherwise>
                </c:choose>
            </td>
            <td><fmt:formatDate value="${order.cdOrderDate}" pattern="yyyy-MM-dd" /></td>
            <td>
                <button onclick="showModal('${order.idOrder}')">보기</button>
            </td>
        </tr>
        <tr>
            <td colspan="7">
                <div class="modal" id="modal-${order.idOrder}">
                    <div class="modal-content">
                        <h3>주문 상세</h3>
                        <p><b>주문번호:</b> ${order.idOrder}</p>
                        <p><b>주문자:</b> ${order.nmOrderPerson}</p>
                        <p><b>수령인:</b> ${order.nmReceiver}</p>
                        <p><b>수령인 연락처:</b> ${order.nmReceiverTelno}</p>
                        <p><b>우편번호:</b> ${order.noDeliveryZipno}</p>
                        <p><b>배송지:</b> ${order.nmDeliveryAddress}</p>
                        <p><b>배송 요청사항:</b> ${order.nmDeliverySpace}</p>
                        <p><b>주문일자:</b> <fmt:formatDate value="${order.cdOrderDate}" pattern="yyyy-MM-dd" /></p>
                        <p><b>결제상태:</b> <c:choose>
                            <c:when test="${order.stPayment == '20'}">결제완료</c:when>
                            <c:otherwise>결제대기</c:otherwise>
                        </c:choose></p>
                        <p><b>주문상태:</b> <c:choose>
                            <c:when test="${order.stOrder == '10'}">주문완료</c:when>
                            <c:otherwise>배송중</c:otherwise>
                        </c:choose></p>
                        <button onclick="closeModal('${order.idOrder}')">닫기</button>
                    </div>
                </div>
            </td>
        </tr>

    </c:forEach>
    </tbody>
</table>
<script>
    function showModal(orderId) {
        document.getElementById('modal-' + orderId).style.display = 'block';
    }
    function closeModal(orderId) {
        document.getElementById('modal-' + orderId).style.display = 'none';
    }
</script>
</body>
</html>

