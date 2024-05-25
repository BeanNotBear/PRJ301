<%-- 
    Document   : verification
    Created on : Feb 9, 2024, 12:26:57 AM
    Author     : nghin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <!-- Mobile Specific Meta -->
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <!-- Favicon-->
        <link rel="shortcut icon" href="img/fav.png">
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
        <link rel="stylesheet" href="<%=request.getContextPath()%>/client/css/linearicons.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/client/css/owl.carousel.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/client/css/themify-icons.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/client/css/font-awesome.min.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/client/css/nice-select.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/client/css/nouislider.min.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/client/css/bootstrap.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/client/css/main.css">
    </head>
    <body style="background-color: white" onload="startCountdown()">
        <!-- Start Header Area -->
        <%@include file="header.jsp"%>
        <!-- End Header Area -->

        <section>
            <div class="col-lg-4 col-md-6 col-sm-6" style="position: absolute; top: 60%; left: 50%; transform: translate(-50%, -50%)">
                <div class="single-footer-widget">
                    <div>You have not received the code? 
                        <span id="countdown" style="color: blue"><span id="timer">60</span>s</span>
                        <a id="resendLink" href="verification"></a>
                    </div>

                    <div>

                        <form class="d-flex flex-row" action="verification" method="post">
                            <input class="form-control" name="code" placeholder="Enter code" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Enter code '"
                                   required="" type="text" style="border: 1px #2222 solid">

                            <button class="click-btn btn btn-default" type="submit"><i class="fa fa-long-arrow-right" aria-hidden="true"></i></button>
                        </form>
                    </div>
                    
                    <c:set var="codeIncorrect" value="${requestScope.codeIncorrect}"></c:set>
                    <c:if test="${codeIncorrect != null}">
                        <div style="color: red">${codeIncorrect}</div>
                    </c:if>
                </div>
            </div>
        </section>

        <script src="<%=request.getContextPath()%>/client/js/vendor/jquery-2.2.4.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4"
        crossorigin="anonymous"></script>
        <script src="<%=request.getContextPath()%>/client/js/vendor/bootstrap.min.js"></script>
        <script src="<%=request.getContextPath()%>/client/js/jquery.ajaxchimp.min.js"></script>
        <script src="<%=request.getContextPath()%>/client/js/jquery.nice-select.min.js"></script>
        <script src="<%=request.getContextPath()%>/client/js/jquery.sticky.js"></script>
        <script src="<%=request.getContextPath()%>/client/js/nouislider.min.js"></script>
        <script src="<%=request.getContextPath()%>/client/js/countdown.js"></script>
        <script src="<%=request.getContextPath()%>/client/js/jquery.magnific-popup.min.js"></script>
        <script src="<%=request.getContextPath()%>/client/js/owl.carousel.min.js"></script>
        <!--gmaps Js-->
        <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCjCGmQ0Uq4exrzdcL6rvxywDDOvfAu6eE"></script>
        <script src="<%=request.getContextPath()%>/client/js/gmaps.min.js"></script>
        <script src="<%=request.getContextPath()%>/client/js/main.js"></script>
        <script src="<%=request.getContextPath()%>/client/js/checkValid.js"></script>
    </body>
</html>
