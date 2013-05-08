<%-- 
    Document   : room
    Created on : 19 mars 2013, 21:13:44
    Author     : momo
--%>
<%@page import="enterprise.entity.Player"%>
<%@page import="enterprise.session.GamingRoomSession"%>
<%@page import="java.util.List"%>
<%@page import="javax.naming.InitialContext"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <center>
        <h1> Salle d'attente </h1>
        <p> Nombre de joueur connecté : 
        <%
            // Set refresh, autoload time as 5 seconds
            response.setIntHeader("Refresh", 5);
            InitialContext ic = new InitialContext();
            GamingRoomSession room = (GamingRoomSession) ic.lookup("java:global/projet/projet-ejb/GamingRoomSession");

            List players = room.listPlayers();
        %>
        <%=room.getNbJoueur()%></p>
        <%
            for(int i=0; i<players.size();++i)
            {
                Player p = (Player)players.get(i);
                %>
                <p>
                    <%=p.getLogin()%>
                    <button onclick="window.open('defi.jsp')">Défier</button>
                </p>
                <%
            }
        %>
        <hr>
        [<a href="/projet-war/logout.jsp"> Deconnexion </a>]
    </center>
    </body>
</html>
