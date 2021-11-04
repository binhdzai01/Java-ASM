
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
            <h1 class="text-center text-light pb-3">Manage The Account List</h1>
            <table class="table table-dark">
                <caption>

                </caption>
                <thead>
                    <tr>
                        <th>Account Name</th>
                        <th><span><i class="fa fa-money text-light"></i></span> Cash</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <jsp:useBean id="dao" class="dal.AccountDAO" ></jsp:useBean>
                    <c:forEach items="${dao.allAccount}" var = "item">
                        <c:if test="${item.isAdmin ==false}">
                            <tr>
                                <td>${item.accountName}</td>

                                <td>$<fmt:formatNumber pattern="##.##" value="${item.cash}"></fmt:formatNumber>
                                    
                                </td>
                                    <td class="text-center">
                                        <form>
                                            <input type="hidden" name="accountId" value="${item.accountId}"/>
                                            <button class="btn btn-primary text-light mr-5" formaction="update-role" formmethod="post">
                                                Update Role
                                            </button>
                                            <button class="btn btn-danger text-light ml-5" formaction="delete-account" formmethod="post">
                                                Delete
                                            </button>
                                        </form>
                                    </td>
                                </tr>
                        </c:if>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <%@include file="./Footer.jsp" %>
    </body>
</html>
