import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface API extends Remote { 
    public void add(String key, String value) throws RemoteException;
    public void remove(String key) throws RemoteException;
    public String query(String key) throws RemoteException;
    public void shutdown() throws RemoteException, NotBoundException; 
    public String printData() throws RemoteException;
}
