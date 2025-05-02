<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.bean.User" %>
<h1>회원 관리</h1>
<table>
	<thead>
	<tr>
		<th>아이디</th>
		<th>이름</th>
		<th>이메일</th>
		<th>휴대폰</th>
		<th>상태</th>
		<th>관리</th>
	</tr>
	</thead>
	<tbody>
	<%
		List<User> userList = (List<User>) request.getAttribute("userList");
		if (userList != null && !userList.isEmpty()) {
			for (User user : userList) {
	%>
	<tr>
		<td><%= user.getIdUser() %></td>
		<td><%= user.getNmUser() %></td>
		<td><%= user.getNmEmail() %></td>
		<td><%= user.getNoMobile() %></td>
		<td>
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
			<%= statusLabel %>
		</td>
		<td>
			<%
				String statusAction = switch (status) {
					case "ST00" -> "<button onclick=\"location.href='userAction?action=approve&idUser=" + user.getIdUser() + "'\">승인</button>";
					case "ST01" -> "<button onclick=\"location.href='userAction?action=suspend&idUser=" + user.getIdUser() + "'\">일시정지</button>";
					case "ST02" -> "<button onclick=\"location.href='userAction?action=delete&idUser=" + user.getIdUser() + "'\">탈퇴</button>";
					case "ST03" -> "<button onclick=\"location.href='userAction?action=activate&idUser=" + user.getIdUser() + "'\">활성화</button>";
					default -> "";
				};
			%>
			<%= statusAction %>
		</td>
	</tr>
	<%
		}
	} else {
	%>
	<tr>
		<td colspan="6">등록된 사용자가 없습니다.</td>
	</tr>
	<%
		}
	%>
	</tbody>
</table>