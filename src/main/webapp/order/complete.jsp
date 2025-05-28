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
  <link rel="stylesheet" href="<%= request.getContextPath() %>/css/nav.css?v=<%= System.currentTimeMillis() %>">
  <link href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap" rel="stylesheet">
  <style>
    .complete-box {
      background: white;
      border-radius: 10px;
      padding: 40px;
      max-width: 600px;
      margin-top: 40px;
      box-shadow: 0 0 10px rgba(0,0,0,0.1);
    }

    h2 {
      color: #333;
      margin-bottom: 20px;
    }

    p {
      font-size: 18px;
      margin-bottom: 30px;
    }

    .btn-group {
      display: flex;
      justify-content: center;
      gap: 20px;
    }

    .btn {
      padding: 12px 20px;
      font-size: 16px;
      text-decoration: none;
      border-radius: 5px;
      border: none;
      cursor: pointer;
      background: #111;
      color: white;
      transition: 0.2s;
    }

    .btn:hover {
      background: #333;
    }
  </style>
</head>
<body>
<jsp:include page="/main/nav.jsp" />
<div class="complete-box">
  <h2>✅ 주문이 완료되었습니다!</h2>
  <p>고객님의 주문이 성공적으로 접수되었습니다.<br>빠른 시일 내에 배송해드리겠습니다.</p>

  <div class="btn-group">
    <a href="${pageContext.request.contextPath}/main.do" class="btn">홈으로</a>
    <a href="${pageContext.request.contextPath}/order/list.do" class="btn">주문 내역 확인</a>
  </div>
</div>

</body>
</html>

