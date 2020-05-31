<%-- 
    Document   : newCarDetails
    Created on : May 30, 2020, 11:05:04 PM
    Author     : glamb
--%>

<%@page import="model.Car"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>New Car</title>
    </head>
    <body>
        <%
            if (request.getAttribute("failMessage") != null) {
                out.print(request.getAttribute("failMessage"));
            } else {
                Car car = (Car) request.getAttribute("myCar");
                if (car != null) {%>
        Successfully registered!
        <h1>New Car details:</h1>
        <ul>
            <%= "<li> ID: " + car.getId() + "</li>"%>
            <%= "<li> Plate: " + car.getPlate() + "</li>"%>
            <%="<li> Horsepower: " + car.getHorsepower() + "</li>"%>
        </ul>
        <% }
            }%>
        <br>
        <a href="newCarForm.html">Go back</a>
    </body>
</html>
