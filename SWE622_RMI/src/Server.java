import java.rmi.*;
import java.rmi.registry.*;

public class Server implements Runnable {
    private static final int PORT = 2000;
    public static Registry registry;
    public static boolean shutdown = false;
    
    public static void startRegistry() throws RemoteException {
        registry = java.rmi.registry.
                LocateRegistry.createRegistry(PORT);
    }
    
    public static void registerObject(String name, Remote remoteObj)
        throws RemoteException, AlreadyBoundException {
        registry.bind(name, remoteObj);
        System.out.println("Registered: " + name + " -> " +
                remoteObj.getClass().getName() + "[" + remoteObj + "]");
    }
    
    public void run() {
        try {
            startRegistry();
            registerObject(API.class.getSimpleName(), new Implementation());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}