package distribuidos;

import archivo.Convertir;
import cifrado.Cifrado;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server implements IMetodos{
    
    public void iniciar(){
        try{        
            IMetodos stub = (IMetodos) UnicastRemoteObject.exportObject(new Server(), 0);
            
            Registry registro = LocateRegistry.createRegistry(1099);
            registro.rebind("Objeto", stub);
            System.out.println("Servidor Iniciado");
        }
        catch(RemoteException e){
            System.out.println(e.getMessage());
        }
    }
    
    public void cerrarServidor() {
        try {
            System.exit(0);
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public byte[] convertirArchivoBytes(String path){
        return Convertir.convertirArchivoBytes(path);
    }
    
    @Override
    public void convertirBytesArchivo(byte[] archivo, String filePath){
        Convertir.convertirBytesArchivo(archivo, filePath);
    }
    
    @Override
    public String cifrar(String claveSecreta, String ruta){
        String direccion = "";
        try{
            direccion = Cifrado.cifrar(claveSecreta, ruta);
        }
        catch(Exception e){
            System.out.println(e.getMessage());            
        }
        
        return direccion;
    }
    
    @Override
    public String descifrar(String claveSecreta, String ruta){
        String direccion = "";
        try{
            direccion = Cifrado.descifrar(claveSecreta, ruta);
        }
        catch(Exception e){
            System.out.println(e.getMessage());            
        }
        
        return direccion;
    }
}
