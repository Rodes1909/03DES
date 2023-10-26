<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="distribuidos.Server"%>
<%
    String iniciar = request.getParameter("iniciar");
    if(iniciar != null && !iniciar.trim().equals("")){
        Server servidor = new Server();
        servidor.iniciar();
    } 
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Servidor</title>
    </head>
    <body>
        <form action="servidor.jsp" method="POST">
            <input type="submit" name="iniciar" value="Iniciar">
        </form>
        <form action="index.jsp" method="POST">
            <input type="submit" name="accion" value="Regresar">
        </form>
    </body>
</html>
