<%--
  Created by IntelliJ IDEA.
  User: kopo
  Date: 2025-05-02
  Time: 오후 7:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.model.User" %>
<html>
<head>
	<title>Profile</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/nav.css?v=<%=System.currentTimeMillis()%>">
</head>
<body>
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
