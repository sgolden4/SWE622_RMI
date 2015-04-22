import java.rmi.*;

public interface API extends Remote {
    
    public void add(String key, String value) 
            throws RemoteException;
    
    public void remove(String key)
            throws RemoteException;
    
    public String query(String key)
            throws RemoteException;
    
    public void shutdown()
            throws Exception;
    
    public String printData()
            throws RemoteException;
}
