
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
        <div class="container pt-5">

            <button type="button" class="card-footer btn btn-primary bg-primary mb-5" data-toggle="modal" data-target="#myModal">
                Add Product
            </button>
            <h1 class="text-center pb-3">Manage The Product List</h1>
            <div class="table-wrapper-scroll-y my-custom-scrollbar">
                <table class="table table-dark" style="height:100px;overflow: scroll;width: 100%;">
                    <thead>
                        <tr>
                            <th> Name</th>
                            <th> Image</th>
                            <th> Category </th>
                            <th> Prices</th>
                            <th> Description</th>
                            <th> Number View</th>
                            <th> Create Date </th>
                            <th> Selled </th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <jsp:useBean id="catDAO" class="dal.CategoryDAO" ></jsp:useBean>
                        <jsp:useBean id="proDAO" class="dal.ProductDAO" ></jsp:useBean>
                        <c:forEach items="${proDAO.allProduct}" var = "item">
                            <tr>
                                <td>${item.proName}</td>
                                <td>
                                    <img src="${item.proImg}" width="100" height="80"/>
                                </td>
                                <td>${item.category.catName}</td>
                                <td>$${item.proPrice}</td>
                                <td>${item.proDes}</td>
                                <td>${item.proView}</td>
                                <td>${item.proCreate}</td>
                                <td>${item.proSelled}</td>

                                <td>
                                    <form>
                                        <input type="hidden" name="proId" value="${item.proId}"/>
                                        <button class="btn btn-primary text-light" formaction="update-product" formmethod="get">
                                            Update
                                        </button>
                                        <button class="btn btn-danger text-light " formaction="delete-product" formmethod="post">
                                            Delete
                                        </button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="modal fade" id="myModal">
                <div class="modal-dialog">
                    <div class="modal-content">

                        <div class="modal-header">
                            <h4 class="modal-title">Add Product</h4>
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                        </div>

                        <div class="container-login100">
                            <div class="wrap-login100 p-l-55 p-r-55 p-t-65 p-b-54">
                                <form class="login100-form validate-form" action="manage-product" method="post" enctype="multipart/form-data">

                                    <div class="wrap-input100 validate-input" data-validate="Name is required">
                                        <span class="label-input100">Product Name</span>
                                        <input class="input100" type="text" placeholder="Type Product name" name="proname"/>
                                        <span class="focus-input100" data-symbol="&#xf206;"></span>
                                    </div>
                                    <br/>

                                    <div class="wrap-input100 validate-input" data-validate="Name is required">
                                        <span class="label-input100">Product Price</span>
                                        <input class="input100" type="number" min="1"  name="proprice"/>
                                        <span class="focus-input100" data-symbol="&#xf206;"></span>
                                    </div>
                                    <br/>

 

                                    <div class="wrap-input100 validate-input" data-validate="Name is required">
                                        <span class="label-input100">Product Description</span>
                                        <input class="input100" type="text" placeholder="Type Product Description" name="prodes"/>
                                        <span class="focus-input100" data-symbol="&#xf206;"></span>
                                    </div>
                                    <br/>

                                    <div class="wrap-input100 validate-input" data-validate="Name is required">
                                        <span class="label-input100">Product Create</span>
                                        <input class="input100" type="date" name="prodate"/>
                                        <span class="focus-input100" data-symbol="&#xf206;"></span>
                                    </div>
                                    <br/>

                                    <div class="wrap-input100 validate-input" data-validate="Name is required">
                                        <span class="label-input100">Category</span>
                                        <select name="catId" class="input100">
                                            <c:forEach items="${catDAO.allCategory}" var = "item">
                                                <option value="${item.catId}">${item.catName}</option>
                                            </c:forEach>
                                        </select>
                                        <span class="focus-input100" data-symbol="&#xf206;"></span>
                                    </div>
                                    <br/>

                                    <div class="wrap-input100 validate-input" data-validate="Name is required">
                                        <span class="label-input100">Product Image</span>
                                        <input class="input100" type="file" name="img"/>
                                    </div>
                                    <br/>


                                    <div class="container-login100-form-btn">
                                        <div class="wrap-login100-form-btn">
                                            <div class="login100-form-bgbtn"></div>
                                            <button class="login100-form-btn" formaction="manage-product" formmethod="POST">
                                                Add
                                            </button>
                                        </div>
                                    </div>

                                </form>
                            </div>
                        </div>

                    </div>
                </div>
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
