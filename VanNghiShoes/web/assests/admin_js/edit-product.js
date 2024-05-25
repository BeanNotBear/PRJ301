let pid;

function openEditProductModal() {
    $('#editProductModal').modal('show');
}

function populateopenEditProductModalFields(row) {
    var categoryName = row.cells[1].textContent.trim();

    categoryName = document.getElementById('addCategoryName').value;
}

$(document).ready(function () {

    $('.btn-edit-product').on('click', function (e) {
        e.preventDefault();
        
        // get data from the row
        pid = $(this).closest('tr').find('td:eq(0)').text();
        var name = $(this).closest('tr').find('td:eq(1)').text();
        var category = $(this).closest('tr').find('td:eq(2)').text();
        var price = $(this).closest('tr').find('td:eq(3)').text();
        var discount = $(this).closest('tr').find('td:eq(5)').text();
        var size = $(this).closest('tr').find('td:eq(6)').text();
        var quantity = $(this).closest('tr').find('td:eq(7)').text();
        var img = new Array();
        img[0] = $(this).closest('tr').find('td:eq(8) img').attr('src');

        img[1] = $(this).closest('tr').find('input:eq(1)').val();
        img[2] = $(this).closest('tr').find('input:eq(2)').val();
        img[3] = $(this).closest('tr').find('input:eq(3)').val();
        img[4] = $(this).closest('tr').find('input:eq(4)').val();
        img[5] = $(this).closest('tr').find('input:eq(5)').val();
        var description = $(this).closest('tr').find('input:eq(6)').val();
        var cateId = $(this).closest('tr').find('input:eq(7)').val();
        var discountId = $(this).closest('tr').find('input:eq(8)').val();
        var discountDes = $(this).closest('tr').find('input:eq(9)').val();
        var discountPercent = $(this).closest('tr').find('input:eq(10)').val();
        var sizeId = $(this).closest('tr').find('input:eq(11)').val();
        var createAt = $(this).closest('tr').find('input:eq(0)').val();

        document.getElementById('productName').value = name;
        document.getElementById('productCategoryID').value = cateId;
        price = price.match(/\d/g);
        price = price.join("");
        document.getElementById('productPrice').value = price;
        document.getElementById('productDiscount').value = discountId;
        document.getElementById('productDescription').value = description;
        document.getElementById('productQuantity').value = quantity;
        document.getElementById('sizeId').value = sizeId;

        for (let i = 0; i <= 5; i++) {
            document.getElementById('image' + (i + 1)).value = img[i];
            document.getElementById('img' + (i + 1)).src = img[i];
            document.getElementById('img' + (i + 1)).alt = 'img' + (i + 1);
        }
    });

});

function saveEditProduct() {
    const url = contextPath + 'admin/product?action=edit';
    var productName = document.getElementById('productName').value;
    var cateID = document.getElementById('productCategoryID').value;
    var price = document.getElementById('productPrice').value;
    var discountId = document.getElementById('productDiscount').value;
    var description = document.getElementById('productDescription').value;
    var quantity = document.getElementById('productQuantity').value;
    var sizeId = document.getElementById('sizeId').value;
    
    var images = new Array();
    for (let i = 0; i <= 5; i++) {
        images[i] = document.getElementById('image' + (i + 1)).value;
    }
    console.log(pid);
    var productData = {
        id: pid,
        name: productName,
        cateId: cateID,
        price: price,
        sizeId: sizeId,
        quantity: quantity,
        discountId: discountId,
        description: description,
        img1: images[0],
        img2: images[1],
        img3: images[2],
        img4: images[3],
        img5: images[4],
        img6: images[5]
    };
    $.ajax({
        url: url,
        type: "POST",
        data: productData,
        cache: false,
        success: function (data) {
            if (data.status === "success") {
                Swal.fire({
                    title: "Success!",
                    text: "You edited a product successfully!",
                    icon: "success"
                }).then((result) => {
                    if (result.isConfirmed) {
                        window.location.reload();
                    }
                });
            } else {
                Swal.fire({
                    title: "Oops...",
                    text: "Something was wrong!",
                    icon: "error"
                });
            }
        },
    });
}

function closeEditProductModal() {
    $('#editProductModal').modal('hide');
}

function openEditProductModal() {
    $('#editProductModal').val('');

    $('#editProductModal').modal('show');
}

function closeEditProductModal() {
    $('#editProductModal').modal('hide');
}

$(document).on('click', '#addCategoryBtn', function () {
    openAddModal();
});

$(document).on('click', '#cancelAddBtn', function () {
    closeAddModal();
});

$(document).on('click', '#saveChangesBtn', function () {
    saveChanges();
});

$(document).on('click', '#cancelChangesBtn', function () {
    closeAddModal();
});