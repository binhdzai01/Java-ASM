<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<nav class="navbar navbar-expand-sm bg-dark">
    <div class="container">
        <a class="navbar-brand" href="home">PinkShop</a>
        <ul class="navbar-nav navbar-right">
            <li class="nav-item mx-3"><a class="nav-link" href="home">Home</a></li>
            <li class="nav-item mx-3"><a class="nav-link" href="all-product">Shop</a></li>
            

            <c:if test="${sessionScope.accountsession!=null}">
                <li class="nav-item mx-3"><a class="nav-link" href="cart"><i class="fa fa-shopping-cart" aria-hidden="true"></i> Cart</a></li>
                <li class="nav-item "><a class="nav-link" href="profile">Profile <span><i class="fa fa-user"></i></span></a></li> 
                <li class="nav-item "><a class="nav-link" href="logout">Logout <span><i class="fa fa-sign-out"></i></span></a></li> 
                        </c:if>

            <c:if test="${sessionScope.accountsession eq null}">
                <li class="nav-item mx-3"><a class="nav-link" href="signup">
                        Sign Up <span><i class="fa fa-user-plus"></i></span></a></li>
                <li class="nav-item mx-3"><a class="nav-link" href="login">
                        Login <span><i class="fa fa-sign-out"></i></span></a></li>
                        </c:if>

        </ul>
    </div>
</nav>