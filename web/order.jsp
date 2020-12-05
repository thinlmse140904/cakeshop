<%-- 
    Document   : order
    Created on : Oct 9, 2020, 4:37:07 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <link href="css/all.min.css" rel="stylesheet" type="text/css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order</title>
        <style>
            td{
                border: none;
            }
            .midChild{
                width: 20vw;
            }
            body{
                background-image: url('image/background/Fullscreen-OnlineOrdering-Bkgd-v2.png');
                background-size: 100%;
                background-repeat: no-repeat;
            }
        </style>
    </head>
    <body>
        <h1>Your Order</h1>
        Name : ${requestScope.CUSTOMER.name}<br>
        Address : ${requestScope.CUSTOMER.address}<br>
        Phone No : ${requestScope.CUSTOMER.phoneNo}<br>
        Order ID: ${requestScope.ORDER.orderID}<br>
        Payment method : ${requestScope.CUSTOMER.paymentMethod}<br>
        Date : ${requestScope.ORDER.dateOfCreate}<br>
        <table class="table table-dark table-striped">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Cake name</th>
                    <th>Quantity</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="cake" items="${requestScope.LIST_CAKE_ORDER}" varStatus="counter">
                    <tr>
                        <td class="firstChild">${counter.count}</td>
                        <td class="midChild">${cake.cakeName}</td>
                        <td class="lastChild">${cake.quantity}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        total : ${requestScope.TOTAL}
        <div>
            <form action="CheckOutAll" method="POST">
                <input type="hidden" name="txtOrderID" value="${requestScope.ORDER.orderID}" />
                <input type="hidden" name="txtName" value="${requestScope.CUSTOMER.name}" />
                <input type="hidden" name="txtPhoneNo" value="${requestScope.CUSTOMER.phoneNo}" />
                <input type="hidden" name="txtPayment" value="${requestScope.CUSTOMER.paymentMethod}" />
                <input type="hidden" name="txtAddress" value="${requestScope.CUSTOMER.address}" />
                <button type="submit" name="action" value="Confirm">
                    Confirm
                </button>
            </form>
        </div>
    </body>
</html>
