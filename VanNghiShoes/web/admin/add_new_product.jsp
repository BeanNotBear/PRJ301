<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" />
        <link href="../assests/admin_css/addNewProduct.css" rel="stylesheet" />
        <link rel="icon" type="image/x-icon" href="<%=request.getContextPath()%>/assests/images/favicon/favicon.png">

        <title>Add new product</title>
    </head>
    <body>
        <div class="container">
            <h1 class="title">ADD NEW PRODUCT</h1>
            <form id="productForm" action="<%=request.getContextPath()%>/admin/add-new-product" method="post">
                <!-- Product Information -->
                <div class="form-group">
                    <label for="productName">Name:</label>
                    <input type="text" id="productName" name="productName" required>
                </div>
                
                <div class="form-group">
                    <label for="productCategoryID">Category:</label>
                    <select class="form-select" type="text" id="productCategoryID" name="productCategoryID" required>
                        <c:forEach var="i" items="${requestScope.categories}">
                            <option value="${i.id}">${i.name}</option>
                        </c:forEach>
                    </select>
                </div>
                
                <div class="form-group">
                    <label for="productPrice">Price:</label>
                    <input type="number" id="productPrice" name="productPrice" required>
                </div>
                
                <div class="form-group">
                    <label for="productSize">Size:</label>
                    <select class="form-select" type="text" id="productSize" name="productSize" required>
                        <c:forEach var="i" items="${requestScope.sizes}">
                            <option value="${i.id}">${i.size}</option>
                        </c:forEach>
                    </select>
                </div>
                
                <div class="form-group">
                    <label for="quantity">Quantity:</label>
                    <input id="quantity" type="number" name="quantity" required>
                </div>
                
                <div class="form-group">
                    <label for="productDiscount">Discount:</label>
                    <select id="productDiscount" name="productDiscount" required>
                        <c:forEach var="i" items="${requestScope.discounts}">
                            <option value="${i.id}">${i.name}</option>
                        </c:forEach>
                    </select>
                </div>
                
                <div class="form-group">
                    <label for="productDescription">Description:</label>
                    <input type="text" id="productDescription" name="productDescription" required>
                </div>

                <!-- Images Upload -->
                <div class="form-group">
                    <label for="images">Images:</label>
                    <div class="form-group" id="images">
                        <c:forEach var="i" begin="1" end="6">
                            <div class="form-group">
                                <label for="${i}">Image ${i}</label>
                                <input type="text" name="image${i}">
                            </div>
                        </c:forEach>
                    </div>
                </div>

                <!-- Submit Button -->
                <div class="form-group">
                    <button type="submit">Submit</button>
                </div>
            </form>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
        <script src="../assests/admin_js/add-new-product.js"></script>
    </body>
</html>