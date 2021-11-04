

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <title>Pink Shop</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!--===============================================================================================-->
        <link rel="icon" type="image/png" href="images/icons/favicon.ico" />
        <!--===============================================================================================-->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
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
    </head>

    <body>
        <%@include file="./Header.jsp" %>
        <c:if test="${listProduct==null || listProduct.size()==0}">
            <h1 class="text-center mt-5 mb-5">
                Not Found Any Product In Cart
            </h1>
        </c:if>
        <c:if test="${listProduct!=null && listProduct.size()!=0}">
            <c:set var="total" value="${0}"></c:set>
                <div class="container">
                    <table class="table table-dark mt-5 mb-5">
                        <thead>
                            <tr>
                                <th scope="col">Name</th>
                                <th scope="col">Price</th>
                                <th scope="col">Quantity</th>
                                <th scope="col">Total</th>
                            </tr>
                        </thead>

                        <tbody>
                        <c:forEach items="${listProduct}" var="product">
                            <tr>
                                <td>${product.proName}</td>
                                <td>${product.proPrice}</td>
                                <td>
                                    <form>
                                        <input type="hidden" name="proId" value="${product.proId}">
                                        <button class="btn btn-danger" formaction="changeCart" formmethod="post">- </button>
                                        ${product.proQuantity}
                                        <button class="btn btn-success" formaction="changeCart" formmethod="get"> +</button>
                                    </form>
                                </td>
                                <td>${product.proQuantity*product.proPrice}</td>
                                <c:set var="total" value="${total+product.proQuantity*product.proPrice}"></c:set>
                                </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <div class="pay text-center bg-light mb-5 mt-5 pt-3 pb-3">

                    <h3>Total :${total}</h3>
                    <h3>Money :${account.cash}</h3>
                    <form>
                        <c:if test="${total>account.cash}">
                            <div class="alert alert-danger" id="money-alert">
                                <strong>Alert!</strong> You don't have enought money!
                            </div>
                        </c:if>
                        <c:if test="${total<=account.cash}">
                            <input type="hidden" name="total" value="${total}">
                            <button class="btn btn-danger btn-lg" formaction="pay"> PayMent </button>
                        </c:if>
                    </form>


                </div>

            </div>
        </c:if>
        <%@include file="./Footer.jsp" %>
        <script>

        </script>
    </body>


</html>
