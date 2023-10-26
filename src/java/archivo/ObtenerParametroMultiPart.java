package archivo;

import java.io.File;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.FileUploadException;
import java.util.Map;
import java.util.HashMap;

public class ObtenerParametroMultiPart {
    private Map<String, String> parametros = null;
    
    public ObtenerParametroMultiPart(HttpServletRequest request, String lugarDestino) {
        try {
            boolean isMultipart = ServletFileUpload.isMultipartContent(request);

            if (isMultipart) {
                FileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);

                List<FileItem> items = upload.parseRequest(request);
                parametros = new HashMap<>();

                for (FileItem item : items) {
                    String fieldName = null;
                    String value = null;
                    boolean valor = item.isFormField();
                    System.out.println(valor);
                    if (item.isFormField()) {
                        fieldName = item.getFieldName();
                        value = item.getString();
                        parametros.put(fieldName, value);
                    }
                    else{
                        try 
                        {
                            File file = null;
                            file = new File(lugarDestino, item.getName());
                            item.write(new File(lugarDestino+item.getName()));
                            parametros.put("RutaArchivo", lugarDestino+file.getName());
                            parametros.put("Archivo", file.getName());
                        } 
                        catch (Exception e) 
                        {
                            System.out.println("Error al crear el archivo"+e.getMessage());
                        }
                    }
                }
            }
        } 
        catch (FileUploadException e) {
            System.out.println(e.getMessage());
        }
    }
    
    private void getParameters(HttpServletRequest request) {
        try {
            boolean isMultipart = ServletFileUpload.isMultipartContent(request);

            if (isMultipart) {
                FileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);

                List<FileItem> items = upload.parseRequest(request);
                parametros = new HashMap<>();

                for (FileItem item : items) {
                    String fieldName = null;
                    String value = null;                    
                    if (item.isFormField()) {
                        fieldName = item.getFieldName();
                        value = item.getString();
                        if(!(value.trim().equals(""))){
                            parametros.put(fieldName, value);
                        }
                        else{
                            parametros.put(fieldName, null);
                        }
                    }
                    else{
                        try 
                        {
                            File file = null;
                            file = new File("C:/Users/DELL/Desktop/Nueva carpeta/nueva", item.getName());
                            item.write(new File("C:/Users/DELL/Desktop/Nueva carpeta/nueva"+item.getName()));
                            parametros.put("RutaArchivo", "C:/Users/DELL/Desktop/Nueva carpeta/nueva"+file.getName());
                            parametros.put("Archivo", file.getName());
                        } 
                        catch (Exception e) 
                        {
                            System.out.println("Error al crear el archivo"+e.getMessage());
                        }
                    }
                }
            }
        } 
        catch (FileUploadException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public String getParameter(HttpServletRequest request, String nombre){
        try{
            if(parametros == null){
                getParameters(request);
            }
            return parametros.get(nombre);
        }
        catch(NullPointerException e){
            return null;
        }
    }
    
    public String getParameter(String nombre){
        try{
            return parametros.get(nombre);
        }
        catch(NullPointerException e){
            return null;
        }
    }
    
}
