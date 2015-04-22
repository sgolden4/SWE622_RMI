package pa2peters;


import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class PA2Server implements Runnable {
	static final int PORTNUM = 3333;
	public static Registry registry;
	
	public void run(){
//		if (System.getSecurityManager() == null) {
//		    System.setSecurityManager(new SecurityManager());
//		}
	    try {
	        String name = "PA2Server";
		    PA2Operations server = new PA2Operator();

		    registry = LocateRegistry.createRegistry(PORTNUM);
//		    PA2Operate stub =
//			        (PA2Operate) UnicastRemoteObject.exportObject(server, PORTNUM);
		    registry.bind(name, server);
		    System.out.println("PA2Server bound");
		} catch (Exception e) {
		    System.err.println("PA2Server exception:");
		        e.printStackTrace();
		}
	}


	
}
