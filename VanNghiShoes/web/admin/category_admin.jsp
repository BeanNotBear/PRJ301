<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <link rel="icon" href="../assests/images/favicon/favicon.png" type="image/ico" />
        <title>Category Management</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
        <link href="../assests/admin_css/style.css" rel="stylesheet" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    </head>

    <body class="sb-nav-fixed">
        <nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
            <!-- Navbar Brand-->
            <a class="navbar-brand ps-3" href="<%=request.getContextPath()%>/admin/dashboard">Welcome Admin</a>
            <!-- Sidebar Toggle-->
            <button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0" id="sidebarToggle" href="#!"><i
                    class="fas fa-bars"></i></button>
            <!-- Navbar Search-->
            <form class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0">
            </form>
            <!-- Navbar-->
            <ul class="navbar-nav ms-auto ms-md-0 me-3 me-lg-4">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown"
                       aria-expanded="false"><i class="fas fa-user fa-fw"></i></a>
                    <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" href="<%=request.getContextPath()%>/admin/logout">Logout</a></li>
                    </ul>
                </li>
            </ul>
        </nav>
        <div id="layoutSidenav">
            <div id="layoutSidenav_nav">
                <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
                    <div class="sb-sidenav-menu">
                        <div class="nav">
                            <div class="sb-sidenav-menu-heading">Core</div>
                            <a class="nav-link" href="<%=request.getContextPath()%>/admin/dashboard">
                                <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                                Dashboard
                            </a>
                            <a class="nav-link" href="<%=request.getContextPath()%>/admin/user">
                                <div class="sb-nav-link-icon"><i class="fas fa-table"></i></div>
                                User Management
                            </a>
                            <a class="nav-link" href="<%=request.getContextPath()%>/admin/order">
                                <div class="sb-nav-link-icon"><i class="fas fa-table"></i></div>
                                Order Management
                            </a>
                            <a class="nav-link" href="<%=request.getContextPath()%>/admin/category">
                                <div class="sb-nav-link-icon"><i class="fas fa-table"></i></div>
                                Category Management
                            </a>
                            <a class="nav-link" href="<%=request.getContextPath()%>/admin/product">
                                <div class="sb-nav-link-icon"><i class="fas fa-table"></i></div>
                                Product Management
                            </a>
                        </div>
                    </div>
                    <div class="sb-sidenav-footer">
                        <div class="small">Logged in as:</div>
                        <c:if test="${sessionScope.account.role.toString() eq 'admin'}">
                            Admin
                        </c:if>
                        <c:if test="${sessionScope.account.role.toString() eq 'Super_admin'}">
                            Super Admin
                        </c:if>
                    </div>
                </nav>
            </div>
            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4">
                        <h1 class="mt-4">List Category</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item active">Admin Role</li>
                        </ol>
                        <div class="add-action mb-2">
                            <button class="btn btn-success waves-effect" onclick="openAddModal()">
                                <i class="fa fa-plus-circle"></i>Add New Category
                            </button>

                        </div>
                        <div class="card mb-4">
                            <div class="card-header">
                                <i class="fas fa-table me-1"></i>
                                Category Table
                            </div>
                            <div class="card-body">
                                <table id="datatablesSimple" class="datatable-table" >
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>Name</th>
                                            <th>Actions</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="c" items="${requestScope.categories}">                  
                                            <tr>
                                                <td>${c.id}</td>
                                                <td>${c.name}</td>
                                                <td>
                                                    <button class="btn btn-info btn-tiny" title="Edit" onclick="openEditModel(${c.id})">
                                                        <i class="fa fa-pencil"></i>
                                                    </button>
                                                        <button href="#" class="btn btn-danger btn-tiny" title="Delete" onclick="openDeleteModel(${c.id}, '${c.name}')">
                                                        <i class="fa fa-trash"></i>
                                                    </button>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </main>
                <footer class="py-4 bg-light mt-auto">
                    <div class="container-fluid px-4">
                        <div class="d-flex align-items-center justify-content-between small">
                            <div class="text-muted">Copyright &copy; Group1's Website 2023</div>
                            <div>
                                <a href="#">Privacy Policy</a>
                                &middot;
                                <a href="#">Terms &amp; Conditions</a>
                            </div>
                        </div>
                    </div>
                </footer>
            </div>
        </div>

        <!-- Edit Category Modal -->
        <div class="modal fade" id="editCategoryModal" tabindex="-1" role="dialog" aria-labelledby="editCategoryModalLabel"
             aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <!-- Modal Header -->
                    <div class="modal-header">
                        <h5 class="modal-title" id="editCategoryModalLabel">Edit Category</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"
                                onclick="closeEditCategoryModal()">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <!-- Modal Body -->

                    <div class="modal-body">
                        <form>
                            <div class="form-group">
                                <label for="editCategoryName">New Category Name:</label>
                                <input type="text" class="form-control" id="editCategoryName" name="CategoryNameNew">
                            </div>
                        </form>
                    </div>
                    <!-- Modal Footer -->
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" onclick="saveChangesEditCategory()">Save changes</button>
                        <button type="button" class="btn btn-secondary" data-dismiss="modal"
                                onclick="closeEditCategoryModal()">Cancel</button>
                    </div>
                </div>
            </div>
        </div>



         <!--Add Category Modal--> 
        <div class="modal fade" id="addCategoryModal" tabindex="-1" role="dialog" aria-labelledby="addCategoryModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                     <!--Modal Header--> 
                    <div class="modal-header">
                        <h5 class="modal-title" id="addCategoryModalLabel">Add New Category</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close" onclick="closeAddModal()">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                     <!--Modal Body--> 
                    <div class="modal-body">
                        <form>
                            <div class="form-group">
                                <label for="addCategoryName">Category Name:</label>
                                <input type="text" class="form-control" id="addCategoryName" required>
                            </div>

                        </form>
                    </div>
                     <!--Modal Footer--> 
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" onclick="saveChangesAddCategory()">Add New Category</button>
                        <button type="button" class="btn btn-secondary" data-dismiss="modal" onclick="closeAddModal()">Cancel</button>
                    </div>
                </div>
            </div>
        </div>

        <!--Delete category-->
        <div class="modal fade" id="deleteCategoryModal" tabindex="-1" role="dialog" aria-labelledby="deleteCategoryModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <!-- Modal Header -->
                    <div class="modal-header">
                        <h5 class="modal-title" id="deleteCategoryModalLabel">Delete Category</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close" onclick="closeDeleteCategoryModal()">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <!-- Modal Body -->
                    <div class="modal-body">
                        <div class="form-group">
                            Do you want to delete this category?
                        </div>
                    </div>
                    <!-- Modal Footer -->
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" onclick="saveChangesDeleteCategory()">Delete Category</button>
                        <button type="button" class="btn btn-secondary" data-dismiss="modal" onclick="closeDeleteCategoryModal()">Cancel</button>
                    </div>
                </div>
            </div>
        </div>

        <script>
            let contextPath = '<%= request.getContextPath()%>/';
        </script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script src="../assests/admin_js/scripts.js"></script>
        <script src="../assests/admin_js/add-category.js"></script>
        <script src="../assests/admin_js/delete-category.js"></script>
        <script src="../assests/admin_js/edit-category.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js"></script>
    </body>
</html>