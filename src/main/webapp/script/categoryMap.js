function selectProduct(id, name, idFile) {
    document.getElementById('selectedProductId').value = id;
    document.getElementById('productName').innerText = name;
    document.getElementById('productImage').src = "/product/image.do?idFile=" + idFile;

    updateCategoryCheckboxesForProduct(id)
    updateSelectedCategoryList();
}

function updateSelectedCategoryList() {
    const selectedDiv = document.getElementById('selectedCategories');
    const checkboxes = document.querySelectorAll('input[name="categoryList"]');
    selectedDiv.innerHTML = '';
    checkboxes.forEach(cb => {
        if (cb.checked) {
            const label = cb.parentElement.textContent.trim();
            selectedDiv.innerHTML += `<p>${label}</p>`;
        }
    });
}

// 선택된 카테고리 UI 출력
document.addEventListener('DOMContentLoaded', function () {

    const checkboxes = document.querySelectorAll('input[name="categoryList"]');
    const selectedDiv = document.getElementById('selectedCategories');

    checkboxes.forEach(checkbox => {
        checkbox.addEventListener('change', updateSelectedCategoryList);
        checkbox.addEventListener('change', () => {
            selectedDiv.innerHTML = '';
            checkboxes.forEach(cb => {
                if (cb.checked) {
                    const label = cb.parentElement.textContent.trim();
                    selectedDiv.innerHTML += `<p>${label}</p>`;
                }
            });
        });
    });
});

function validateForm() {
    const productId = document.getElementById('selectedProductId').value;
    if (!productId) {
        alert('상품을 선택해주세요.');
        return false;
    }
    return true;
}

function updateCategoryCheckboxesForProduct(productId) {
    const checkboxes = document.querySelectorAll('input[name="categoryList"]');

    checkboxes.forEach(cb => cb.checked = false);

    const productCategoryMap = window.categoryMapData[productId] || [];

    checkboxes.forEach(cb => {
        const catId = cb.value;
        if (productCategoryMap.includes(parseInt(catId))) {
            cb.checked = true;
        }
    });
}
