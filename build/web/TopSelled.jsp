
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <title>Pink Shop</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!--===============================================================================================-->
        <link rel="icon" type="image/png" href="images/icons/favicon.ico" />
        <!--===============================================================================================-->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="fonts/iconic/css/material-design-iconic-font.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="vendor/css-hamburgers/hamburgers.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="vendor/animsition/css/animsition.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="vendor/daterangepicker/daterangepicker.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="css/util.css">
        <link rel="stylesheet" type="text/css" href="css/main.css">

        <!--===============================================================================================-->
    </head>
    <body>
        
        <%@include file="./Header.jsp" %>
        <jsp:useBean id="proDAO" class="dal.ProductDAO" ></jsp:useBean>
        <div class="container mt-5 mb-5">
            <h1 class="mt-5 mb-5 text-center text-light">Top Selled Product</h1>
            <div class="row">
            <c:forEach items="${proDAO.topSelled}" var="pr">
                    <div class="col-md-4">
                        <figure class="card card-product-grid">
                            <div> <h3 class="mb-3 text-center">${pr.proSelled} product selled <span><i class="fa fa-trophy text-warning" aria-hidden="true"></i></span></h3></div>
                            <div class="img-wrap"> 
                                <span class="badge badge-danger"> new </span>
                                <a href="product-detail?productid=${pr.proId}">
                                    <img src="${pr.proImg}" alt="${pr.proImg}" class="img-fluid reponsive"/>
                                </a>
                                <div class="col-lg-4 col-md-12 mb-4">
                                </div>

                            </div> <!-- img-wrap.// -->
                            <figcaption class="info-wrap">
                                <div class="fix-height">
                                    <div id="productnamefix">
                                        <a href="product-detail?productid=${pr.proId}" class="title">${pr.proName}</a>
                                    </div>

                                    <div class="price-wrap mt-2">
                                        <span class="price">$${pr.proPrice}</span>
                                    </div> <!-- price-wrap.// -->
                                    
                                </div>
                            </figcaption>
                        </figure>
                    </div> <!-- col.// -->
                </c:forEach>
            </div>
            <%@include file="./Footer.jsp" %>
            <!--===============================================================================================-->
            <script src="vendor/jquery/jquery-3.2.1.min.js"></script>
            <!--===============================================================================================-->
            <script src="vendor/animsition/js/animsition.min.js"></script>
            <!--===============================================================================================-->
            <script src="vendor/bootstrap/js/popper.js"></script>
            <script src="vendor/bootstrap/js/bootstrap.min.js"></script>
            <!--===============================================================================================-->
            <script src="vendor/select2/select2.min.js"></script>
            <!--===============================================================================================-->
            <script src="vendor/daterangepicker/moment.min.js"></script>
            <script src="vendor/daterangepicker/daterangepicker.js"></script>
            <!--===============================================================================================-->
            <script src="vendor/countdowntime/countdowntime.js"></script>
            <!--===============================================================================================-->
            <script src="js/main.js"></script>

    </body>
</html>
