<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title><c:choose>
		<c:when test="${not empty category}">카테고리 수정</c:when>
		<c:otherwise>카테고리 생성</c:otherwise>
	</c:choose></title>
	<script>
        function onLevelChange() {
            const level = document.getElementById("cnLevel").value;
            const parentSelect = document.getElementById("nbParentCategory");
            parentSelect.innerHTML = "";
            if (level === "1") {
                parentSelect.disabled = true;
            } else {
                parentSelect.disabled = false;
                const targetLevel = parseInt(level) - 1;
                fetch("/category/level.do?level=" + targetLevel)
                    .then(response => response.json())
                    .then(data => {
                        data.forEach(item => {
                            const option = document.createElement("option");
                            option.value = item.nbCategory;
                            option.text = item.nmCategory;
                            if (item.nbCategory == '${category.nbParentCategory}') {
                                option.selected = true;
                            }
                            parentSelect.appendChild(option);
                        });
                    });
            }
        }
	</script>
</head>
<body onload="<c:if test='${not empty category}' >onLevelChange()</c:if>">

<h2><c:choose>
	<c:when test="${not empty category}">카테고리 수정</c:when>
	<c:otherwise>카테고리 생성</c:otherwise>
</c:choose></h2>

<form action="<c:choose>
    <c:when test='${not empty category}'>update.do</c:when>
    <c:otherwise>create.do</c:otherwise>
</c:choose>" method="post">
	<c:if test="${not empty category}">
		<input type="hidden" name="nbCategory" value="${category.nbCategory}" />
	</c:if>
	
	<p>카테고리명: <input type="text" name="nmCategory" value="${category.nmCategory}" required></p>
	
	<p>레벨 선택:
		<select name="cnLevel" id="cnLevel" onchange="onLevelChange()" required <c:if test="${not empty category}">disabled</c:if>>
			<option value="">선택</option>
			<option value="1" <c:if test="${category.cnLevel == 1}">selected</c:if>>대분류</option>
			<option value="2" <c:if test="${category.cnLevel == 2}">selected</c:if>>중분류</option>
			<option value="3" <c:if test="${category.cnLevel == 3}">selected</c:if>>소분류</option>
		</select>
		<c:if test="${not empty category}">
			<input type="hidden" name="cnLevel" value="${category.cnLevel}" />
		</c:if>
	</p>
	
	<p>상위 카테고리:
		<select name="nbParentCategory" id="nbParentCategory" <c:if test="${category.cnLevel == 1}">disabled</c:if>></select>
	</p>
	
	<p>카테고리 설명: <br>
		<textarea name="nmExplain" rows="4" cols="50">${category.nmExplain}</textarea>
	</p>
	
	<p>사용 여부:
		<select name="ynUse">
			<option value="Y" <c:if test="${category.ynUse == 'Y'}">selected</c:if>>사용</option>
			<option value="N" <c:if test="${category.ynUse == 'N'}">selected</c:if>>미사용</option>
		</select>
	</p>
	
	<p><button type="submit"><c:choose>
		<c:when test="${not empty category}">수정</c:when>
		<c:otherwise>등록</c:otherwise>
	</c:choose></button></p>
</form>
</body>
</html>
