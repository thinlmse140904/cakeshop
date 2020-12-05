<%-- 
    Document   : orderTracking
    Created on : Oct 15, 2020, 7:21:04 AM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="css/all.min.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/all.min.css" rel="stylesheet" type="text/css"/>
        <title>order tracking </title>
    </head>
    <body>
        <form action="SearchOrder">
            <input type="text" name="txtOrderID" value="" placeholder="Your order ID : "/>
            <button type="submit" name="action" value="Search Order"> 
                Search
            </button>
        </form>
        <c:if test="${not empty requestScope.ORDER_LIST}">
            <table class="table table-dark table-striped">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Order ID</th>
                        <th>Date of create</th>
                        <th>total</th>
                        <th>Detail</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="order" items="${requestScope.ORDER_LIST}" varStatus="counter">
                        <tr>
                            <td class="firstChild">${counter.count}</td>
                            <td class="midChild">${order.orderID}</td>
                            <td class="midChild">${order.dateOfCreate}</td>
                            <td class="midChild">${order.total}</td>
                            <td class="lastChild">
                                <c:url var="DetailLinks" value="SeeDetail">
                                    <c:param name="txtOrderID" value="${order.orderID}"/>
                                    <c:param name="txtTotal" value="${order.total}"/>
                                </c:url>
                                <a href="${DetailLinks}">
                                    See more
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </body>
</html>
