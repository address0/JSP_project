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
    h2 { margin-bottom: 20px; }
    table { width: 100%; border-collapse: collapse; margin-bottom: 30px; }
    th, td { border: 1px solid #ccc; padding: 10px; text-align: center; }
    th { background: #f8f8f8; }
    .form-group { margin-bottom: 15px; }
    label { display: block; margin-bottom: 5px; font-weight: bold; }
    input[type="text"], input[type="tel"] { width: 100%; padding: 8px; }
    button { background: #111; color: white; padding: 12px 20px; border: none; border-radius: 5px; cursor: pointer; }
    button:hover { background: #333; }
  </style>
</head>
<body>
<jsp:include page="/main/nav.jsp" />
<h2>📝 주문서 작성</h2>

<form method="post" action="${pageContext.request.contextPath}/order/submit.do">

  <div class="form-group">
    <label>주문자명</label>
    <input type="text" name="nmOrderPerson" required />
  </div>

  <div class="form-group">
    <label>수령자명</label>
    <input type="text" name="nmReceiver" required />
  </div>

  <div class="form-group">
    <label>우편번호</label>
    <input type="text" name="noDeliveryZipno" required />
  </div>

  <div class="form-group">
    <label>주소</label>
    <input type="text" name="nmDeliveryAddress" required />
  </div>

  <div class="form-group">
    <label>연락처</label>
    <input type="tel" name="nmReceiverTelno" required />
  </div>

  <div class="form-group">
    <label>배송 요청사항</label>
    <input type="text" name="nmDeliverySpace" />
  </div>

  <h3>📦 주문 상품</h3>
  <table>
    <thead>
    <tr>
      <th>상품명</th>
      <th>단가</th>
      <th>수량</th>
      <th>합계</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="product" items="${productList}">
      <tr>
        <td>
            ${product.nmProduct}
          <input type="hidden" name="noProduct" value="${product.noProduct}" />
        </td>
        <td>
          <fmt:formatNumber value="${product.qtSalePrice}" type="number" />원
          <input type="hidden" name="qtUnitPrice" value="${product.qtSalePrice}" />
        </td>
        <td>
          <input type="number" name="qtOrderItem" value="1" min="1" max="${product.qtStock}" style="width: 60px;" />
        </td>
        <td>
          <fmt:formatNumber value="${product.qtSalePrice}" type="number" />원
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>

  <div style="text-align: right;">
    <button type="submit">결제 및 주문하기</button>
  </div>

</form>

</body>
</html>

