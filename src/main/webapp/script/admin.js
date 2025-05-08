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