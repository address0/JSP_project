function loadSubCategory(parentId, targetId) {
    console.log(parentId)
    btnView(parentId)
    const targetList = document.getElementById(targetId);
    const allLi = targetList ? targetList.querySelectorAll('li') : [];
    allLi.forEach(li => li.classList.remove('selected'));
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

    fetch('/category/subList.do?parentId=' + parentId)
        .then(response => response.text())
        .then(html => {
            document.getElementById(targetId).innerHTML = html;

            if (targetId === 'middle-category') {
                document.getElementById('sub-category').innerHTML = '';
            }
        });
}

function btnView(btnNum) {
    console.log(btnNum)
    const btn = document.getElementById('update-btn-' + btnNum);
    if (btn) {
        if (btn.classList.contains('disabled')) {
            btn.classList.remove('disabled');
        } else {
            btn.classList.add('disabled');
        }
    }
}