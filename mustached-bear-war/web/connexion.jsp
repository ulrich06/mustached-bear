<%-- 
    Document   : connexion
    Created on : 19 mars 2013, 20:51:57
    Author     : momo
--%>

<%@page import="enterprise.persistence.Player"%>
<%@page import="enterprise.sessions.*"%>
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
        <form method="post" action="/mustached-bear-war/connexion.jsp">
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
        PlayerSessionLocal playSess = (PlayerSessionLocal) ic.lookup("java:global/mustached-bear/mustached-bear-ejb/PlayerSession!enterprise.sessions.PlayerSessionLocal");
        GameRoomSessionLocal room = (GameRoomSessionLocal) ic.lookup("java:global/mustached-bear/mustached-bear-ejb/GameRoomSession");
        
        if(playSess.login(login, password))
        {
            room.enterRoom(playSess.searchPlayer(login));
%>
           <jsp:forward page="room.jsp" />            
<%
        }
        else
        {   
%>
Erreur lors du login, nom ou mot de passe incorrect...
<%
        }
    } catch(Exception e) {
        e.printStackTrace();
        out.println("Create Customer Failed : " + e.toString()); 
    } 
}
%>

<hr>
[<a href="/mustached-bear-war/index.jsp"> HOME </a>]
</center>
</body>
</html>
