<%-- 
    Document   : cart
    Created on : Oct 4, 2020, 8:56:35 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Cart Page</title>
        <link href="css/all.min.css" rel="stylesheet" type="text/css"/>
        <script type="text/javascript">
            function getScrollPosition() {
                var x = document.getElementsByClassName('posi');

                var pos = document.documentElement.scrollTop;
                var i;
                for (i = 0; i < x.length; i++) {
                    x[i].value = pos;
                }
            }
            function showInformationForm() {
                var info = document.getElementById('informationForm');
                if (info.style.display === 'none') {
                    info.style.display = "block";
                } else {
                    info.style.display = "none";
                }
            }

            function showInformationForm1() {
                var info1 = document.getElementById('informationForm_1');
                if (info1.style.display === 'none') {
                    info1.style.display = "block";
                } else {
                    info1.style.display = "none";
                }

            }
            function showConfirmRemoveBox() {
                var confirm = document.getElementById('confirmRemoveBox');
                if (confirm.style.display === 'none') {
                    confirm.style.display = "block";
                } else {
                    confirm.style.display = "none";
                }

            }
            function closeMsgBox() {
                var msgBox = document.getElementById('msgBox');
                if (msgBox.style.display === 'none') {
                    msgBox.style.display = "block";
                } else {
                    msgBox.style.display = "none";
                }
            }
            function checkRadioWithCash() {
                var x = document.getElementsByClassName('paymentTxtForMember');
                for (var i = 0; i < x.length; i++) {
                    x[i].value = 'Cash';
                }
            }
            function checkRadioWithOnl() {
                var x = document.getElementsByClassName('paymentTxtForMember');
                for (var i = 0; i < x.length; i++) {
                    x[i].value = 'Online payment';
                }
            }
        </script>
        <script type="module">
            document.documentElement.scrollTop = ${param.txtPosition};

        </script>
        <style>
            body{
                background-image: url('image/background/delicious-moon-cake-background-realistic-style_23-2147868789.jpg');
                background-size: 100%;
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

            #paraLink {
                font-size: large;
                font-weight: 700;
                color: white;
                padding-top: 1.8vh;
            }
            #contLink{
                background-color: cornflowerblue;
                height: 7vh;
                width: 19vw;
                margin-left: 3vw;
                text-align: center;
                border-radius: 10px;
                cursor: pointer;
            }
            a{
                text-decoration: none;
            }
            #totalBox{
                background-color: cornflowerblue;
                height: 20vh;
                width: 23vw;
                border-radius: 10px;
                display: inline-block;
                margin-left: 70%;
                position: relative;
            }
            #totalTXT{
                text-align: center;
                font-weight: 900;
                font-size: x-large;
                margin-top: -10px;
            }
            .closingBtn{
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
            .closingBtn:hover{
                color: red;
            }
            #informationForm{
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
            #msgBox{
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
            /*            #closeInformationBtn_1{
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
                        #closeInformationBtn_1:hover{
                            color: red;
                        }*/
            #informationForm_1{
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
            #confirmRemoveBox{
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
            .removeFromCart{
                position: absolute;
                top: 1vh;
                right: 0.5vw;
                background-color: transparent;
                border: none;
                font-size: 5vh;
                color: slategray;
                opacity: 0.3;
                transition: 0.24s linear;
            }
            .removeFromCart:hover{
                color : darkgrey;
                opacity: 1;
            }
            .checkoutBtn{
                cursor: pointer;
                position: absolute;
                bottom: 10px;
                background-color: slategray;
                color: white;
                height: 5vh;
                border-radius: 10px;
                margin-left: 14%;
                width: 66%;
                border: 1.5px solid darkslategrey;
            }
            #emptyDiv{
                height: 10vh;
                background-color: orange;
                width: 50%;
                margin: 0 auto;
                border-radius: 15px;
            }
            #emptyMsg{
                text-align: center;
                padding-top: 2.7vh;
                font-size: x-large;
                font-weight: bolder;
            }
            .paymentBox{
                width: 56%;
                margin: 2vh auto;
                text-align: left;
                cursor: pointer;
            }
            .paymentBoxForMember{
                height: 10vh;
                position: absolute;
                top: 15vh;
                left: 6vh;
                width: 20vw;
                padding: 2vh 0;
                align-items: center;
                background-color: cornflowerblue;
                color: white;
                font-size: 20px;
                border-radius: 10px;
                font-weight: 600;
            }
            #EditQuantityBox{
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
        </style>
    </head>
    <body>
        <c:set var="ListError" value="${requestScope.LIST_QUANTITY_ERROR}"/>
        <c:if test="${sessionScope.ACCOUNT.role eq 'member'}" var="isMember"></c:if>
            <div>
                <a href="Paging">
                    <div id="contLink">
                        <p id="paraLink">
                            Continue Shopping
                        </p>
                    </div>
                </a>
                <!--bellow this is div that contain TOTAL-->
            <c:if test="${not empty sessionScope.CART.shoppingCart}">
                <div id="totalBox">
                    <p style="font-size: 17px;margin-left: 8%;width: 80%;margin-left: 8%;">Total : </p>
                    <p id="totalTXT">
                        ${sessionScope.CART.total}$
                    </p>

                    <button onclick="showInformationForm()"  class="checkoutBtn">
                        <i class="fas fa-money-check-alt"></i>
                        Check out
                    </button>
                </div>
            </c:if>
        </div>
        <c:if test="${requestScope.IS_SHOW_CART_MSG}">
            <div id="emptyDiv">
                <p id="emptyMsg">
                    ${requestScope.ERROR_CART_MSG}
                </p>
            </div>
        </c:if>
        <c:if test="${!requestScope.IS_SHOW_CART_MSG}">
            <c:forEach var="cake" items="${requestScope.LIST_CART}" >
                <!--div that contains one cake--> 
                <div class="oneCake">
                    <img src="${cake.cakeDTO.image}" style="height :37vh;"/>
                    <p>
                        ${cake.cakeDTO.name}
                        <br>
                        price: ${cake.cakeDTO.price}
                        <br>
                        category : ${cake.cakeDTO.category}
                        <br>
                        MFG : ${cake.cakeDTO.dateOfCreate}
                        <br>
                        EXP : ${cake.cakeDTO.expirationDate}
                    </p>
                    <form action="Edit" onsubmit="getScrollPosition()">
                        <input type="hidden" name="txtCakeID" value="${cake.cakeDTO.cakeID}" />
                        <input type="hidden" name="txtPosition" value="" class="posi"/>
                        Quantity : <input type="number" name="txtQuantity" value="${cake.quantity}" readonly="readonly"/>
                        <button class="removeFromCart" onclick="showConfirmRemoveBox()" type="submit" name="action" value="Remove from cart">
                            <i class="fas fa-times-circle"></i>
                        </button>
                        <button name="action" type="submit" value="Edit">
                            Edit
                        </button>
                    </form>
                    <c:if test="${cake.cakeDTO.cakeID eq requestScope.CAKE_ID}">
                        <font color="red">${requestScope.ERROR_QUANTITY.quantityError}</font>
                    </c:if>
                    <c:if test="${ not empty ListError}">
                        <c:forEach var="Error" items="${ListError}">
                            <c:if test="${Error.cakeIDError eq cake.cakeDTO.cakeID}">
                                <font color="red">${Error.quantityError}</font>
                            </c:if>
                        </c:forEach>
                    </c:if>
                </div>

            </c:forEach>
        </c:if>





        <!--bellow this is div that contain information input for ALL cake-->
        <div id="informationForm"
             <c:if test="${requestScope.IS_SHOW_GUEST_INFO_ALL}">
                 style="display: block"
             </c:if>
             >
            <button id="closeInformationBtn" onclick="showInformationForm()" class="closingBtn">
                <i class="fas fa-times"></i>
            </button>
            <form action="SubCheckOutAll">
                <input type="text" name="txtName" value="<c:if test="${isMember}">${sessionScope.ACCOUNT.name}</c:if>" placeholder="Your name" class="inputField" required="required"/><br>
                    <font color="red">
                ${requestScope.ERROR_GUEST_ALL.nameError}
                </font>
                <input type="text" name="txtAddress" value="<c:if test="${isMember}">${sessionScope.ACCOUNT.address}</c:if>" placeholder="Your address" class="inputField" required="required"/><br>
                    <font color="red">
                ${requestScope.ERROR_GUEST_ALL.addressError}
                </font>
                <input type="text" name="txtPhoneNo" value="<c:if test="${isMember}">${sessionScope.ACCOUNT.phoneNo}</c:if>" placeholder="Your phone number" class="inputField" required="required"/><br>
                    <font color="red">
                ${requestScope.ERROR_GUEST_ALL.phoneNoError}
                </font>
                <div class="paymentBox">
                    <input type="radio" name="txtPayment" value="Cash" checked="checked">
                    <label for="Cash">Cash</label><br>
                    <input type="radio" name="txtPayment" value="Online Payment">
                    <label for="Online payment">Online payment</label><br>    
                </div>
                <button type="submit" name="action" value="Check Out All" class="squareBtn">
                    Submit
                </button>
            </form>
        </div>



        <!--bellow this is div that contains information input for checkout ONE product-->
        <div id="informationForm_1" 
             <c:if test="${requestScope.IS_SHOW_GUEST_INFO}">
                 style="display: block"
             </c:if>
             >
            <button id="closeInformationBtn_1" onclick="showInformationForm1()" class="closingBtn">
                <i class="fas fa-times"></i>
            </button>
            <form action="CheckOut">
                <input type="hidden" name="txtQuantity" value="${requestScope.QUANTITY}" />
                <input type="hidden" name="txtCakeID" value="${requestScope.CAKE_ID}" />
                <input type="text" name="txtName" value="" placeholder="Your name" class="inputField" required="required"/><br>
                <font color="red">
                ${requestScope.ERROR_GUEST.nameError}
                </font>
                <input type="text" name="txtAddress" value="" placeholder="Your address" class="inputField" required="required"/><br>
                <font color="red">
                ${requestScope.ERROR_GUEST.addressError}
                </font>
                <input type="text" name="txtPhoneNo" value="" placeholder="Your phone number" class="inputField" required="required"/><br>
                <font color="red">
                ${requestScope.ERROR_GUEST.phoneNoError}
                </font>
                <br>
                <div class="paymentBox">
                    <input type="radio" name="txtPayment" value="Cash" checked="checked">
                    <label for="Cash">Cash</label><br>
                    <input type="radio" name="txtPayment" value="Online Payment">
                    <label for="Online payment">Online payment</label><br>    
                </div>
                <button type="submit" name="action" value="Checkout" class="squareBtn">
                    Submit
                </button>
            </form>
        </div>



        <!--bellow is div that contain remove from cart confirmation box-->
        <div id="confirmRemoveBox"
             <c:if test="${requestScope.IS_SHOW_CONFIRM}"> 
                 style="display: block"
             </c:if>
             >
            <p>
                Do you really want to remove ${requestScope.QUANTITY} ${requestScope.CAKE_DTO.name} from your cart ? 
            </p>
            <form action="Confirm">
                <input type="hidden" name="txtCakeID" value="${requestScope.CAKE_DTO.cakeID}"/>
                <button class="squareBtn" type="submit" name="action" value="Confirm remove">
                    yes
                </button>
            </form>
            <button onclick="showConfirmRemoveBox()" class="squareBtn">
                no
            </button>
        </div>

        <!--bellow this is div that contains confirm remove message-->
        <div  id="msgBox"
              <c:if test="${requestScope.IS_SHOW_MSG}"> 
                  style="display: block"
              </c:if>
              >
            <button onclick="closeMsgBox()" class="closingBtn">
                <i class="fas fa-times"></i>
            </button>
            Remove successfully !!!
        </div>
        <c:if test="${isMember}">
            <div class="paymentBoxForMember">
                <font color="black">Using :</font> <br>
                <input type="radio" name="payment" value="Cash" checked="checked" onclick="checkRadioWithCash()">
                <label for="Cash">Cash</label><br>
                <input type="radio" name="payment" value="Online Payment" onclick="checkRadioWithOnl()">
                <label for="Online payment">Online payment</label><br>    
            </div>
        </c:if>
        <div id="EditQuantityBox" style="display: ${requestScope.IS_SHOW_EDIT_COSTUMER}">
            Name : ${requestScope.CAKE_DTO.name}
            <form action="editQuantity" onsubmit="getScrollPosition()">
                Quantity : <input type="text" name="txtQuantity" value="${requestScope.QUANTITY_TO_EDIT}" class="inputField" />
                <input type="hidden" name="txtCakeID" value="${requestScope.CAKE_DTO.cakeID}" />
                <button type="submit" name="action" value="Apply edit">
                    Apply
                </button>
            </form>
        </div>
    </body>
</html>
