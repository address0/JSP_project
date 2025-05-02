<%--
  Created by IntelliJ IDEA.
  User: kopo
  Date: 2025-05-02
  Time: 오후 7:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Modify Result</title>
</head>
<body>
<%
    String modifyResult = request.getParameter("result");
    if ("success".equals(modifyResult)) {
%>
    <h1>수정 완료</h1>
    <p>정보가 성공적으로 수정되었습니다.</p>
    <button onclick="location.href='<%= request.getContextPath() %>/modify?action=profile&id=<%= session.getAttribute("id") %>'">내 정보</button>
<%
    } else if ("fail".equals(modifyResult)) {
%>
    <h1>수정 실패</h1>
    <p>정보 수정에 실패했습니다. 다시 시도하세요.</p>
    <button onclick="location.href='<%= request.getContextPath() %>/modify?action=profile&id=<%= session.getAttribute("id") %>'">내 정보</button>
<%
    } else if ("delete".equals(modifyResult)) {
%>
    <h1>탈퇴 신청 완료</h1>
    <p>탈퇴 신청이 완료되었습니다. 확인 후 처리하겠습니다.</p>
    <button onclick="location.href = '<%= request.getContextPath() %>/index.jsp'">메인 페이지</button>
<%
    } else {
%>
    <h1>알 수 없는 오류</h1>
    <p>알 수 없는 오류가 발생했습니다. 다시 시도하세요.</p>
    <button onclick="location.href='<%= request.getContextPath() %>/modify?action=profile&id=<%= session.getAttribute("id") %>'">내 정보</button>
<%
	} %>
</body>
</html>
