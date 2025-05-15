<%--
  Created by IntelliJ IDEA.
  User: kopo
  Date: 2025-05-14
  Time: 오전 9:36
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
  <link href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="<%= request.getContextPath() %>/css/nav.css?v=<%= System.currentTimeMillis() %>">
</head>
<body>
<jsp:include page="/main/nav.jsp" />
<c:forEach var="category" items="${categories}">
  <h2>${category.nmCategory}</h2>
  <ul>
    <!-- 해당 카테고리에 속한 상품만 출력 -->
    <c:forEach var="mapping" items="${mappings}">
      <c:if test="${mapping.nbCategory == category.nbCategory}">
        <c:forEach var="product" items="${products}">
          <c:if test="${product.noProduct == mapping.noProduct}">
            <li>${product.nmProduct}</li>
          </c:if>
        </c:forEach>
      </c:if>
    </c:forEach>
  </ul>
</c:forEach>
</body>
</html>
