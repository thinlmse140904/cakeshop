<%-- 
    Document   : index
    Created on : Sep 30, 2020, 6:55:12 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="google-signin-scope" content="profile email">
        <meta name="google-signin-client_id" content="431325923888-dvj7oojdhu7gs781164igkvfm0lu31cj.apps.googleusercontent.com">
        <script src="https://apis.google.com/js/platform.js" async defer></script>
        <link href="css/all.min.css" rel="stylesheet" type="text/css"/>
        <script type="text/javascript">
            function showLoginBox() {
                var loginBox = document.getElementById('LoginBox');
                if (loginBox.style.display === 'none') {
                    loginBox.style.display = "block";
                } else {
                    loginBox.style.display = "none";
                }
            }
            function showCreateBox() {
                var createBox = document.getElementById('CreateBox');
                if (createBox.style.display === 'none') {
                    createBox.style.display = "block";
                } else {
                    createBox.style.display = "none";
                }
            }
            function showUpdateForm() {
                var updateForm = document.getElementById('updateForm');
                if (updateForm.style.display === 'none') {
                    updateForm.style.display = "block";
                } else {
                    updateForm.style.display = "none";
                }
            }
            function showSearchForm() {
                var searchForm = document.getElementById('searchForm');
                if (searchForm.style.display === 'none') {
                    searchForm.style.display = "block";
                } else {
                    searchForm.style.display = "none";
                }
            }
            function showPriceFilter() {
                var priceFilter = document.getElementById('priceFilter');
                if (priceFilter.style.display === 'none') {
                    priceFilter.style.display = "block";
                } else {
                    priceFilter.style.display = "none";
                }
            }
            function showLoginForm() {
                var loginForm = document.getElementById('LoginBox');
                if (loginForm.style.display === 'none') {
                    loginForm.style.display = "block";
                } else {
                    loginForm.style.display = "none";
                }
            }
            function showLoginMsg() {
                var loginMsg = document.getElementById('accountNotFoundBox');
                if (loginMsg.style.display === 'none') {
                    loginMsg.style.display = "block";
                } else {
                    loginMsg.style.display = "none";
                }
            }
            function showAdminMsg() {
                var adminMsg = document.getElementById('adminMsg');
                if (adminMsg.style.display === 'none') {
                    adminMsg.style.display = "block";
                } else {
                    adminMsg.style.display = "none";
                }
            }
            function showCostumerMsg() {
                var customerMsg = document.getElementById('costumerMsg');
                if (customerMsg.style.display === 'none') {
                    customerMsg.style.display = "block";
                } else {
                    customerMsg.style.display = "none";
                }
            }
            function getScrollPosition() {
                var posi = document.getElementsByClassName("posi");
                var pos = document.documentElement.scrollTop;
                for (var i = 0; i < posi.length; i++) {
                    posi[i].value = pos;
                }
            }
            function onSignIn(googleUser) {
                // Useful data for your client-side scripts:
                var profile = googleUser.getBasicProfile();
                console.log("ID: " + profile.getId()); // Don't send this directly to your server!
                console.log('Full Name: ' + profile.getName());
                console.log('Given Name: ' + profile.getGivenName());
                console.log('Family Name: ' + profile.getFamilyName());
                console.log("Image URL: " + profile.getImageUrl());
                console.log("Email: " + profile.getEmail());

                // The ID token you need to pass to your backend:
                var id_token = googleUser.getAuthResponse().id_token;
                console.log("ID Token: " + id_token);
                
                var name = document.getElementById("Name");
                var email = document.getElementById("email");
                    <c:if test="${sessionScope.IS_LOGIN != 'login'}">
                            name.value = profile.getName();
                            email.value = profile.getEmail();
                            document.getElementById("GoogleLoginBtn").click();
                    </c:if>
              }
              function signOut() {
                var auth2 = gapi.auth2.getAuthInstance();
                auth2.signOut().then(function () {
                  console.log('User signed out.');
                });
                document.getElementById("logoutBtn").click();
              }
            document.getElementById('checkBox')
        </script>
        <script type="module">
            document.documentElement.scrollTop = ${param.txtPosition};
        </script>
        <style>
            body{
                background-image: url('image/background/delicious-moon-cake-background-realistic-style_23-2147868789.jpg');
                background-size: cover;
                background-repeat: no-repeat;
            }
            .oneCake{
                width: 20vw;
                display: inline-block;
                border: 2px solid whitesmoke;
                padding: 2vh 5vw;
                margin: 0px 0.7vw;
                padding-bottom: 7vh;
                position: relative;
                transition: ease-in 0.2s;
                margin-top: 5vh;
                background-color: bisque;
                border-radius: 15px;
            }
            #LoginBox{

            }
            .Icon{
                display: block;
            }
            .notiBox{
                position: fixed;
                top: 12%;
                left: 26%;
                background-color: mintcream;
                text-align: center;
                padding: 20px;
                border-radius: 10px;
                border: 1.5px solid aqua;
                width: 39vw;
                display: none;
            }
            .inputField{
                margin: 7px 0px;
                border: none;
                background-color: powderblue;
                border-radius: 3px;
                height: 5vh;
                width: 79%;
            }
            .icon{
                position: absolute;
                top: 1vh;
                right: 2vh;
                border: none;
                background-color: transparent;
                font-size: 4vh;
                color: coral;
                opacity: 0.5;
                transition: ease-in 0.2s;
            }
            .icon:hover{
                opacity: 1;
            }
            #allCake{
                margin: 8vh 0;
            }
            .close{
                position: absolute;
                top: 1vh;
                right: 1vh;
                border: none;
                background-color: transparent;
                font-size: 27px;
                cursor: pointer;
                color: cadetblue;
                transition: 0.3s ease-in;
            }
            .close:hover{
                color: red;
            }
            #closeLoginForm{
                position: absolute;
                top: 1vh;
                right: 1vh;
                border: none;
                background-color: transparent;
                font-size: 27px;
                cursor: pointer;
                color: cadetblue;
                transition: 0.3s ease-in;
            }
            #selectBox{
                height: 6vh;
                width: 10vw;
                background-color: cornflowerblue;
                color: white;
                font: initial;
                border-radius: 4px;
                border: 2px solid skyblue;
            }
            .chooseBtn{
                height: 7vh;
                cursor: pointer;
                display: block;
                margin: auto;
                width: 21vw;
                border-radius: 10px;
                background-color: greenyellow;
                border: green 1.5px solid;
            }
            .dropBox{
                height: 5.2vh;
                margin: 0 2vw;
                background: mintcream;
                border-radius: 10px;
                font-weight: bold;
            }
            .paging{
                background-color: #515050;
                border: 1.8px #a2a2a2 solid;
                height: 7vh;
                width: 7vh;
                border-radius: 7px;
                color: white;
                font-size: x-large
            }
            .navDiv{
                display: inline-block;
                /*                height: 10vh;*/
            }
            .navBtn{
                height: 7vh;
                width: 7vh;
            }
            i{
                font-size: 4vh;
            }
            .navBtn{
                height: 12vh;
                width: 12vh;
                margin: 2vh 1vh;
                border: none;
                border-radius: 10px;
                background-color: chocolate;
                color: white;
                opacity: 0.6;
                transition: 0.17s ease-in-out;
                cursor: pointer
            }
            .navBtn:hover{
                opacity: 1;
                background-color: brown;
            }
            .Icon{
                display: block;
                background-image: url(image/background/background.png);
                background-size: 100%;
                background-repeat: no-repeat;
                background-position: bottom;
                height: 34vh;
                margin-top: 7vh;
                border-radius: 5px;
            }
            .allBtn{
                text-align: center
            }
            #logoutBtn{
                margin: 2vh 0;
            }
            .addToCartBtn{
                padding: 1vh 2vh;
                background-color: chocolate;
                color: whitesmoke;
                border: 1.2px solid black;
                border-radius: 5px;
                opacity: 0.75;
                transition: 0.2s ease-in-out;
            }
            .addToCartBtn:hover{
                opacity: 1;
                background-color: darkorange;
            }
        </style>
        <title>Moon Cake Shop</title>
    </head>
    <body>
        <c:set var="error" value="${requestScope.ERROR_CREATE}" />
        <c:set var="errorUpdate" value="${requestScope.ERROR_UPDATE}" />
        <c:set var="errorAdmin" value="${requestScope.ADMIN_ERROR}" />
        <c:set var="errorCostumer" value="${requestScope.COSTUMER_ERROR}" />
        <c:if test="${sessionScope.ACCOUNT.role eq 'admin'}" var="isAdmin"/>
        <c:if test="${sessionScope.ACCOUNT.role eq 'member'}" var="isMember"/>
        <c:if test="${ON_SEARCH_PROCESS}" var="onSearch"/>
        <div id="subBackground">
        </div>
        <div class="Icon">
            <div class="navDiv">
                <form action="Home">
                    <button name="action" value="1_home_page" class="navBtn">
                        <i class="fas fa-home"></i>
                        <br>
                        <p>Home</p>
                    </button>
                </form>
            </div>    
            <div class="navDiv">
                <button onclick="showLoginBox()" <c:if test="${sessionScope.ACCOUNT != null}" var="isLogin">style="display: none"</c:if> class="navBtn">
                        <i class="fas fa-sign-in-alt"></i>
                        <br>
                        <p>Login</p><br>
                        
                    </button>

                </div>    
                <div class="navDiv">    
                    <form action="Logout" onsubmit="signOut();">
                        <button type="submit" name="action" value="Logout" <c:if test="${!isLogin && sessionScope.IS_LOGIN == null}">style="display: none;"</c:if> id="logoutBtn" class="navBtn">
                            <i class="fas fa-sign-out-alt"></i>
                            <br>
                            <p>Logout</p>
                        </button>
                    </form>
                </div>        
                <div class="navDiv">            
                <c:if test="${isAdmin}">
                    <button id="create" onclick="showCreateBox()" class="navBtn"> 
                        <i class="far fa-plus-square"></i><br>
                        <p>Create</p>
                    </button>
                </c:if>
            </div>
            <div class="navDiv">            
                <button onclick="showSearchForm()" class="navBtn">
                    <i class="fas fa-search"></i><br>
                    <p>Search</p>
                </button>
            </div>
            <c:if test="${!isAdmin}">
                <div id="cart" class="navDiv">
                    <form action="ViewCart">
                        <button type="submit" name="action" value="View Cart" class="navBtn">
                            <i class="fas fa-shopping-cart"></i><br>
                            <p>view cart</p>
                        </button>
                    </form>
                </div>
            </c:if>
        </div>
        <!--bellow is div that contain login form-->
        <div style="display: none;" id="LoginBox" class="notiBox">
            <button id="closeLoginForm" onclick="showLoginForm()" class="close">
                <i class="fas fa-times"></i>
            </button>
            <form action="Login" method="POST">
                <input type="email" name="txtEmail" value="" placeholder="Email" class="inputField" required="required"/>
                <input type="password" name="txtPassword" value="" placeholder="Password" class="inputField" required="required"/><br>
                <button type="submit" name="action" value="Login">
                    Login
                </button>
            
            </form>
            <div class="g-signin2" data-onsuccess="onSignIn" data-theme="dark"></div>
        </div>
            
        <!--div that contains all cake-->
        <div id="allCake">
            <c:forEach var="cake" items="${requestScope.LIST_CAKE}" >
                <!--div that contains one cake--> 
                <div class="oneCake">
                    <c:if test="${isAdmin}">
                        <form action="Update">
                            <input type="hidden" name="txtCakeID" value="${cake.cakeID}" />
                            <input type="hidden" name="txtPosition" value="" class="posi"/>
                            <button type="submit" name="action" value="Update Cake" class="icon">
                                <i class="fas fa-edit"></i>
                            </button>
                        </form>
                    </c:if>
                    <img src="${cake.image}" style="height :37vh;"/>
                    <p>
                        ${cake.name}
                        <br>
                        price: ${cake.price}$
                        <br>
                        available: ${cake.quantity}
                        <br>
                        category : ${cake.category}
                        <br>
                        MFG : ${cake.dateOfCreate}
                        <br>
                        EXP : ${cake.expirationDate}
                    </p>

                    <c:if test="${!isAdmin}">
                        <form action="AddToCart" onsubmit="getScrollPosition()">
                            <div style="text-align: center">
                                <input type="hidden" name="txtCakeID" value="${cake.cakeID}" />
                                <input type="number" name="txtQuantity" value="" class="inputField" placeholder="enter quantity you want to buy"/>
                                <input type="hidden" name="txtPosition" value="" class="posi"/>
                                <button type="submit" name="action" value="Add to cart" class="addToCartBtn">
                                    <i class="fas fa-cart-plus"></i><br>
                                    Add to cart
                                </button>
                            </div>
                        </form>
                    </c:if>
                    <c:if test="${cake.cakeID eq requestScope.CAKE_ID_CANT_ADD}">
                        <font color="red">
                        ${requestScope.ERROR_QUANTITY.quantityError}
                        </font>
                    </c:if>
                </div>
                <!--end div-->
            </c:forEach>
        </div>
        <!--end div contains cake-->


        <!--this is create form-->
        <div id="CreateBox" 
             <c:if test="${requestScope.IS_SHOW_CREATE != null || error != null}" var="isChoose">style="display: block"</c:if>
             <c:if test="${!isChoose}">style="display: none"</c:if>
                 class="notiBox"
                 >
                 <button id="closeCreateButton" onclick="showCreateBox()" class="close">
                     <i class="fas fa-times"></i>
                 </button>
                 <form action="Create" onsubmit="getScrollPosition()">
                     <input type="text" name="txtCakeName" value="${param.txtCakeName}" placeholder="Cake name" class="inputField" required="required"/><br>
                    <font color="red">
                    ${error.nameError}
                    </font>
                    <input type="number" name="txtPrice" value="${param.txtPrice}" placeholder="price" class="inputField" required="required"/><br>
                    <font color="red">
                    ${error.priceError}
                    </font>
                    MFG: <input type="date" name="txtDateOfCreate" value="${param.txtDateOfCreate}" placeholder="Date of create" class="inputField" required="required"/><br>
                    <font color="red">
                    ${error.dateError}
                    </font>
                    EXP: <input type="date" name="txtExpirationDate" value="${param.txtExpirationDate}" placeholder="Expiration date" class="inputField" required="required"/><br>
                    <font color="red">
                    ${error.dateError}
                    </font>
                    <input type="number" name="txtQuantity" value="${param.txtQuantity}" placeholder="quantity" class="inputField" required="required"/><br>
                    <font color="red">
                    ${error.quantityError}
                    </font>
                    <p style="display: inline-block;">Category:   </p><select name="txtCategory" id="selectBox" class="dropBox" >
                        <c:forEach var="category" items="${sessionScope.CATEGORY_LIST}">
                            <option value="${category.categoryID}">${category.category}</option>
                        </c:forEach>
                    </select><br>
                    <font color="red">
                    ${error.categoryName}
                    </font>
                    <input type="text" name="txtImage" value="${requestScope.FILE_PATH}" id="imageInput" readonly="readonly" class="inputField"/>
                    <font color="red">
                    ${error.imageError}
                    </font>
                    <button type="submit" name="action" value="Choose Image Create" style="display: block;margin: auto;" class="chooseBtn">
                        <i class="far fa-image"></i>Choose a Image
                    </button><br>
                    <input type="hidden" name="txtPosition" value="" class="posi"/>
                    <img src="" id="myImg"/><br>
                    <button type="submit" name="action" value="Create Cake" class="submitBtn">
                        Add Cake
                    </button> 
             </form>
        </div>
        <!--end create form-->


        <!--update form-->
        <div id="updateForm" <c:if test="${requestScope.IS_SHOW_UPDATE || requestScope.ERROR != null}">style="display: block"</c:if> class="notiBox">
                <button id="closeUpdateButton" onclick="showUpdateForm()" class="close">
                    <i class="fas fa-times"></i>
                </button>
                <form action="ApplyChange" onsubmit="getScrollPosition()">
                    <input type="text" name="txtCakeName" value="${requestScope.CAKE_TO_UPDATE.name}" placeholder="Cake name" class="inputField" required="required"/><br>
                <font color="red">
                ${errorUpdate.nameError}
                </font>
                <input type="number" name="txtPrice" value="${requestScope.CAKE_TO_UPDATE.price}" placeholder="price" class="inputField" required="required"/><br>
                <font color="red">
                ${errorUpdate.priceError}
                </font>
                MFG: <input type="date" name="txtDateOfCreate" value="${requestScope.MY_DATE.dateOfCreate}" placeholder="Date of create" class="inputField" required="required"/><br>
                <font color="red">
                ${errorUpdate.dateError}
                </font>
                EXP: <input type="date" name="txtExpirationDate" value="${requestScope.MY_DATE.expirationDate}" placeholder="Expiration date" class="inputField" required="required"/><br>
                <font color="red">
                ${errorUpdate.dateError}
                </font>
                <input type="number" name="txtQuantity" value="${requestScope.CAKE_TO_UPDATE.quantity}" placeholder="quantity" class="inputField" required="required"/><br>
                <font color="red">
                ${errorUpdate.quantityError}
                </font>
                <c:if test="${requestScope.IS_CHANGE}">
                    <input type="hidden" name="txtIsChange" value="true" />
                </c:if>
                <div>
                    <p style="display: inline-block;">Category:   
                    </p><select name="txtCategory" id="selectBox1" class="dropBox">
                        <c:forEach var="category" items="${sessionScope.CATEGORY_LIST}">
                            <option value="${category.categoryID}" <c:if test="${requestScope.CAKE_TO_UPDATE.category eq category.categoryID}"> selected="selected"</c:if>>${category.category}</option>
                        </c:forEach>
                    </select><br>
                    <font color="red">
                    ${errorUpdate.categoryName}
                    </font>
                    <select name="txtStatus">
                        <option value="Actived">Available</option>
                        <option value="Out of order">Out of order</option>
                    </select>
                </div>
                <input type="text" name="txtImage" value="${requestScope.CAKE_TO_UPDATE.image}" id="imageInput" readonly="readonly" class="inputField" required="required"/>
                <font color="red">
                ${errorUpdate.imageError}
                </font>
                <input type="hidden" name="txtCakeID" value="${requestScope.CAKE_TO_UPDATE.cakeID}" />
                <button type="submit" name="action" value="Choose Image Update" style="display: block;margin: auto;" class="chooseBtn">
                    <i class="far fa-image"></i>Choose a Image
                </button><br>
                <input type="hidden" name="txtCakeID" value="${requestScope.CAKE_TO_UPDATE.cakeID}" />
                <img src="" id="myImg"/><br>
                <input type="hidden" name="txtPosition" value="" class="posi"/>
                <button type="submit" name="action" value="Apply Change" class="submitBtn">
                    Apply change
                </button> 

            </form>

        </div>
        <!--end update form-->


        <!-- div that contains search form-->

        <div id="searchForm" class="notiBox">
            <button id="closeSearchBtn" onclick="showSearchForm()" class="close">
                <i class="fas fa-times"></i>
            </button>
            <form action="Search"onsubmit="getScrollPosition()">
                <div>
                    <input type="text" name="txtSearch" style="width: 62%;" value="${param.txtSearch}" class="inputField"/>
                    <select name="txtCategory" class="dropBox">
                        <option value="All"
                                <c:if test="${param.txtCategory eq 'All'}">selected="selected"</c:if>    
                                    >All</option>
                        <c:forEach var="category" items="${sessionScope.category}">
                            <option value="${category.categoryID}"
                                    <c:if test="${param.txtCategory eq category.categoryID}">selected="selected"</c:if>
                                        >
                                    ${category.category}
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <input type="hidden" name="txtPosition" value="" class="posi"/>
                <input type="checkbox" name="txtIsCheck" value="on" id="checkBox" onclick="showPriceFilter()"/>Using price filter<br>
                <div id="priceFilter" style="display: none;">
                    from : <input type="number" name="txtPriceFloor" class="inputField" value="${param.txtPriceFloor}" style="width: 50%" /><br>
                    to : <input type="number" name="txtPriceCeil" value="${param.txtPriceCeil}" class="inputField" style="width: 50%"/><br>
                </div>
                <button type="submit" name="action" value="1_search_page"> 
                    <i class="fas fa-search"></i>
                    Search
                </button>
            </form>

        </div>

        <!--end search form-->

        <!--bellow is div that contain all paging button-->
        <div class="allBtn">
            <form action="Paging">
                <c:forEach begin="1" end="${sessionScope.NUMBER_OF_PAGE}" step="1" varStatus="counter">
                    <c:if test="${onSearch}">
                        <input type="hidden" name="txtSearch" value="${param.txtSearch}" />
                        <input type="hidden" name="txtCategory" value="${param.txtCategory}" />
                        <c:if test="${param.txtIsCheck != null}">
                            <input type="hidden" name="txtIsCheck" value="${param.txtIsCheck}" />
                        </c:if>
                        <input type="hidden" name="txtPriceFloor" value="${param.txtPriceFloor}" />
                        <input type="hidden" name="txtPriceCeil" value="${param.txtPriceCeil}" />
                        <button type="submit" value="${counter.count}_search_page" name="action" class="paging" <c:if test="${counter.count eq sessionScope.pageNO}">style="background-color: orange;color: black;font-weight: 600;border: none;"</c:if>>
                            ${counter.count}
                        </button>
                    </c:if>
                    <c:if test="${!onSearch}">
                        <button type="submit" value="${counter.count}_home_page" name="action" class="paging" <c:if test="${counter.count eq sessionScope.pageNO}">style="background-color: orange;color: black;font-weight: 600;border: none;"</c:if>>
                            ${counter.count}
                        </button>
                    </c:if>
                </c:forEach>
            </form>
        </div>
        <!--bellow is div that contains login's failure message-->
        <div class="notiBox" id="accountNotFoundBox" <c:if test="${requestScope.ACCOUNT_NOT_FOUND}">style="display: block"</c:if>>
            <button id="closeNotFoundhBtn" onclick="showLoginMsg()" class="close">
                <i class="fas fa-times"></i>
            </button>
            <p>
                Account not found<br>
                please check your password and email!
            </p>
        </div>
            <div class="notiBox" <c:if test="${requestScope.IS_SHOW_GG_INFO}">style="display: block"</c:if>>
            <form action="CreateGGAccount" method="POST">
                <input type="text" name="txtName" value="${sessionScope.ACCOUNT.name}" placeholder="Your name" required="required"/>
                <input type="hidden" name="txtEmail" value="${sessionScope.ACCOUNT.email}" />
                <input type="text" name="txtAddress" value="" placeholder="Address" required="required"/>
                <input type="text" name="txtPhoneNo" value="" placeholder="Phone number" required="required"/>
                <button type="submit" name="action" value="submit">
                    Submit
                </button>
            </form>
            </div>
            <div class="notiBox" style="display: ${requestScope.IS_SHOW_ADMIN_MSG}" id="adminMsg">
            <button onclick="showAdminMsg()" class="close">
                    <i class="fas fa-times"></i>
                </button>
                <p>   ${errorAdmin}</p>
            </div>
            <div class="notiBox" style="display: ${requestScope.IS_SHOW_COSTUMER_MSG}" id="costumerMsg">
                <button onclick="showCostumerMsg()" class="close">
                    <i class="fas fa-times"></i>
                </button>
                <p>   ${errorCostumer}</p>
            </div>
                <c:if test="${isMember}">
                    <a href="OrderTracking">Order tracking</a>
                </c:if>
                    <form action="GmailLogin" style="display: none" id="googleLogin" method="POST">
                        <input type="text" name="txtName" value="" id="Name"/>
                        <input type="hidden" name="txtEmail" value="" id="email" />
                        <button type="submit" name="action" value="Login With Gmail" id="GoogleLoginBtn">
                            login
                        </button>
                    </form>
    </body>
</html>
