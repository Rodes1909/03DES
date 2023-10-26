package distribuidos;

import java.io.File;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.servlet.http.HttpServletRequest;

public class Client {
    Registry registro;
    IMetodos stub;
    
    public Client(String puerto){
        try{
            registro = LocateRegistry.getRegistry(puerto, 1099);
            stub = (IMetodos) registro.lookup("Objeto");
        }
        catch(RemoteException | NotBoundException e){
            System.out.println(e.getMessage());
        }
    }
    
    public void crearArchivoCifrado(HttpServletRequest request, String clave, String direccion, String nombre, String ruta){
        try{
            String rutaCifrado = "C:/Users/DELL/Documents/CECyT 9/Quinto Semestre/SW"+nombre;
            String destino= direccion+nombre+".cifrado";
            
            stub.convertirBytesArchivo(stub.convertirArchivoBytes(ruta), rutaCifrado);
            String cifrado = stub.cifrar(clave, rutaCifrado);
            stub.convertirBytesArchivo(stub.convertirArchivoBytes(cifrado), destino);
        }
        catch(RemoteException e){
            System.out.println(e.getMessage());
        }
    }
    
   public void crearArchivoDescifrado(HttpServletRequest request, String clave, String direccion, String nombre, String ruta){
        try{
            String rutaCifrado = "C:/Users/DELL/Documents/CECyT 9/Quinto Semestre/SW"+nombre;
            String destino = direccion+nombre.replace(".cifrado", ".descifrado");
            
            stub.convertirBytesArchivo(stub.convertirArchivoBytes(ruta), rutaCifrado);
            String descifrado = stub.descifrar(clave, rutaCifrado);
            stub.convertirBytesArchivo(stub.convertirArchivoBytes(descifrado), destino);
        }
        catch(RemoteException e){
            System.out.println("mensaje: "+e.getMessage());
        }
    } 
}
