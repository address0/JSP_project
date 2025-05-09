<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:forEach var="category" items="${subCategories}">
  <li onclick="loadSubCategory('${category.nbCategory}', 'sub-category')" data-id="${category.nbCategory}">
    <span>${category.nmCategory}</span>
    <button class="disabled" id="update-btn-${category.nbCategory}"
            onclick="location.href='updateForm.do?id=${category.nbCategory}'">수정</button>
  </li>
</c:forEach>
