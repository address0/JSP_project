function loadSubCategory(clickedLi, targetId) {
    const parentId = clickedLi.dataset.id;

    // 1. 기존 선택된 li 초기화
    const currentList = clickedLi.parentElement;
    const allSiblings = currentList.querySelectorAll('li');

    allSiblings.forEach(li => {
        li.classList.remove('selected');

        // 🔥 버튼도 같이 초기화 (모두 비활성화)
        const btn = li.querySelector('button[id^="update-btn-"]');
        if (btn) {
            btn.classList.add('disabled');
        }
    });

    // 2. 현재 li만 selected 및 버튼 활성화
    clickedLi.classList.add('selected');

    const currentBtn = clickedLi.querySelector('button[id^="update-btn-"]');
    if (currentBtn) {
        currentBtn.classList.remove('disabled');
    }

    // 3. 하위 카테고리 fetch
    fetch('/category/subList.do?parentId=' + parentId)
        .then(response => response.text())
        .then(html => {
            document.getElementById(targetId).innerHTML = html;

            // 중분류 클릭 시 소분류 초기화
            if (targetId === 'middle-category') {
                document.getElementById('sub-category').innerHTML = '';
            }

            // 4. 새로 생성된 li에 이벤트 부여
            const newLiList = document.getElementById(targetId).querySelectorAll('li');
            newLiList.forEach(li => {
                li.addEventListener('click', function () {
                    // 형제 li 초기화
                    newLiList.forEach(l => {
                        l.classList.remove('selected');
                        const btn = l.querySelector('button[id^="update-btn-"]');
                        if (btn) {
                            btn.classList.add('disabled');
                        }
                    });

                    // 현재 li 선택 및 버튼 활성화
                    this.classList.add('selected');
                    const btn = this.querySelector('button[id^="update-btn-"]');
                    if (btn) {
                        btn.classList.remove('disabled');
                    }

                    // 중분류이면 소분류 로드
                    if (targetId === 'middle-category') {
                        loadSubCategory(this, 'sub-category');
                    }
                });
            });
        });
}
