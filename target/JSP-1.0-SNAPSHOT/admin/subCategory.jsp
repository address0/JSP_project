<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:forEach var="category" items="${subCategories}">
  <li onclick="loadSubCategory(${category.nbCategory}, 'sub-category')">
      ${category.nmCategory}
  </li>
</c:forEach>
