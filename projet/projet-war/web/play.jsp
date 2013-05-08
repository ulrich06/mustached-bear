<%-- 
    Document   : logout
    Created on : 8 mai 2013, 14:51:01
    Author     : momo
--%>
<%@page import="javax.ejb.EJBException"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="enterprise.game.PierreFeuilleCiseaux"%>
<%

    IGameSession ig = null;
    String play = request.getParameter("play");
    if (play != null || session.getAttribute("session.IGameSession") == null) {
        try {
            // First time we come on this page
            InitialContext ic = new InitialContext();
            ig = (IGameSession) ic.lookup(I_GAME_SESSION);
            ig.setPlayer(_player);
            ig.setGame(game);
            ig.newGame();
            session.setAttribute("session.IGameSession", ig);
        } catch (EJBException ex) {
        }
    } else {
        ig = (IGameSession) session.getAttribute("session.IGameSession");
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
        winner = ig.play(userChoice);
    }
%>
<h2>Pierre Feuille Ciseaux</h2>
<% if (!ig.isFinished()) {%>
<form method="POST" action="play.jsp" id="game">
    <input type="submit" name="pierre" class="boutonjeu" id="pierre" value="Pierre" />
    <input type="submit" name="feuille" class="boutonjeu" id="feuille" value="Feuille" />
    <input type="submit" name="ciseaux" class="boutonjeu" id="ciseaux" value="Ciseaux" />
    <input type="hidden" name="game" value="<%=game%>" /> 
</form>
<% } else {%>
<p>La partie est terminée !</p>
<% if (ig.userWinner()) {%>
<b>Félicitations, vous avez gagné !</b>
<% } else {%>
<b>Dommage, vous avez perdu</b>
<% }%>
<br />
<a href="room.jsp">Retour à la salle de jeu</a>
<%}%>
<fieldset class="score"><legend>Score</legend>
    <div style="width:350px; margin:auto;">
        <p class="score"><%=ig.getPlayerScore()%><span class="playername">Vous</span></p>
        <p class="score">-</p>
        <p class="score"><%=ig.getComputerScore()%><span class="playername">Ordinateur</span></p>
    </div>
</fieldset>
<br class="clear"> </br>
<fieldset><legend>Dernier coup joué</legend>
    <p><%
        if (winner == 0) {
        %>Egalité, vous avez tous les deux joué <%=ig.getLastUserChoice().toString()%><%
        } else if (winner == 1) {
        %>Victoire, vous avez gagné avec <%=ig.getLastUserChoice().toString()%> contre <%=ig.getLastComputerChoice().toString()%><%
        } else if (winner == 2) {
        %>Défaite, vous avez perdu avec <%=ig.getLastUserChoice().toString()%> contre <%=ig.getLastComputerChoice().toString()%><%
        }
        %>
    </p>
</fieldset>

<%@include file="inc/footer.jspf" %>