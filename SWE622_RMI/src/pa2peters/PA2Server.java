package pa2peters;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class PA2Server implements PA2Operate{
	static final int PORTNUM = 3210;
	Map<String, String> dictionary;
	
	public PA2Server(){
		super();
		dictionary = new HashMap<String, String>();
	}
	
	public static void start(){
		if (System.getSecurityManager() == null) {
		    System.setSecurityManager(new SecurityManager());
		}
	    try {
	        String name = "PA2Server";
		    PA2Operate server = new PA2Server();
		    PA2Operate stub =
		        (PA2Operate) UnicastRemoteObject.exportObject(server, PORTNUM);
		    Registry registry;
		    try{
		    	registry = LocateRegistry.getRegistry(PORTNUM);
		    } catch (RemoteException e){
		    	registry = LocateRegistry.createRegistry(PORTNUM);
		    }
		    registry.rebind(name, stub);
		    System.out.println("PA2Server bound");
		} catch (Exception e) {
		    System.err.println("PA2Server exception:");
		        e.printStackTrace();
		}
	}


	@Override
	public void addItem(String key, String value) throws RemoteException {
		dictionary.put(key, value);
	}

	@Override
	public void removeItem(String key) throws RemoteException {
		dictionary.remove(key);
	}

	@Override
	public String queryItem(String key) throws RemoteException {
		return dictionary.get(key);
	}
}
