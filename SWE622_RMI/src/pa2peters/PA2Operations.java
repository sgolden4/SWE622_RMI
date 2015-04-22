package pa2peters;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PA2Operations extends Remote {
	public void addItem(String key, String value) throws RemoteException;
	public void removeItem(String key) throws RemoteException;
	public String queryItem(String key) throws RemoteException;
	public void shutdown() throws RemoteException;
}
