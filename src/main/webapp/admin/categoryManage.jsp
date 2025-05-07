<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h1>카테고리 관리</h1>

<style>
    .category-wrapper {
        display: flex;
        width: 100%;
    }

    .category-column {
        width: 30%;
        padding: 10px;
        border: 1px solid #ddd;
        background-color: #fff;
        margin-right: 10px;
        border-radius: 5px;
        min-height: 300px;
    }

    .category-column h3 {
        font-size: 1.2em;
        border-bottom: 1px solid #ccc;
        padding-bottom: 5px;
        margin-bottom: 10px;
    }

    ul.category-list {
        list-style: none;
        padding: 0;
    }

    ul.category-list li {
        padding: 6px 10px;
        margin-bottom: 5px;
        border: 1px solid #ccc;
        border-radius: 4px;
        cursor: pointer;
        background-color: #f9f9f9;
        transition: background-color 0.2s ease;
    }

    ul.category-list li:hover {
        background-color: #e0f7ff;
    }

    ul.category-list li.selected {
        background-color: #007bff;
        color: white;
        font-weight: bold;
        border-color: #007bff;
    }

</style>

<div class="category-wrapper">
	<div class="category-column">
		<h3>대분류</h3>
		<ul class="category-list" id="top-category">
			<c:forEach var="category" items="${mainCategories}">
				<li onclick="loadSubCategory(${category.nbCategory}, 'middle-category')">
						${category.nmCategory}
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

<script>
    function loadSubCategory(parentId, targetId) {
        // 1. 이전에 선택된 항목들의 .selected 제거
        const targetList = document.getElementById(targetId);
        const allLi = targetList ? targetList.querySelectorAll('li') : [];
        allLi.forEach(li => li.classList.remove('selected'));

        // 2. 클릭된 항목에 .selected 부여
        //    대분류 또는 중분류에서 호출되므로 두 영역 다르게 처리
        const eventTargetListId = (targetId === 'middle-category') ? 'top-category' : 'middle-category';
        const eventList = document.getElementById(eventTargetListId);
        if (eventList) {
            const liList = eventList.querySelectorAll('li');
            liList.forEach(li => {
                li.onclick = () => {
                    liList.forEach(l => l.classList.remove('selected'));
                    li.classList.add('selected');
                };
            });
        }

        // 3. 실제 fetch 로직
        fetch('/category/subList.do?parentId=' + parentId)
            .then(response => response.text())
            .then(html => {
                document.getElementById(targetId).innerHTML = html;

                if (targetId === 'middle-category') {
                    document.getElementById('sub-category').innerHTML = '';
                }
            });
    }

</script>

