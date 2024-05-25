<!DOCTYPE html>
<html lang="zxx" class="no-js">

    <head>
        <!-- Mobile Specific Meta -->
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <!-- Favicon-->
        <link rel="shortcut icon" href="<%=request.getContextPath()%>/client/img/fav.png">
        <!-- Author Meta -->
        <meta name="author" content="CodePixar">
        <!-- Meta Description -->
        <meta name="description" content="">
        <!-- Meta Keyword -->
        <meta name="keywords" content="">
        <!-- meta character set -->
        <meta charset="UTF-8">
        <!-- Site Title -->
        <title>Karma Shop</title>

        <!--
                CSS
                ============================================= -->
        <link href="
              https://cdn.jsdelivr.net/npm/sweetalert2@11.10.6/dist/sweetalert2.min.css
              " rel="stylesheet">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/client/css/linearicons.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/client/css/owl.carousel.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/client/css/font-awesome.min.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/client/css/themify-icons.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/client/css/nice-select.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/client/css/nouislider.min.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/client/css/bootstrap.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/client/css/main.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    </head>

    <body>

        <!-- Start Header Area -->
        <%@include file="header.jsp"%>
        <!-- End Header Area -->

        <!-- Start Banner Area -->
        <section class="banner-area organic-breadcrumb">
            <div class="container">
                <div class="breadcrumb-banner d-flex flex-wrap align-items-center justify-content-end">
                    <div class="col-first">
                        <h1>Shopping Cart</h1>
                        <nav class="d-flex align-items-center">
                            <a href="index.html">Home<span class="lnr lnr-arrow-right"></span></a>
                            <a href="category.html">Cart</a>
                        </nav>
                    </div>
                </div>
            </div>
        </section>
        <!-- End Banner Area -->

        <!--================Cart Area =================-->
        <section class="cart_area">
            <div class="container">
                <div class="cart_inner">
                    <div class="table-responsive">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th scope="col">Product</th>
                                    <th scope="col">Price</th>
                                    <th scope="col">Quantity</th>
                                    <th scope="col">Total</th>
                                    <th scope="col">Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="i" items="${requestScope.cart}">
                                    <tr>
                                        <td>
                                            <div class="media">
                                                <div class="d-flex">
                                                    <img width="120px" src="${i.product.img1}" alt="">
                                                </div>
                                                <div class="media-body">
                                                    <p><i>${i.product.name}</i></p>
                                                    <p><span><b>Size: </b></span>${i.size.size}</p>
                                                </div>
                                            </div>
                                        </td>
                                        <td>
                                            <h5>${i.product.listedPrice} VND</h5>
                                        </td>
                                        <td>
                                            <div class="product_count">
                                                <input type="text" name="qty" id="${i.product.id} ${i.size.id}" maxlength="12" title="Quantity:"
                                                        class="input-text qty" value="${i.quantity}">
                                                <button onclick="increase('${i.product.id}', '${i.size.id}', '${i.product.listedPrice}')"
                                                        class="increase items-count" type="button"><i class="lnr lnr-chevron-up"></i></button>
                                                <button onclick="decrease('${i.product.id}', '${i.size.id}', '${i.product.listedPrice}')"
                                                        class="reduced items-count" type="button"><i class="lnr lnr-chevron-down"></i></button>
                                            </div>
                                        </td>
                                        <td>
                                            <h5 id="total ${i.product.id} ${i.size.id}">${i.product.listedPrice * i.quantity} VND</h5>
                                        </td>
                                        <td>
                                            <button onclick="deleteItem('${i.product.id}', '${i.size.id}')" style="cursor: pointer" class="fa-solid fa-xmark"></button>
                                        </td>
                                    </tr>
                                </c:forEach>
                                <tr class="bottom_button">
                                    <td>
                                        <a class="gray_btn" href="#">Update Cart</a>
                                    </td>
                                    <td>

                                    </td>
                                    <td>

                                    </td>
                                    <td>
                                        <div class="cupon_text d-flex align-items-center">
                                            <input type="text" placeholder="Coupon Code">
                                            <a class="primary-btn" href="#">Apply</a>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td>

                                    </td>
                                    <td>

                                    </td>
                                    <td>
                                        <h5>Subtotal</h5>
                                    </td>
                                    <td>
                                        <h5>${requestScope.totalPrice} VND</h5>
                                    </td>
                                </tr>
                                <tr class="out_button_area">
                                    <td>

                                    </td>
                                    <td>

                                    </td>
                                    <td>

                                    </td>
                                    <td>

                                    </td>
                                    <td>

                                    </td>
                                    <td>

                                    </td>
                                    <td>
                                        <div class="checkout_btn_inner d-flex align-items-center">
                                            <a href="checkout" class="primary-btn">Check Out Now</a>
                                        </div>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </section>
        <!--================End Cart Area =================-->

        <!-- start footer Area -->
        <footer class="footer-area section_gap">
            <div class="container">
                <div class="row">
                    <div class="col-lg-3  col-md-6 col-sm-6">
                        <div class="single-footer-widget">
                            <h6>About Us</h6>
                            <p>
                                Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt
                                ut labore dolore
                                magna aliqua.
                            </p>
                        </div>
                    </div>
                    <div class="col-lg-4  col-md-6 col-sm-6">
                        <div class="single-footer-widget">
                            <h6>Newsletter</h6>
                            <p>Stay update with our latest</p>
                            <div class="" id="mc_embed_signup">

                                <form target="_blank" novalidate="true" action="https://spondonit.us12.list-manage.com/subscribe/post?u=1462626880ade1ac87bd9c93a&amp;id=92a4423d01"
                                      method="get" class="form-inline">

                                    <div class="d-flex flex-row">

                                        <input class="form-control" name="EMAIL" placeholder="Enter Email" onfocus="this.placeholder = ''"
                                               onblur="this.placeholder = 'Enter Email '" required="" type="email">


                                        <button class="click-btn btn btn-default"><i class="fa fa-long-arrow-right"
                                                                                     aria-hidden="true"></i></button>
                                        <div style="position: absolute; left: -5000px;">
                                            <input name="b_36c4fd991d266f23781ded980_aefe40901a" tabindex="-1" value=""
                                                   type="text">
                                        </div>

                                        <!-- <div class="col-lg-4 col-md-4">
                                                                                                            <button class="bb-btn btn"><span class="lnr lnr-arrow-right"></span></button>
                                                                                                    </div>  -->
                                    </div>
                                    <div class="info"></div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-3  col-md-6 col-sm-6">
                        <div class="single-footer-widget mail-chimp">
                            <h6 class="mb-20">Instragram Feed</h6>
                            <ul class="instafeed d-flex flex-wrap">
                                <li><img src="<%=request.getContextPath()%>/client/img/i1.jpg" alt=""></li>
                                <li><img src="<%=request.getContextPath()%>/client/img/i2.jpg" alt=""></li>
                                <li><img src="<%=request.getContextPath()%>/client/img/i3.jpg" alt=""></li>
                                <li><img src="<%=request.getContextPath()%>/client/img/i4.jpg" alt=""></li>
                                <li><img src="<%=request.getContextPath()%>/client/img/i5.jpg" alt=""></li>
                                <li><img src="<%=request.getContextPath()%>/client/img/i6.jpg" alt=""></li>
                                <li><img src="<%=request.getContextPath()%>/client/img/i7.jpg" alt=""></li>
                                <li><img src="<%=request.getContextPath()%>/client/img/i8.jpg" alt=""></li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-lg-2 col-md-6 col-sm-6">
                        <div class="single-footer-widget">
                            <h6>Follow Us</h6>
                            <p>Let us be social</p>
                            <div class="footer-social d-flex align-items-center">
                                <a href="#"><i class="fa fa-facebook"></i></a>
                                <a href="#"><i class="fa fa-twitter"></i></a>
                                <a href="#"><i class="fa fa-dribbble"></i></a>
                                <a href="#"><i class="fa fa-behance"></i></a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="footer-bottom d-flex justify-content-center align-items-center flex-wrap">
                    <p class="footer-text m-0"><!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                        Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class="fa fa-heart-o" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank">Colorlib</a>
                        <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                    </p>
                </div>
            </div>
        </footer>
        <!-- End footer Area -->
        <script>
            let contextPath = '<%=request.getContextPath()%>';
        </script>
        <script src="
                https://cdn.jsdelivr.net/npm/sweetalert2@11.10.6/dist/sweetalert2.all.min.js
        "></script>
        <script src="<%=request.getContextPath()%>/client/js/selectItem.js"></script>
        <script src="<%=request.getContextPath()%>/client/js/vendor/jquery-2.2.4.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4"
        crossorigin="anonymous"></script>
        <script src="<%=request.getContextPath()%>/client/js/vendor/bootstrap.min.js"></script>
        <script src="<%=request.getContextPath()%>/client/js/jquery.ajaxchimp.min.js"></script>
        <script src="<%=request.getContextPath()%>/client/js/jquery.nice-select.min.js"></script>
        <script src="<%=request.getContextPath()%>/client/js/jquery.sticky.js"></script>
        <script src="<%=request.getContextPath()%>/client/js/nouislider.min.js"></script>
        <script src="<%=request.getContextPath()%>/client/js/jquery.magnific-popup.min.js"></script>
        <script src="<%=request.getContextPath()%>/client/js/owl.carousel.min.js"></script>
        <!--gmaps Js-->
        <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCjCGmQ0Uq4exrzdcL6rvxywDDOvfAu6eE"></script>
        <script src="<%=request.getContextPath()%>/client/js/gmaps.min.js"></script>
        <script src="<%=request.getContextPath()%>/client/js/main.js"></script>
    </body>

</html>