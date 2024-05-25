function openAddProductSizeModal() {
    $('#addProductSizeModal').modal('show');
}

function populateAddModalFields(row) {
    var productName = row.cells[1].textContent.trim();

    productName = document.getElementById('addProductName').value;
}

function saveChangesAddProductSize() {
    // Collect form data
    let productId = document.getElementById('productName-Size').value;
    let sizeId = document.getElementById('productSize-Size').value;
    let productQuantity = document.getElementById('productQuantity-Size').value;
    


    // Prepare form data
    let formData = {
        productIdSize: productId,
        sizeIdSize: sizeId,
        productQuantitySize: productQuantity
    };
    
    var  url = contextPath + 'admin/product?action=add-product-size';
    console.log(url);

    // Send form data via AJAX
    $.ajax({
        url: url,
        type: "POST",
        data: formData,
        cache: false,
        success: function (data) {
            if (data.status === "success") {
                Swal.fire({
                    title: "Success!",
                    text: "You added a new product size successfully!",
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
        }
    });
}


function closeAddProductSizeModal() {
    $('#addProductSizeModal').modal('hide');
}

function openAddProductSizeModal() {
    $('#addProductSizeName').val('');

    $('#addProductSizeModal').modal('show');
}

function closeAddProductSizeModal() {
    $('#addProductSizeModal').modal('hide');
}

$(document).on('click', '#addProductBtn', function () {
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
