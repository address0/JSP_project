<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>카테고리 등록</title>
	<script>
        function onLevelChange() {
            const level = document.getElementById("cnLevel").value;
            const parentSelect = document.getElementById("nbParentCategory");

            parentSelect.innerHTML = "";

            if (level === "1") {
                // 대분류는 상위 카테고리 없음
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
                            parentSelect.appendChild(option);
                        });
                    });
            }
        }
	</script>
</head>
<body>
<h2>카테고리 생성</h2>

<form action="create.do" method="post">
	<p>카테고리명: <input type="text" name="nmCategory" required></p>
	
	<p>레벨 선택:
		<select name="cnLevel" id="cnLevel" onchange="onLevelChange()" required>
			<option value="">선택</option>
			<option value="1">대분류</option>
			<option value="2">중분류</option>
			<option value="3">소분류</option>
		</select>
	</p>
	
	<p>상위 카테고리:
		<select name="nbParentCategory" id="nbParentCategory" disabled></select>
	</p>
	
	<p>카테고리 설명: <br>
		<textarea name="nmExplain" rows="4" cols="50"></textarea>
	</p>
	
	<p>사용 여부:
		<select name="ynUse">
			<option value="Y">사용</option>
			<option value="N">미사용</option>
		</select>
	</p>
	
	<input type="hidden" name="ynDelete" value="N">
	
	<p><button type="submit">등록</button></p>
</form>
</body>
</html>
