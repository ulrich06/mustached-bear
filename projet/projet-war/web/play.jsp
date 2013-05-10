<%-- 
    Document   : logout
    Created on : 8 mai 2013, 14:51:01
    Author     : momo
--%>
<%@page import="enterprise.entity.Player"%>
<%@page import="javax.ejb.EJBException"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="enterprise.game.PierreFeuilleCiseaux"%>
<%@page import="enterprise.session.GameSession"%>
<%@include file="inc/header.jspf" %>
<%

    GameSession gs = null;
    String game = "PierreFeuilleCiseaux";%>
    <%
    if (session.getAttribute("GameSession") == null) {
        try {
            // First time we come on this page
            InitialContext ic = new InitialContext();
            gs = (GameSession) ic.lookup("java:global/projet/projet-ejb/GameSession");
            gs.setPlayer((Player)session.getAttribute("Player"));
            gs.newGame();
            session.setAttribute("GameSession", gs);
        } catch (EJBException ex) {
        }
    } else {
        gs = (GameSession) session.getAttribute("GameSession");
    }

    PierreFeuilleCiseaux.Choice userChoice = null;
    if (request.getParameter("pierre") != null) {
        userChoice = PierreFeuilleCiseaux.Choice.PIERRE;
    } else if (request.getParameter("feuille") != null) {
        userChoice = PierreFeuilleCiseaux.Choice.FEUILLE;
    } else if (request.getParameter("ciseaux") != null) {
        userChoice = PierreFeuilleCiseaux.Choice.CISEAUX;
    }
    int winner = -1;
    if (userChoice != null) {
        winner = gs.play(userChoice);
    }
%>
<h2>Pierre Feuille Ciseaux</h2>
<% if (!gs.isFinished()) {%>
<form method="POST" action="play.jsp" id="game">
    <input type="submit" name="pierre" class="boutonjeu" id="pierre" value="Pierre" />
    <input type="submit" name="feuille" class="boutonjeu" id="feuille" value="Feuille" />
    <input type="submit" name="ciseaux" class="boutonjeu" id="ciseaux" value="Ciseaux" />
    <input type="hidden" name="game" value="<%=game%>" /> 
</form>
<% } else {%>
<p>La partie est terminée !</p>
<% if (gs.userWinner()) {%>
<b>Félicitations, vous avez gagné !</b>
<% } else {%>
<b>Dommage, vous avez perdu</b>
<% }%>
<br />
<a href="room.jsp">Aller à la salle de jeu</a>
<p></p>
<a href="play.jsp">Rejouer contre l'ordinateur</a>
<%}%>
<fieldset class="score"><legend>Score</legend>
    <div style="width:350px; margin:auto;">
        <p class="score"><%=gs.getPlayerScore()%><span class="playername">Vous</span></p>
        <p class="score">-</p>
        <p class="score"><%=gs.getComputerScore()%><span class="playername">Ordinateur</span></p>
    </div>
</fieldset>
<br class="clear"> </br>
<fieldset><legend>Dernier coup joué</legend>
    <p><%
        if (winner == 0) {
        %>Egalité, vous avez tous les deux joué <%=gs.getLastUserChoice().toString()%><%
        } else if (winner == 1) {
        %>Victoire, vous avez gagné avec <%=gs.getLastUserChoice().toString()%> contre <%=gs.getLastComputerChoice().toString()%><%
        } else if (winner == 2) {
        %>Défaite, vous avez perdu avec <%=gs.getLastUserChoice().toString()%> contre <%=gs.getLastComputerChoice().toString()%><%
        }
        %>
    </p>
</fieldset>
<%@include file="inc/footer.jspf" %>