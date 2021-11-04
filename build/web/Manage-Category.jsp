
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
                Add Category
            </button>
            <h1 class="text-center text-light pb-3">Manage The Category List</h1>
            <table class="table table-dark">
                <thead>
                    <tr>
                        <th>Category Name</th>
                        <th>Category Images</th>
                        <th>Category Description</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <jsp:useBean id="dao" class="dal.CategoryDAO" ></jsp:useBean>
                    <c:forEach items="${dao.allCategory}" var = "item">
                        <tr>
                            <td>${item.catName}</td>
                            <td>
                                <img src="${item.catImage}" class="reponsive img-fluid" width="100" height="80" />
                            </td>
                            <td>${item.catDes}</td>
                            <td>
                                <form>
                                    <input type="hidden" name="catId" value="${item.catId}"/>
                                    <button class="btn btn-primary text-light mr-5">
                                        Update
                                    </button>
                                    <button class="btn btn-danger text-light ml-5" formaction="delete-category" formmethod="post">
                                        Delete
                                    </button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <!-- The Modal -->
            <div class="modal fade" id="myModal">
                <div class="modal-dialog">
                    <div class="modal-content">

                        <!-- Modal Header -->
                        <div class="modal-header">
                            <h4 class="modal-title">Add Category</h4>
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                        </div>

                        <!-- Modal body -->
                        <div class="container-login100">
                            <div class="wrap-login100 p-l-55 p-r-55 p-t-65 p-b-54">
                                <form class="login100-form validate-form" action="manage-category" method="post" enctype="multipart/form-data">

                                    <div class="wrap-input100 validate-input" data-validate="Name is required">
                                        <span class="label-input100">Category Name</span>
                                        <input class="input100" type="text" placeholder="Type Category name" name="catname"/>
                                        <span class="focus-input100" data-symbol="&#xf206;"></span>
                                    </div>
                                    <br/>

                                    <div class="wrap-input100 validate-input" data-validate="Name is required">
                                        <span class="label-input100">Category Image</span>
                                        <input class="input100" type="file" name="img"/>
                                    </div>
                                    <br/>



                                    <div class="wrap-input100 validate-input" data-validate="Name is required">
                                        <span class="label-input100">Category Description</span>
                                        <input class="input100" type="text" placeholder="Type Category Description" name="catdes"/>
                                        <span class="focus-input100" data-symbol="&#xf206;"></span>
                                    </div>
                                    <br/>
                                    <div class="container-login100-form-btn">
                                        <div class="wrap-login100-form-btn">
                                            <div class="login100-form-bgbtn"></div>
                                            <button class="login100-form-btn" formaction="manage-category" formmethod="POST">
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
