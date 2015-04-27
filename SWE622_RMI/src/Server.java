import java.rmi.*;
import java.rmi.registry.*;

public class Server implements Runnable {
    private static final int PORT = 3333;
    public static Registry registry;
    
    public static void startRegistry() throws RemoteException {
        registry = java.rmi.registry.
                LocateRegistry.createRegistry(PORT);
    }
    
    public static void registerObject(String name, Remote remoteObj)
        throws RemoteException, AlreadyBoundException {
        registry.bind(name, remoteObj);
        System.out.println("Registered: " + name);
    }
    
    public void run() {
        try {
            startRegistry();
            registerObject(API.class.getSimpleName(), new Implementation());
            System.out.println("Server bound");
        } catch (RemoteException | AlreadyBoundException e) {
            e.printStackTrace();
        }
    }
}