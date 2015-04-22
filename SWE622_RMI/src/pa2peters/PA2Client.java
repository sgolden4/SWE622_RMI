package pa2peters;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class PA2Client {
	static final int PORTNUM = 3333;
	static final String REGISTRYSERVER = "localhost";
	
	public static void process(String[] args){
		if(args.length < 2){
			printHelp();
			return;
		}
		if("shutdown".equals(args[1])){
			shutdownServer();
			System.exit(0);
		}
		if(args.length < 3){
			printHelp();
			return;
		}
		switch(args[1]){
			case "add":
				if(args.length < 4){
					printHelp();
					return;
				} else {
					addItem(args[2], args[3]);
				}
				break;
			case "query":
				queryItem(args[2]);
				break;
			case "remove":
				removeItem(args[2]);
				break;

			default:
				printHelp();
		}
	}
	
	private static PA2Operations getServer(){
//        if (System.getSecurityManager() == null) {
//            System.setSecurityManager(new SecurityManager());
//        }
        try {
            String name = "PA2Server";
            Registry registry = LocateRegistry.getRegistry(REGISTRYSERVER, PORTNUM);
            PA2Operations server = (PA2Operations) registry.lookup(name);  
            return server;
        } catch (Exception e) {
            System.err.println("PA2Client error.  Make sure server is running before running the client.");
            System.err.println(e.getMessage());
            System.exit(1);
        }
        return null;
	}

	private static void removeItem(String key) {
		PA2Operations server = getServer();
		try {
			server.removeItem(key);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	private static void queryItem(String key) {
		PA2Operations server = getServer();
		try {
			String out = server.queryItem(key);
			if(out != null)
				System.out.println(out);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	private static void addItem(String key, String value) {
		PA2Operations server = getServer();
		try {
			server.addItem(key, value);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	private static void shutdownServer() {
		PA2Operations server = getServer();
		try {
			server.shutdown();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	private static void printHelp() {
		System.out.println("To add an item: java -jar pa2.jar client add <key> <value>");
		System.out.println("To remove an item: java -jar pa2.jar client remove <key>");
		System.out.println("To query an item: java -jar pa2.jar client query <key>");
		System.out.println("To shutdown the server: java -jar pa2.jar client shutdown");
		
	}
}
