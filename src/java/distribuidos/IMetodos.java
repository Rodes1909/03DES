package distribuidos;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IMetodos extends Remote{
    byte[] convertirArchivoBytes(String Path) throws RemoteException;
    void convertirBytesArchivo(byte[] archivo, String filePath) throws RemoteException;
    String cifrar(String claveSecreta, String ruta) throws RemoteException;
    String descifrar(String claveSecreta, String ruta) throws RemoteException;
}
