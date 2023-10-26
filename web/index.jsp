<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="distribuidos.Server"%>
<%@page import="distribuidos.Client"%>
<%
    String accion = request.getParameter("accion");
    if(accion != null && !accion.trim().equals("")) {
        switch (accion) {
            case "Servidor":
                response.sendRedirect("servidor.jsp");
                break;
            case "Cliente":
                response.sendRedirect("cliente.jsp");
                break;
        }
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cifrar :)</title>
    </head>
    <body>
        <form action="index.jsp" method="POST">
            <input type="submit" name="accion" value="Servidor">
            <input type="submit" name="accion" value="Cliente">
        </form>
    </body>
</html>
