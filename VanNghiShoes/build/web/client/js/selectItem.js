function selectItem(id, sizeId) {
    var url = contextPath + '/cart?action=addYield';
    var quantity = document.getElementById('sst').value;
    var formData = {
        id: id,
        sizeId: sizeId,
        quantity: quantity
    };
    $.ajax({
        url: url,
        type: "GET",
        data: formData,
        cache: false,
        success: function (data) {
            Swal.fire({
                title: "Success!",
                text: "You added an item to the cart successfully!",
                icon: "success"
            });
        },
        error: function (xhr, status, error) {

        }
    });
}

function deleteItem(productId, sizeId) {
    var url = contextPath + '/cart?action=deleteYield';
    var formData = {
        productId: productId,
        sizeId: sizeId
    };

    Swal.fire({
        title: "Delete this item?",
        text: "You really want to delete this item?",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Delete!",
        cancelButtonText: "No"
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                url: url,
                type: "GET",
                data: formData,
                cache: false,
                success: function (data) {
                        Swal.fire({
                            title: "Success!",
                            text: "You delete item success!",
                            icon: "success"
                        }).then((result) => {
                            if (result.isConfirmed) {
                                window.location.reload();
                            }
                        });
                },
                error: function () {
                    Swal.fire({
                        title: "Oops...",
                        text: "Something was wrong when send the request!",
                        icon: "error"
                    });
                }
            });
        }
    });
}