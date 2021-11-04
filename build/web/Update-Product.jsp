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


        <jsp:useBean id="catDAO" class="dal.CategoryDAO" ></jsp:useBean>
        <jsp:useBean id="proDAO" class="dal.ProductDAO" ></jsp:useBean>
            <div class="container-login100">
                <div class="wrap-login100 p-l-55 p-r-55 p-t-65 p-b-54">
                    <form class="login100-form validate-form" action="update-product" method="post">
                        <h1> Update Product</h1>
                        <input type="hidden" name="proId" value="${product.proId}">
                        <div class="wrap-input100 validate-input" data-validate="Name is required">
                            <span class="label-input100">Product Name</span>
                            <input class="input100" type="text" placeholder="Type Product name" name="proname" value="${product.proName}"/>
                            <span class="focus-input100" data-symbol="&#xf206;"></span>
                        </div>
                        <br/>
                        
                        <div class="wrap-input100 validate-input" data-validate="Name is required">
                            <span class="label-input100">Product Price</span>
                            <input class="input100" type="number" min="1"  name="proprice" value="${product.proPrice}"/>
                            <span class="focus-input100" data-symbol="&#xf206;"></span>
                        </div>
                        <br/>



                        <div class="wrap-input100 validate-input" data-validate="Name is required">
                            <span class="label-input100">Product Description</span>
                            <input class="input100" type="text" placeholder="Type Product Description" name="prodes" value="${product.proDes}"/>
                            <span class="focus-input100" data-symbol="&#xf206;"></span>
                        </div>
                        <br/>


                        <div class="wrap-input100 validate-input" data-validate="Name is required">
                            <span class="label-input100">Category</span>
                            <select name="catId" class="input100">
                            <c:forEach items="${catDAO.allCategory}" var = "item">
                                <c:if test="${product.category.catId == item.catId}">
                                    <option value="${item.catId}" selected>${item.catName}</option>
                                </c:if>
                                <c:if test="${product.category.catId != item.catId}">
                                    <option value="${item.catId}">${item.catName}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                        <span class="focus-input100" data-symbol="&#xf206;"></span>
                    </div>
                    <br/>
                    <div class="container-login100-form-btn">
                        <div class="wrap-login100-form-btn">
                            <div class="login100-form-bgbtn"></div>
                            <button class="login100-form-btn" formaction="update-product" formmethod="POST">
                                Change
                            </button>
                        </div>
                    </div>

                </form>
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