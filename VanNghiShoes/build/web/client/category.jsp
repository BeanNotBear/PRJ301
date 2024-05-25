<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
        <title>Van Nghi Shoes | Shop Category</title>

        <!--Link CSS-->
        <link rel="stylesheet" href="<%=request.getContextPath()%>/client/css/linearicons.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/client/css/owl.carousel.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/client/css/font-awesome.min.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/client/css/themify-icons.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/client/css/nice-select.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/client/css/nouislider.min.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/client/css/bootstrap.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/client/css/main.css">
    </head>

    <body id="category">

        <!-- Start Header Area -->
        <%@include file="header.jsp"%>
        <!-- End Header Area -->

        <!-- Start Banner Area -->
        <section class="banner-area organic-breadcrumb">
            <div class="container">
                <div class="breadcrumb-banner d-flex flex-wrap align-items-center justify-content-end">
                    <div class="col-first">
                        <h1>Shop Category page</h1>
                        <nav class="d-flex align-items-center">
                            <a href="index.html">Home<span class="lnr lnr-arrow-right"></span></a>
                            <a href="#">Shop<span class="lnr lnr-arrow-right"></span></a>
                            <a href="category">Fashon Category</a>
                        </nav>
                    </div>
                </div>
            </div>
        </section>
        <!-- End Banner Area -->
        <div class="container">
            <div class="row">
                <div class="col-xl-3 col-lg-4 col-md-5">
                    <div class="sidebar-categories">
                        <div class="head">Browse Categories</div>
                        <ul class="main-categories">

                            <!--List category-->
                            <c:forEach var="i" items="${requestScope.categoryDTOs}">
                                <li class="filter-list">
                                    <input  onclick="filter()" class="pixel-radio category" type="checkbox" id="${i.id}" name="category">
                                    <label>${i.name}<span>(${i.numberOfProduct})</span>
                                    </label>
                                </li>
                            </c:forEach>

                        </ul>
                    </div>
                    <div class="sidebar-filter mt-50">
                        <div class="top-filter-head">Product Filters</div>
                        <div class="common-filter">
                            <div class="head">Sizes</div>
                            <form action="#">
                                <ul>

                                    <!--List size-->
                                    <c:forEach var="s" items="${requestScope.sizes}">
                                        <li class="filter-list">
                                            <input onclick="filter()" class="pixel-radio size" type="checkbox" id="${s.id}" name="size">
                                            <label>${s.size}<span>(${s.numberOfProduct})</span></label>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </form>
                        </div>
                        <!--                        <div class="common-filter">
                                                    <div class="head">Color</div>
                                                    <form action="#">
                                                        <ul>
                                                            <li class="filter-list"><input class="pixel-radio" type="checkbox" id="black" name="color"><label for="black">Black<span>(29)</span></label></li>
                                                            <li class="filter-list"><input class="pixel-radio" type="radio" id="balckleather" name="color"><label for="balckleather">Black
                                                                    Leather<span>(29)</span></label></li>
                                                            <li class="filter-list"><input class="pixel-radio" type="radio" id="blackred" name="color"><label for="blackred">Black
                                                                    with red<span>(19)</span></label></li>
                                                            <li class="filter-list"><input class="pixel-radio" type="radio" id="gold" name="color"><label for="gold">Gold<span>(19)</span></label></li>
                                                            <li class="filter-list"><input class="pixel-radio" type="radio" id="spacegrey" name="color"><label for="spacegrey">Spacegrey<span>(19)</span></label></li>
                                                        </ul>
                                                    </form>
                                                </div>-->
                        <div class="common-filter">
                            <div class="head">Price</div>
                            <div class="price-range-area">
                                <div id="price-range"></div>
                                <div class="value-wrapper d-flex">
                                    <div class="price">Price:</div>
                                    <div id="lower-value"></div>
                                    <span>VND</span>
                                    <div class="to">to</div>
                                    <div id="upper-value"></div>
                                    <span>VND</span>
                                    <input type="hidden" id="lower">
                                    <input type="hidden" id="upper">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-xl-9 col-lg-8 col-md-7">
                    <!-- Start Filter Bar -->
                    <div class="filter-bar d-flex flex-wrap align-items-center">
                        <div class="sorting">
                            <select id="sort" onchange="filter()">
                                <option value="cancel sort">Default</option>
                                <option value="price asc">Sort by ascending Price</option>
                                <option value="price desc">Sort by descending Price</option>
                            </select>
                        </div>
                        <div class="pagination">
                            <a onclick="previousPage()" class="prev-arrow"><i class="fa fa-long-arrow-left" aria-hidden="true"></i></a>
                            <a id="first-page" onclick="changePage(this)" class="active page">1</a>
                            <a onclick="changePage(this)" class="page">2</a>
                            <a id="last-page" onclick="changePage(this)" class="page">3</a>
                            <a onclick="nextPage()" class="next-arrow"><i class="fa fa-long-arrow-right" aria-hidden="true"></i></a>
                        </div>
                    </div>
                    <!-- End Filter Bar -->
                    <!-- Start Best Seller -->
                    <section class="lattest-product-area pb-40 category-list">
                        <div id="content" class="row">
                            <!-- single product -->
                            <%int i = 0;%>
                            <c:set var="items" value="${requestScope.items}"/>
                            <c:set var="cateId" value="${requestScope.cateId}"/>
                            <c:forEach var="p" items="${items}">
                                <div class="col-lg-4 col-md-6">
                                    <div class="single-product">
                                        <img class="img-fluid" src="${p.product.img1}" alt="${p.product.name}">
                                        <div class="product-details">
                                            <h6>${p.product.name}</h6>
                                            <div class="price">
                                                <h6>${p.product.listedPrice} VND</h6>
                                            </div>
                                            <div class="prd-bottom">
                                                <a onclick="" class="social-info">
                                                    <span class="lnr lnr-heart"></span>
                                                    <p class="hover-text">Wishlist</p>
                                                </a>
                                                <a onclick="" class="social-info">
                                                    <span class="lnr lnr-sync"></span>
                                                    <p class="hover-text">Compare</p>
                                                </a>
                                                <a href="<%=request.getContextPath()%>/product-details?id=${p.product.id}" class="social-info">
                                                    <span class="lnr lnr-move"></span>
                                                    <p class="hover-text">view more</p>
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </section>
                    <!-- End Best Seller -->
                    <!-- Start Filter Bar -->
                    <div class="filter-bar d-flex flex-wrap align-items-center">
                        <div class="sorting mr-auto">
                            <select>
                                <option value="1">Show 12</option>
                                <option value="1">Show 12</option>
                                <option value="1">Show 12</option>
                            </select>
                        </div>
                        <div class="pagination">
                            <a href="#" class="prev-arrow"><i class="fa fa-long-arrow-left" aria-hidden="true"></i></a>
                            <a href="#" class="active">1</a>
                            <a href="#">2</a>
                            <a href="#">3</a>
                            <a href="#" class="dot-dot"><i class="fa fa-ellipsis-h" aria-hidden="true"></i></a>
                            <a href="#">6</a>
                            <a href="#" class="next-arrow"><i class="fa fa-long-arrow-right" aria-hidden="true"></i></a>
                        </div>
                    </div>
                    <!-- End Filter Bar -->
                </div>
            </div>
        </div>

        <!-- Start related-product Area -->
        <section class="related-product-area section_gap">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-lg-6 text-center">
                        <div class="section-title">
                            <h1>Deals of the Week</h1>
                            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore
                                magna aliqua.</p>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-9">
                        <div class="row">
                            <div class="col-lg-4 col-md-4 col-sm-6 mb-20">
                                <div class="single-related-product d-flex">
                                    <a href="#"><img src="<%=request.getContextPath()%>/client/img/r1.jpg" alt=""></a>
                                    <div class="desc">
                                        <a href="#" class="title">Black lace Heels</a>
                                        <div class="price">
                                            <h6>$189.00</h6>
                                            <h6 class="l-through">$210.00</h6>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-4 col-md-4 col-sm-6 mb-20">
                                <div class="single-related-product d-flex">
                                    <a href="#"><img src="<%=request.getContextPath()%>/client/img/r2.jpg" alt=""></a>
                                    <div class="desc">
                                        <a href="#" class="title">Black lace Heels</a>
                                        <div class="price">
                                            <h6>$189.00</h6>
                                            <h6 class="l-through">$210.00</h6>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-4 col-md-4 col-sm-6 mb-20">
                                <div class="single-related-product d-flex">
                                    <a href="#"><img src="<%=request.getContextPath()%>/client/img/r3.jpg" alt=""></a>
                                    <div class="desc">
                                        <a href="#" class="title">Black lace Heels</a>
                                        <div class="price">
                                            <h6>$189.00</h6>
                                            <h6 class="l-through">$210.00</h6>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-4 col-md-4 col-sm-6 mb-20">
                                <div class="single-related-product d-flex">
                                    <a href="#"><img src="<%=request.getContextPath()%>/client/img/r5.jpg" alt=""></a>
                                    <div class="desc">
                                        <a href="#" class="title">Black lace Heels</a>
                                        <div class="price">
                                            <h6>$189.00</h6>
                                            <h6 class="l-through">$210.00</h6>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-4 col-md-4 col-sm-6 mb-20">
                                <div class="single-related-product d-flex">
                                    <a href="#"><img src="<%=request.getContextPath()%>/client/img/r6.jpg" alt=""></a>
                                    <div class="desc">
                                        <a href="#" class="title">Black lace Heels</a>
                                        <div class="price">
                                            <h6>$189.00</h6>
                                            <h6 class="l-through">$210.00</h6>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-4 col-md-4 col-sm-6 mb-20">
                                <div class="single-related-product d-flex">
                                    <a href="#"><img src="<%=request.getContextPath()%>/client/img/r7.jpg" alt=""></a>
                                    <div class="desc">
                                        <a href="#" class="title">Black lace Heels</a>
                                        <div class="price">
                                            <h6>$189.00</h6>
                                            <h6 class="l-through">$210.00</h6>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-4 col-md-4 col-sm-6">
                                <div class="single-related-product d-flex">
                                    <a href="#"><img src="<%=request.getContextPath()%>/client/img/r9.jpg" alt=""></a>
                                    <div class="desc">
                                        <a href="#" class="title">Black lace Heels</a>
                                        <div class="price">
                                            <h6>$189.00</h6>
                                            <h6 class="l-through">$210.00</h6>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-4 col-md-4 col-sm-6">
                                <div class="single-related-product d-flex">
                                    <a href="#"><img src="<%=request.getContextPath()%>/client/img/r10.jpg" alt=""></a>
                                    <div class="desc">
                                        <a href="#" class="title">Black lace Heels</a>
                                        <div class="price">
                                            <h6>$189.00</h6>
                                            <h6 class="l-through">$210.00</h6>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-4 col-md-4 col-sm-6">
                                <div class="single-related-product d-flex">
                                    <a href="#"><img src="<%=request.getContextPath()%>/client/img/r11.jpg" alt=""></a>
                                    <div class="desc">
                                        <a href="#" class="title">Black lace Heels</a>
                                        <div class="price">
                                            <h6>$189.00</h6>
                                            <h6 class="l-through">$210.00</h6>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-3">
                        <div class="ctg-right">
                            <a href="#" target="_blank">
                                <img class="img-fluid d-block mx-auto" src="<%=request.getContextPath()%>/client/img/category/c5.jpg" alt="">
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- End related-product Area -->

        <!-- start footer Area -->
        <footer class="footer-area section_gap">
            <div class="container">
                <div class="row">
                    <div class="col-lg-3  col-md-6 col-sm-6">
                        <div class="single-footer-widget">
                            <h6>About Us</h6>
                            <p>
                                Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore dolore
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

                                        <input class="form-control" name="EMAIL" placeholder="Enter Email" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Enter Email '"
                                               required="" type="email">


                                        <button class="click-btn btn btn-default"><i class="fa fa-long-arrow-right" aria-hidden="true"></i></button>
                                        <div style="position: absolute; left: -5000px;">
                                            <input name="b_36c4fd991d266f23781ded980_aefe40901a" tabindex="-1" value="" type="text">
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

        <!-- Modal Quick Product View -->
        <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="container relative">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <div class="product-quick-view">
                        <div class="row align-items-center">
                            <div class="col-lg-6">
                                <div class="quick-view-carousel">
                                    <div class="item" style="background: url(img/organic-food/q1.jpg);">

                                    </div>
                                    <div class="item" style="background: url(img/organic-food/q1.jpg);">

                                    </div>
                                    <div class="item" style="background: url(img/organic-food/q1.jpg);">

                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="quick-view-content">
                                    <div class="top">
                                        <h3 class="head">Mill Oil 1000W Heater, White</h3>
                                        <div class="price d-flex align-items-center"><span class="lnr lnr-tag"></span> <span class="ml-10">$149.99</span></div>
                                        <div class="category">Category: <span>Household</span></div>
                                        <div class="available">Availibility: <span>In Stock</span></div>
                                    </div>
                                    <div class="middle">
                                        <p class="content">Mill Oil is an innovative oil filled radiator with the most modern technology. If you are
                                            looking for something that can make your interior look awesome, and at the same time give you the pleasant
                                            warm feeling during the winter.</p>
                                        <a href="#" class="view-full">View full Details <span class="lnr lnr-arrow-right"></span></a>
                                    </div>
                                    <div class="bottom">
                                        <div class="color-picker d-flex align-items-center">Color:
                                            <span class="single-pick"></span>
                                            <span class="single-pick"></span>
                                            <span class="single-pick"></span>
                                            <span class="single-pick"></span>
                                            <span class="single-pick"></span>
                                        </div>
                                        <div class="quantity-container d-flex align-items-center mt-15">
                                            Quantity:
                                            <input type="text" class="quantity-amount ml-15" value="1" />
                                            <div class="arrow-btn d-inline-flex flex-column">
                                                <button class="increase arrow" type="button" title="Increase Quantity"><span class="lnr lnr-chevron-up"></span></button>
                                                <button class="decrease arrow" type="button" title="Decrease Quantity"><span class="lnr lnr-chevron-down"></span></button>
                                            </div>

                                        </div>
                                        <div class="d-flex mt-20">
                                            <a href="#" class="view-btn color-2"><span>Add to Cart</span></a>
                                            <a href="#" class="like-btn"><span class="lnr lnr-layers"></span></a>
                                            <a href="#" class="like-btn"><span class="lnr lnr-heart"></span></a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <script>
            let contextPath = '<%=request.getContextPath()%>';
        </script>
        <script src="<%=request.getContextPath()%>/client/js/selectItem.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js" integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
        <script src="<%=request.getContextPath()%>/client/js/main.js"></script>
        <script src="<%=request.getContextPath()%>/client/js/FilterProduct.js"></script>
        <script src="<%=request.getContextPath()%>/client/js/ProductDetail.js"></script>
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
    </body>

</html>