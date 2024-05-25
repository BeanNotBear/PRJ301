// view-detail-product.js

$(document).ready(function () {
    // Handle click on "View Detail" button
    $('.btn-view-detail').on('click', function (e) {
        e.preventDefault();

        // Get the data from the row
        var id = $(this).closest('tr').find('td:eq(0)').text();
        var name = $(this).closest('tr').find('td:eq(1)').text();
        var category = $(this).closest('tr').find('td:eq(2)').text();
        var price = $(this).closest('tr').find('td:eq(3)').text();
        var listedPrice = $(this).closest('tr').find('td:eq(4)').text();
        var discount = $(this).closest('tr').find('td:eq(5)').text();
        var size = $(this).closest('tr').find('td:eq(6)').text();
        var quantity = $(this).closest('tr').find('td:eq(7)').text();
        var thumbnail = $(this).closest('tr').find('td:eq(8) img').attr('src');

        var img2 = $(this).closest('tr').find('input:eq(1)').val();
        var img3 = $(this).closest('tr').find('input:eq(2)').val();
        var img4 = $(this).closest('tr').find('input:eq(3)').val();
        var img5 = $(this).closest('tr').find('input:eq(4)').val();
        var img6 = $(this).closest('tr').find('input:eq(5)').val();
        var description = $(this).closest('tr').find('input:eq(6)').val();

        var createAt = $(this).closest('tr').find('input:eq(0)').val();


        // Create HTML for the detail table
        var detailTable = `
            <table class="table">
                <tbody>
                    <tr>
                        <th>ID</th>
                        <td>${id}</td>
                    </tr>
                    <tr>
                        <th>Name</th>
                        <td>${name}</td>
                    </tr>
                    <tr>
                        <th>Category</th>
                        <td>${category}</td>
                    </tr>
                    <tr>
                        <th>Price</th>
                        <td>${price}</td>
                    </tr>
                    <tr>
                        <th>Listed Price</th>
                        <td>${listedPrice}</td>
                    </tr>
                    <tr>
                        <th>Discount</th>
                        <td>${discount}</td>
                    </tr>
                    <tr>
                        <th>Size</th>
                        <td>${size}</td>
                    </tr>
                    <tr>
                        <th>Quantity</th>
                        <td>${quantity}</td>
                    </tr>
                    <tr>
                        <th>Thumbnail</th>
                        <td><img src="${thumbnail}" style="width: 80px; height: 80px" alt="${name}"></td>
                    </tr>
                    <tr>
                        <th>Images</th>
                        <td>
                            <img src="${img2}" style="width: 80px; height: 80px" alt="${name}">
                            <img src="${img3}" style="width: 80px; height: 80px" alt="${name}">
                            <img src="${img4}" style="width: 80px; height: 80px" alt="${name}">
                            <img src="${img5}" style="width: 80px; height: 80px" alt="${name}">
                            <img src="${img6}" style="width: 80px; height: 80px" alt="${name}">
                        </td>
                    </tr>
                    <tr>
                        <th>Create At</th>
                        <td>${createAt}</td>
                    </tr>
        
                    <tr>
                        <th>Description</th>
                        <td>${description}</td>
                    </tr>
                </tbody>
            </table>
        `;

        // Display the detail table in a modal
        $('#detailModal .modal-body').html(detailTable);
        $('#detailModal').modal('show');
    });
});

