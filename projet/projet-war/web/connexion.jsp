<%-- 
    Document   : connexion
    Created on : 19 mars 2013, 20:51:57
    Author     : momo
--%>

<%@page import="enterprise.session.GamingRoomSession"%>
<%@page import="enterprise.session.PlayerSession"%>
<%@page import="javax.naming.InitialContext"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Connexion</title>
    </head>
    <body>
        <center>
        <h2>Connexion</h2>
        <form method="post" action="connexion.jsp">
        Login : <input type="text" name="login" size="20" value=""><br/>
        Password : <input type="password" name="pwd" size="20" value=""><br/>
        <p>
        <input type="submit" name="submit" value="Submit">
        </p>
        </form>
        <%
        
String login = request.getParameter("login");
String password = request.getParameter("pwd");

if (login != null && !"".equals(login)) 
{
    try 
    {
        InitialContext ic = new InitialContext();
        PlayerSession playSess = (PlayerSession) ic.lookup("java:global/projet/projet-ejb/PlayerSession");
        GamingRoomSession room = (GamingRoomSession) ic.lookup("java:global/projet/projet-ejb/GamingRoomSession");
        
        if(playSess.login(login, password))
        {
            room.enterRoom(playSess.searchPlayer(login));
            room.getNbJoueur();
            String redirectURL = "room.jsp";
            response.sendRedirect(redirectURL);   
        }
        else
        {   
%>
            Erreur lors du login, nom ou mot de passe incorrect...
<%
        }
    } catch(Exception e) {
        e.printStackTrace();
        out.println("Echec lors de la connexion : " + e.toString()); 
    } 
}
%>

<hr>
[<a href="/projet-war/index.jsp"> HOME </a>]
</center>
</body>
</html>
