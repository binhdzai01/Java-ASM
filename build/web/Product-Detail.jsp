<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <style>
            strong {
                font-size: 1.3em;
            }
        </style>
    </head>

    <body>
        <%@include file="./Header.jsp" %>
        <form action="product-detail" method = "post" class="mt-5 mb-5">
            <div class="container text-light">
                <div class="row">
                    <div class="col-6">
                        <img src="${product.proImg}" alt="" class="img-fluid">
                    </div>
                    <div class="col-6 pl-5 pt-5 mt-5">
                        <h1>${product.proName}</h1>
                        <span>${product.proView} people see this product.</span><br />
                        <br />
                        <strong>Create Date: </strong> ${product.proCreate}
                        <br />
                        <strong>State :</strong> <span class="bg-success">100%</span>
                        <br />

                        <strong>Price :</strong> <span class="bg-danger">$${product.proPrice}</span>
                        <br />
                        <strong>Description </strong> ${product.proDes}
                        <br />
                        <hr />
                        <input type="hidden" name="id" value="${product.proId}"/>
                        <button class="btn btn-danger text-light btn-lg" formaction="product-detail" formmethod="post">
                            Order
                        </button>
                    </div>

                </div>
            </div>
        </form>
                        
                        
        <div class="container">
            <h2>Top View Products</h2>
            <div class="row">
                <c:forEach items="${viewProduct}" var="pr">
                    <div class="col-md-4">
                    <figure class="card card-product-grid">
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