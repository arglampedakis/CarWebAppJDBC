<%-- 
    Document   : carDetails
    Created on : May 30, 2020, 9:48:25 PM
    Author     : glamb
--%>

<%@page import="model.Car"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Car Details</title>
    </head>
    <body>
        <h1>Car details</h1>
        <% 
        if (request.getAttribute("errorMessage") != null) {
                out.print(request.getAttribute("errorMessage"));
            }
        %>
        <% Car car = (Car) request.getAttribute("mycar");
            if (car != null) {%>
        <ul>
            <%= "<li> Plate: " + car.getPlate() + "</li>"%>
            <%="<li> Horsepower: " + car.getHorsepower() + "</li>"%>
        </ul>
        <% }%>

    </body>
</html>
