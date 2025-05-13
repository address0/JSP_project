function selectProduct(id, name, idFile) {
    document.getElementById('selectedProductId').value = id;
    document.getElementById('productName').innerText = name;
    document.getElementById('productImage').src = "/product/image.do?idFile=" + idFile;
}

// 선택된 카테고리 UI 출력
document.addEventListener('DOMContentLoaded', function () {
    const checkboxes = document.querySelectorAll('input[name="categoryList"]');
    const selectedDiv = document.getElementById('selectedCategories');

    checkboxes.forEach(checkbox => {
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