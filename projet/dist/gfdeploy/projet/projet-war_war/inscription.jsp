<%--
 Copyright 2004-2005 Sun Microsystems, Inc.  All rights reserved.
 Use is subject to license terms.
--%>

<%@page import="enterprise.entity.Player"%>
<%@page import="enterprise.session.PlayerSession"%>
<%@page import="javax.naming.InitialContext"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head><title> Inscription </title></head>

<body bgcolor="white">
<center>
<h2>Inscription</h2>
<p>
<form method="post" action="inscription.jsp">
Login : <input type="text" name="login" size="20" value=""><br/>
Password : <input type="password" name="pwd" size="20" value=""><br/>
E-mail : <input type="text" name="mail" size="20" value=""><br/>
<p>
<input type="submit" name="submit" value="Submit">
</p>
</form>

<%
String login = request.getParameter("login");
String password = request.getParameter("pwd");
String mail = request.getParameter("mail");

if (login != null && !"".equals(login)) 
{
    try 
    {
        InitialContext ic = new InitialContext();
        PlayerSession playSess = (PlayerSession) ic.lookup("java:global/projet/projet-ejb/PlayerSession");
        Player player = new Player(login, password, mail);
        playSess.persist(player);
%>
Nouveau joueur : 
<%=player.getLogin()%>, 
<%=player.getEmail()%>.

<%
    } catch(Exception e) {
        e.printStackTrace();
        out.println("Create Customer Failed : " + e.toString()); 
    } 
}
%>

<hr>
[<a href="/projet-war/index.jsp"> HOME </a>]
</center>
</body>
</html>
