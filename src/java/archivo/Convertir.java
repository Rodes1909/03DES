package archivo;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Convertir{
    public static byte[] convertirArchivoBytes(String filePath){
        Path path = Path.of(filePath);
        try{
            return Files.readAllBytes(path);
        }
        catch(IOException e){
            return null;
        }
    }
    
    public static void convertirBytesArchivo(byte[] archivo, String filePath){
        try (FileOutputStream fos = new FileOutputStream(filePath)){
            fos.write(archivo);
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}
