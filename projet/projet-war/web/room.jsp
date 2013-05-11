<%-- 
    Document   : room
    Created on : 19 mars 2013, 21:13:44
    Author     : momo
--%>
<%@page import="enterprise.entity.Player"%>
<%@page import="enterprise.session.GamingRoomSession"%>
<%@page import="java.util.List"%>
<%@page import="javax.naming.InitialContext"%>
<%@include file="inc/header.jspf" %>
        <h1> Salle d'attente </h1>
        <p> Nombre de joueur connect? : 
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
                </p>
                <%
            }
        %>
        <p></p>
        <form method="post" action="play.jsp">
            <input type="submit" name="submit" value="Jouer contre l'ordinateur">
        </form>
<%@include file="inc/footer.jspf" %>
