<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="distribuidos.Client"%>
<%@page import="archivo.ObtenerParametroMultiPart"%>
<%@page import="archivo.RecieveFiles"%>
<%
    String url = "";
    ObtenerParametroMultiPart multiPart = new ObtenerParametroMultiPart(request, application.getRealPath("/").replace("\\", "/")+"Archivos/");
    String accion = multiPart.getParameter("accion"), claveSacreta = multiPart.getParameter("claveSecreta");
    if(accion != null && !accion.trim().equals("") && claveSacreta != null && !claveSacreta.trim().equals("")){
        if(accion.equals("Cifrar")){
            try{
                Client cliente = new Client("localhost");
                String nombre = multiPart.getParameter("Archivo");
                String ruta = multiPart.getParameter("RutaArchivo");
                cliente.crearArchivoCifrado(request, claveSacreta, application.getRealPath("/").replace("\\", "/")+"Archivos/", nombre, ruta);
                url = "<a href='./Archivos/"+nombre+".cifrado' download>Descargar Archivo Cifrado</a>";
            }
            catch(NullPointerException e){
                out.println(e.getMessage());
            }
        }
        else if(accion.equals("Descifrar")){
            try{
                Client cliente = new Client("localhost");
                String nombre = multiPart.getParameter("Archivo");
                String ruta = multiPart.getParameter("RutaArchivo");
                cliente.crearArchivoDescifrado(request, claveSacreta, application.getRealPath("/").replace("\\", "/")+"Archivos/", nombre, ruta);
                url = "<a href='./Archivos/"+nombre.replace(".cifrado", ".descifrado")+"' download>Descargar Archivo Cifrado</a>";
            }
            catch(NullPointerException e){
                out.println(e.getMessage());
            }
        }
    }
%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cliente</title>
    </head>
    <body>
        <form action="cliente.jsp" method="POST">
            <input type="submit" name="iniciar" value="Iniciar">
        </form>
        <form action="index.jsp" method="POST">
            <input type="submit" value="Regresar">
        </form>
        <br>
        <br>
        <form action='cliente.jsp' method='POST' enctype='multipart/form-data'>
            <input type="file" name='archivo' accept='.txt, .cifrado, .descifrado' id="cifrar" onchange="validarArchivo()">
            <br>
            <input type="text" name="claveSecreta" required>
            <br>
            <input type="submit" value="Cifrar" name="accion" required>
            <br>
            <input type="submit" value="Descifrar" name="accion" required>
        </form>
        <%=url%>
        <script>
            function validarArchivo()
            {
                var archivoInput = document.getElementById('cifrar');
                var archivo = archivoInput.files[0];

                if (archivo)                {
                    var nombre = archivo.name;
                    var extension = nombre.substring(nombre.lastIndexOf('.') + 1).toLowerCase();

                    var extensionesPermitidas = ['txt', 'cifrado', 'descifrado'];

                    if (extensionesPermitidas.includes(extension)){
                    } 
                    else{
                        alert('Archivo no válido. Solo se permiten archivos con extensiones: ' + extensionesPermitidas.join(', '));
                        archivoInput.value = '';
                    }
                }
            }
        </script>
    </body>
</html>
