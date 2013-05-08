<%-- 
    Document   : logout
    Created on : 8 mai 2013, 14:51:01
    Author     : momo
--%>

<%@page import="enterprise.entity.Player"%>
<%@page import="enterprise.session.GamingRoomSession"%>
<%@page import="enterprise.session.PlayerSession"%>
<%
    GamingRoomSession grs = (GamingRoomSession)session.getAttribute("Room");
    grs.leaveRoom((Player)session.getAttribute("Player"));
    session.invalidate();
    String redirectURL = "index.jsp";
    response.sendRedirect(redirectURL);
%>
