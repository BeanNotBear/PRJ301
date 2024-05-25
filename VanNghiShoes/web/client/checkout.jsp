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
        <title>Van Nghi Shoes</title>

        <!--
                CSS
                ============================================= -->
        <link rel="stylesheet" href="<%=request.getContextPath()%>/client/css/linearicons.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/client/css/owl.carousel.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/client/css/themify-icons.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/client/css/font-awesome.min.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/client/css/nice-select.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/client/css/nouislider.min.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/client/css/bootstrap.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/client/css/main.css">
    </head>

    <body onload="calculateTotal()">

        <!-- Start Header Area -->
        <%@include file="header.jsp"%>
        <!-- End Header Area -->

        <!-- Start Banner Area -->
        <section class="banner-area organic-breadcrumb">
            <div class="container">
                <div class="breadcrumb-banner d-flex flex-wrap align-items-center justify-content-end">
                    <div class="col-first">
                        <h1>Checkout</h1>
                        <nav class="d-flex align-items-center">
                            <a href="index.html">Home<span class="lnr lnr-arrow-right"></span></a>
                            <a href="single-product.html">Checkout</a>
                        </nav>
                    </div>
                </div>
            </div>
        </section>
        <!-- End Banner Area -->

        <!--================Checkout Area =================-->
        <section class="checkout_area section_gap">
            <div class="container">
                <div class="cupon_area">
                    <div class="check_title">
                        <h2>${sessionScope.msg}</h2>
                    </div>
                </div>
                <div class="billing_details">
                    <div class="row">
                        <div class="col-lg-8">
                            <h3>Billing Details</h3>
                            <form id="checkOutForm" class="row contact_form" action="checkout" method="post" novalidate="novalidate">
                                <c:set var="a" value="${sessionScope.account}"/>
                                <div class="col-md-12 form-group p_star">
                                    <input required placeholder="Full Name" type="text" class="form-control" id="first" name="name" value="${a.fullName}" readonly>
                                </div>
                                <div class="col-md-6 form-group p_star">
                                    <input required onkeyup="checkPhoneNumber(this)" placeholder="Phone number" type="text" class="form-control" id="number" name="phone" value="${a.phone}" readonly>
                                    <div id="phone_err" style="color: red"></div>
                                </div>
                                <div class="col-md-6 form-group p_star">
                                    <input required onkeyup="checkEmail(this)" placeholder="Email Address" type="text" class="form-control" id="email" name="email" value="${a.email}" readonly>
                                    <div id="email_err" style="color: red"></div>
                                </div>
                                <div class="col-md-12 form-group p_star">
                                    <input required placeholder="Country" type="text" class="form-control" id="country" name="country">
                                </div>
                                <div class="col-md-12 form-group p_star">
                                    <input required placeholder="City" type="text" class="form-control" id="city" name="city">
                                </div>
                                <div class="col-md-12 form-group p_star">
                                    <input required placeholder="District" type="text" class="form-control" id="add1" name="district">
                                </div>
                                <div class="col-md-12 form-group p_star">
                                    <input required placeholder="Address" type="text" class="form-control" id="add1" name="address">
                                </div>
                                <div class="col-md-12 form-group">
                                    <input type="text" class="form-control" id="zip" name="zip" placeholder="Postcode/ZIP">
                                    <input id="payment" type="hidden" name="payment">
                                    <input id="amount" type="hidden" name="amount">
                                </div>
                                <div class="col-md-12 form-group">
                                    <div class="creat_account">
                                        <h3>Shipping Details</h3>
                                        <label for="f-option3">Note</label>
                                    </div>
                                    <textarea class="form-control" name="note" id="message" rows="1" placeholder="Order Notes"></textarea>
                                </div>
                            </form>
                        </div>
                        <div class="col-lg-4">
                            <div class="order_box">
                                <h2>Your Order</h2>
                                <ul class="list">
                                    <li><a href="#">Product</a></li>
                                    <c:forEach var="i" items="${sessionScope.items}">
                                        <li><a href="#">${i.product.name}<div style="margin-top: -20px">x ${i.quantity}</div> <div style="margin-top: -20px" class="last">${i.product.listedPrice * i.quantity } VND</div></a></li>
                                            </c:forEach>
                                </ul>
                                <ul class="list list_2">
                                    <li><a href="#">Subtotal <span> VND</span><span id="subtotal">${sessionScope.total}</span></a></li>
                                    <li><a href="#">Shipping <span> VND</span><span id="ship-cost">40000</span><span>Flat rate:</span></a></li>
                                    <li><a href="#">Total <span id="total"></span></a></li>
                                </ul>
                                <div class="payment_item">
                                    <div class="radion_btn">
                                        <input type="radio" id="f-option5" name="selector" value="COD">
                                        <label for="f-option5">COD payment</label>
                                        <div class="check"></div>
                                    </div>
                                    <p>Payment on delivery</p>
                                </div>
                                <div class="payment_item active">
                                    <div class="radion_btn">
                                        <input type="radio" id="f-option6" name="selector" value="VN PAY">
                                        <label for="f-option6">VN Pay</label>
                                        <img src="img/product/card.jpg" alt="">
                                        <div class="check"></div>
                                    </div>
                                    <p>Prepayment</p>
                                </div>
                                <div class="creat_account">
                                    <input required type="checkbox" id="f-option4">
                                    <label for="f-option4">I accept all</label>
                                    <a href="#">terms & conditions*</a>
                                </div>
                                    <a class="primary-btn" onclick="checkOut()">Proceed to Order</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!--================End Checkout Area =================-->

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
                                <li><img src="img/i1.jpg" alt=""></li>
                                <li><img src="img/i2.jpg" alt=""></li>
                                <li><img src="img/i3.jpg" alt=""></li>
                                <li><img src="img/i4.jpg" alt=""></li>
                                <li><img src="img/i5.jpg" alt=""></li>
                                <li><img src="img/i6.jpg" alt=""></li>
                                <li><img src="img/i7.jpg" alt=""></li>
                                <li><img src="img/i8.jpg" alt=""></li>
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
        <script src="<%=request.getContextPath()%>/client/js/checkValid.js"></script>
        <script src="<%=request.getContextPath()%>/client/js/checkOut.js"></script>
    </body>

</html>