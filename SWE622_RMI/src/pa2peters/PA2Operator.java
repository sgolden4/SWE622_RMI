package pa2peters;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class PA2Operator extends UnicastRemoteObject implements PA2Operations {

	Map<String, String> dictionary = new HashMap<String, String>();

	public PA2Operator() throws RemoteException {
		super();
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

	@Override
	public void shutdown() throws RemoteException {
		try {
			PA2Server.registry.unbind("PA2Server");
			PA2Operator.unexportObject(this, true);
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}

}
