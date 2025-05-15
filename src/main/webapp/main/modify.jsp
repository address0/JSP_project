<%@ page import="com.example.model.User" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
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
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/admin.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/nav.css?v=<%= System.currentTimeMillis() %>">
    <style>
        /* 프로필 및 수정 박스 */
        #profile-container, #edit-container {
            max-width: 480px;
            margin: 20px auto;
            padding: 24px;
            background-color: #fff;
            border-radius: 12px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
            font-size: 15px;
        }
        
        #profile-container p,
        #edit-container label {
            margin-bottom: 12px;
            display: block;
            color: #222;
            font-weight: 500;
        }
        
        #profile-container p strong {
            font-weight: bold;
            margin-right: 4px;
        }
        
        /* 입력 필드 */
        #edit-container input[type="text"],
        #edit-container input[type="email"] {
            width: 100%;
            padding: 10px 12px;
            border: 1px solid #ddd;
            border-radius: 6px;
            font-size: 14px;
            margin-bottom: 14px;
        }
        
        /* 버튼 공통 */
        #profile-container button,
        #edit-container button {
            padding: 10px 16px;
            font-size: 14px;
            margin: 10px 6px 0 0;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            background-color: #FF4DCA;
            color: white;
            transition: background-color 0.3s ease;
        }
        
        #profile-container button:hover,
        #edit-container button:hover {
            background-color: #DF41B0;
        }
        
        /* 탈퇴 버튼 - 보조색 */
        #profile-container button:last-of-type {
            background-color: #e0e0e0;
            color: #333;
        }
        
        #profile-container button:last-of-type:hover {
            background-color: #ccc;
        }
        
        /* 제목 */
        h1, h2 {
            text-align: center;
            font-size: 22px;
            margin-top: 40px;
        }
    
    </style>
</head>
<body>
<jsp:include page="/main/nav.jsp" />
<%
    User user = (User) request.getAttribute("user");
    if (user == null) {
        response.sendRedirect(request.getContextPath() + "/modify?action=profile");
        return;
    }
%>
<button onclick="location.href='<%= request.getContextPath() %>/user/logout.do'">로그아웃</button>
<h1>내 정보</h1>
<div id="profile-container">
    <p><strong>아이디:</strong> <%= user.getIdUser() %></p>
    <p><strong>이름:</strong> <%= user.getNmUser() %></p>
    <p><strong>이메일:</strong> <%= user.getNmEmail() %></p>
    <p><strong>전화번호:</strong> <%= user.getNoMobile() %></p>
    <p><strong>상태:</strong>
        <%
            String status = user.getStStatus();
            String statusLabel = switch (status) {
                case "ST00" -> "가입 요청";
                case "ST01" -> "정상";
                case "ST02" -> "탈퇴 요청";
                case "ST03" -> "일시정지";
                default -> "알 수 없음";
            };
        %>
        <%= statusLabel %></p>
    <button onclick="showEditForm()">수정</button>
    <button onclick="location.href='<%= request.getContextPath() %>/modify?action=delete&id=<%= user.getIdUser() %>'">탈퇴 신청</button>
</div>
<div id="edit-container" style="display: none;">
    <h2>정보 수정</h2>
    <form action="<%= request.getContextPath() %>/modify" method="post">
        <input type="hidden" name="id" value="<%= user.getIdUser() %>" readonly>
        <label for="username">이름:</label>
        <input type="text" id="username" name="username" value="<%= user.getNmUser() %>" required>
        <br>
        <label for="email">이메일:</label>
        <input type="email" id="email" name="email" value="<%= user.getNmEmail() %>" required>
        <br>
        <label for="phone">전화번호:</label>
        <input type="text" id="phone" name="phone" value="<%= user.getNoMobile() %>" required>
        <br>
        <button type="submit">수정 완료</button>
    </form>
    <button onclick="showEditForm()">취소</button>
</div>
<script>
  let form = "profile"
  function showEditForm() {
    if (form === "profile") {
      document.getElementById('edit-container').style.display = 'block';
      document.getElementById('profile-container').style.display = 'none';
      form = "edit";
    } else {
      document.getElementById('edit-container').style.display = 'none';
      document.getElementById('profile-container').style.display = 'block';
      form = "profile";
    }
  }
</script>
</body>
</html>
