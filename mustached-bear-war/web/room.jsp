<%-- 
    Document   : room
    Created on : 19 mars 2013, 21:13:44
    Author     : momo
--%>

<%@page import="enterprise.persistence.Player"%>
<%@page import="java.util.List"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="enterprise.sessions.GameRoomSessionLocal"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1> Salle d'attente </h1>
        <%
            InitialContext ic = new InitialContext();
            GameRoomSessionLocal room = (GameRoomSessionLocal) ic.lookup("java:global/mustached-bear/mustached-bear-ejb/GameRoomSession!enterprise.sessions.GameRoomSessionLocal");

            List players = room.listPlayers();
            
            for(int i=0; i<players.size();++i)
            {
                Player p = (Player)players.get(i);
                %>
                <p>
                    <%=p.getLogin()%>
                </p>
                <%
            }
        %>
    </body>
</html>
