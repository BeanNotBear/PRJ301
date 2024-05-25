function openAddModal() {
    $('#addProductModal').modal('show');
}

function populateAddModalFields(row) {
    var productName = row.cells[1].textContent.trim();

    productName = document.getElementById('addProductName').value;
}

function saveChangesAddProduct() {
    // Collect form data
    let productName = document.getElementById('productName-add').value;
    let productCategoryID = document.getElementById('productCategoryID-add').value;
    let productPrice = document.getElementById('productPrice-add').value;
    let productDiscount = document.getElementById('productDiscount-add').value;
    let productDescription = document.getElementById('productDescription-add').value;
    let img1 = document.getElementById('image1-add').value;
    let img2 = document.getElementById('image2-add').value;
    let img3 = document.getElementById('image3-add').value;
    let img4 = document.getElementById('image4-add').value;
    let img5 = document.getElementById('image5-add').value;
    let img6 = document.getElementById('image6-add').value;


    // Prepare form data
    let formData = {
        productName: productName,
        productCategoryID: productCategoryID,
        productPrice: productPrice,
        productDiscount: productDiscount,
        productDescription: productDescription,
        image1: img1,
        image2: img2,
        image3: img3,
        image4: img4,
        image5: img5,
        image6: img6
    };
    
    var  url = contextPath + 'admin/product?action=add';
    console.log(url);

    // Send form data via AJAX
    $.ajax({
        url: url, // Replace 'your-servlet-url' with the actual URL of your servlet
        type: "POST",
        data: formData,
        cache: false,
//        dataType: "json",
        success: function (data) {
            if (data.status === "success") {
                Swal.fire({
                    title: "Success!",
                    text: "You added a new product successfully!",
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


function closeAddModal() {
    $('#addProductModal').modal('hide');
}

function openAddModal() {
    $('#addProductName').val('');

    $('#addProductModal').modal('show');
}

function closeAddModal() {
    $('#addProductModal').modal('hide');
}

// jquery
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
