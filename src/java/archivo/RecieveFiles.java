package archivo;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;

public class RecieveFiles extends HttpServlet 
{
    public static String recieveFiles(HttpServletRequest request, String direccionArchivo) 
    {
        int i = 0;
        ServletRequestContext src = new ServletRequestContext(request);
        String retorno = "";

        if (ServletFileUpload.isMultipartContent(src)) 
        {
            DiskFileItemFactory factory = new DiskFileItemFactory((1024 * 1024), new File(direccionArchivo));
            ServletFileUpload upload = new ServletFileUpload(factory);
            List lista = null;
            
            try 
            {
                lista = upload.parseRequest(src);
            } 
            catch (FileUploadException e) 
            {
                System.out.println("Error al subir el archivo: "+e.getMessage());
            }
            
            File file = null;
            Iterator it = lista.iterator();
            boolean valor = it.hasNext();
            System.out.println(valor);
            while (it.hasNext()) 
            {
                FileItem item = (FileItem) it.next();

                if (item.isFormField())
                {
                    System.out.println(item.getFieldName());
                } 
                else 
                {
                    try 
                    {
                        file = new File(direccionArchivo, item.getName());
                        item.write(new File(direccionArchivo+item.getName()));
                        retorno = direccionArchivo + file.getName();
                    } 
                    catch (Exception e) 
                    {
                        System.out.println("Error al crear el archivo"+e.getMessage());
                    }
                    
                    i++;
                }
            }
        }
        
        return retorno;
    }
    
    public static String recieveFiles(HttpServletRequest request, String direccionArchivo, String... terminaciones) 
    {
        int i = 0;
        ServletRequestContext src = new ServletRequestContext(request);
        String retorno = "";

        if (ServletFileUpload.isMultipartContent(src)) 
        {
            DiskFileItemFactory factory = new DiskFileItemFactory((1024 * 1024), new File(direccionArchivo));
            ServletFileUpload upload = new ServletFileUpload(factory);
            List lista = null;
            
            try 
            {
                lista = upload.parseRequest(src);
            } 
            catch (FileUploadException e) 
            {
                System.out.println("Error al subir el archivo: "+e.getMessage());
            }
            
            File file = null;
            Iterator it = lista.iterator();

            while (it.hasNext()) 
            {
                FileItem item = (FileItem) it.next();

                if (item.isFormField())
                {
                    System.out.println(item.getFieldName());
                } 
                else 
                {
                    for(String posicion : terminaciones)
                    {
                        if(item.getName().endsWith(posicion))
                        {
                            try 
                            {
                                file = new File(direccionArchivo, item.getName());
                                item.write(new File(direccionArchivo+item.getName())); 
                                retorno = direccionArchivo + file.getName();
                            } 
                            catch (Exception e) 
                            {
                                System.out.println("Error al crear el archivo"+e.getMessage());
                            }
                        }
                    }
                    i++;
                }
            }
        }
        
        return retorno;
    }
}
