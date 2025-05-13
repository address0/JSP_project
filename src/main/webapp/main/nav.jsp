<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<nav>
  <img src="../images/logo.png" alt="Logo" id="logo" onclick="location.href='<%= request.getContextPath() %>/main.do'">
  <ul>
    <c:choose>
      <c:when test="${sessionScope.status == 'admin'}">
        <li><a href="<%= request.getContextPath() %>/logout.do">상품 관리</a></li>
        <li><a href="<%= request.getContextPath() %>/logout.do">카테고리 관리</a></li>
        <li><a href="<%= request.getContextPath() %>/logout.do">사용자 관리</a></li>
        <li><a href="<%= request.getContextPath() %>/logout.do">주문</a></li>
      </c:when>
      <c:otherwise>
        <li><a href="<%= request.getContextPath() %>/logout.do">카테고리</a></li>
        <li><a href="<%= request.getContextPath() %>/logout.do">장바구니</a></li>
        <li><a href="<%= request.getContextPath() %>/logout.do">주문내역</a></li>
        <li><a href="<%= request.getContextPath() %>/logout.do">마이페이지</a></li>
      </c:otherwise>
    </c:choose>
  </ul>
  <c:choose>
    <c:when test="${not empty sessionScope.status}">
      <span class="material-symbols-outlined" id="userIcon">account_circle</span>
      <div class="user-dropdown" id="userDropdown">
        <p><strong>${requestScope.user.nmUser}</strong> 님</p>
        <a href="<%= request.getContextPath() %>/user/mypage.do">마이페이지</a>
        <a href="<%= request.getContextPath() %>/user/logout.do">로그아웃</a>
      </div>
    </c:when>
    <c:otherwise>
      <a href="<%= request.getContextPath() %>/user/loginForm.do">로그인</a>
    </c:otherwise>
  </c:choose>
</nav>
<script>
  const icon = document.getElementById('userIcon');
  const dropdown = document.getElementById('userDropdown');
  let timeoutId;
  
  if (icon && dropdown) {
    const hideDropdown = () => {
      timeoutId = setTimeout(() => {
        dropdown.style.display = 'none';
      }, 200);
    };
    
    const showDropdown = () => {
      clearTimeout(timeoutId);
      dropdown.style.display = 'block';
    };
    
    icon.addEventListener('mouseenter', showDropdown);
    dropdown.addEventListener('mouseenter', showDropdown);
    
    icon.addEventListener('mouseleave', hideDropdown);
    dropdown.addEventListener('mouseleave', hideDropdown);
  }
</script>
