<%-- 
    Document   : game
    Created on : 8 mai 2013, 15:10:22
    Author     : momo
--%>

<%@page import="enterprise.entity.Player"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Defi</title>
    </head>
    <body>
    <center>
        <%
            Player p = (Player)session.getAttribute("Player");
        %>
        <h1> <%=p.getLogin()%> VS <%=p.getAdv()%> </h1>
    </center>
    </body>
</html>
