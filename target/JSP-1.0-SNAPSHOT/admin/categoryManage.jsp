<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h1>카테고리 관리</h1>
<button onclick="location.href='/category/form.do'">카테고리 추가</button>

<div class="category-wrapper">
	<div class="category-column">
		<h3>대분류</h3>
		<ul class="category-list" id="top-category">
			<c:forEach var="category" items="${mainCategories}">
				<li onclick="loadSubCategory('${category.nbCategory}', 'middle-category')">
					<span>${category.nmCategory}</span>
					<button class="disabled" id="update-btn-${category.nbCategory}"
					onclick="location.href='updateForm.do?id=${category.nbCategory}'">수정</button>
				</li>
			</c:forEach>
		</ul>
	</div>
	
	<div class="category-column">
		<h3>중분류</h3>
		<ul class="category-list" id="middle-category"></ul>
	</div>
	
	<div class="category-column">
		<h3>소분류</h3>
		<ul class="category-list" id="sub-category"></ul>
	</div>
</div>

