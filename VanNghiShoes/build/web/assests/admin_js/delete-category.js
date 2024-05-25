//let categoryId;
let categoryName;

function openDeleteModal() {
    $('#deleteCategoryModal').modal('show');
}

function openDeleteModel(id, name) {
    categoryId = id;
    categoryName = name;
    openDeleteModal();
}

function saveChangesDeleteCategory() {

    // Get the category ID from the row or another source

    const url = contextPath + '/admin/category?action=delete&id=' + categoryId + '&name=' + categoryName;

    $.ajax({
        url: url,
        type: "POST",
        dataType: "json",
        success: function (data) {
            if (data.status === "success") {
                Swal.fire({
                    title: "Success!",
                    text: "Category information deleted successfully!",
                    icon: "success"
                }).then((result) => {
                    if (result.isConfirmed) {
                        window.location.reload();
                    }
                });
            } else {
                Swal.fire({
                    title: "Oops...",
                    text: "Something went wrong!",
                    icon: "error"
                });
            }
        },
    });
}

function closeDeleteCategoryModal() {
    $('#deleteCategoryModal').modal('hide');
}

$(document).on('click', '#cancelEditBtn', function () {
    closeDeleteModal();
});

$(document).on('click', '#saveCategoryChangesBtn', function () {
    saveDeleteChanges();
});