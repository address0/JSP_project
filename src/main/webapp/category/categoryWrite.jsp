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
        h2 {
            font-size: 24px;
            color: #111;
            margin-bottom: 20px;
        }

        form {
            background: #fafafa;
            padding: 24px;
            border: 1px solid #ddd;
            border-radius: 8px;
        }

        form p {
            margin-bottom: 16px;
            font-size: 15px;
        }

        input[type="text"],
        input[type="number"],
        select,
        textarea {
            width: 100%;
            padding: 8px 12px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 14px;
            box-sizing: border-box;
            margin-top: 4px;
        }

        select:disabled {
            background-color: #f2f2f2;
            color: #999;
        }

        textarea {
            resize: vertical;
            font-family: "Gowun Dodum", serif;
        }

        button[type="submit"] {
            background-color: #DF41B0;
            color: white;
            border: none;
            padding: 10px 16px;
            font-size: 14px;
            border-radius: 6px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        button[type="submit"]:hover {
            background-color: #c9369c;
        }
	
	</style>
</head>
<body onload="<c:if test='${not empty category}' >onLevelChange()</c:if>">
<jsp:include page="/main/nav.jsp" />

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
</body>
</html>
