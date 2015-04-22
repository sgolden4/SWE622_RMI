import java.rmi.*;
import java.rmi.server.*;
import java.util.HashMap;
import java.util.Map;

public class Implementation extends UnicastRemoteObject
    implements API {

    private static final long serialVersionUID = 1L;
    private Map<String,String> database = new HashMap<String,String>();
    
    public Implementation() throws RemoteException {
        super();
    }
    
    @Override
    public void add(String key, String value) throws RemoteException {
        database.put(key, value);
    }

    @Override
    public void remove(String key) throws RemoteException {
        database.remove(key);
    }

    @Override
    public String query(String key) throws RemoteException {
        String value = database.get(key);
        return value;
    }

    @Override
    public void shutdown() throws Exception {
        Server.registry.unbind(API.class.getSimpleName());
        Implementation.unexportObject(this, true);
    }

    @Override
    public String printData() throws RemoteException {
        return database.toString();
    }
    

}
