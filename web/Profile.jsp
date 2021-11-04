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
        <div class="container">
            <div class="card mb-5">
                <c:if test="${error !=null}">
                        <div class="alert alert-danger">
                            <strong>Opps!</strong> ${error}
                        </div>
                    </c:if>
                <c:if test="${success !=null}">
                        <div class="alert alert-success">
                            <strong>Ok!</strong> ${success}
                        </div>
                    </c:if>
                <div class="card-header text-center"><h2>Profile</h2></div>
                <div class="card-body text-center"><strong class="bg-info">Account :</strong> ${user.accountName}</div> 
                <div class="card-body text-center"><strong class="bg-info">Role &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</strong> ${user.isAdmin?"Admin":"User"}</div> 
                <div class="card-body text-center"><strong class="bg-info">Money &nbsp;&nbsp;&nbsp;&nbsp; :</strong> $${user.cash}</div> 
                <button type="button" class="card-footer btn btn-primary bg-primary" data-toggle="modal" data-target="#myModal">
                    Change Password
                </button>
            </div>
            <!-- The Modal -->
            <div class="modal fade" id="myModal">
                <div class="modal-dialog">
                    <div class="modal-content">

                        <!-- Modal Header -->
                        <div class="modal-header">
                            <h4 class="modal-title">Change Password</h4>
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                        </div>

                        <!-- Modal body -->
                        <div class="container-login100">
                            <div class="wrap-login100 p-l-55 p-r-55 p-t-65 p-b-54">
                                <form class="login100-form validate-form" action="profile" method="post">

                                    <div class="wrap-input100 validate-input" data-validate="Password is required">
                                        <span class="label-input100">Old Password</span>
                                        <input class="input100" type="password" name="oldpass" placeholder="Type your password">
                                        <span class="focus-input100" data-symbol="&#xf190;"></span>
                                    </div>
                                    <br/>
                                    <div class="wrap-input100 validate-input" data-validate="Password is required">
                                        <span class="label-input100">New Password</span>
                                        <input class="input100" type="password" name="newpass" placeholder="Type your password">
                                        <span class="focus-input100" data-symbol="&#xf190;"></span>
                                    </div>
                                    <br/>
                                    <div class="wrap-input100 validate-input" data-validate="Password is required">
                                        <span class="label-input100">ReNew Password</span>
                                        <input class="input100" type="password" name="renewpass" placeholder="Type your password">
                                        <span class="focus-input100" data-symbol="&#xf190;"></span>
                                    </div>
                                    <br/>
                                    <div class="container-login100-form-btn">
                                        <div class="wrap-login100-form-btn">
                                            <div class="login100-form-bgbtn"></div>
                                            <button class="login100-form-btn" formaction="profile" formmethod="POST">
                                                Change
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