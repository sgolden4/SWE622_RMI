import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.*;

public class Client implements Runnable {
	private static final String HOST = "localhost";
	private static final int PORT = 3333;
	private String[] args;

	public Client(String [] args) {
		this.args = args;
	}

	public void run(){
		switch (args.length) {
			case 2: 
				if(args[1].equalsIgnoreCase("shutdown")) {
					shutdown();
				} else {
					printHelp();
				} 
				break;
			case 3: 
				switch (args[1].toLowerCase()) {
					case "remove": remove(args[2]); break;
					case "query": System.out.println(query(args[2])); break;
					default: printHelp();
				}
				break;
			case 4: 
				if(args[1].equalsIgnoreCase("add")) {
					add(args[2], args[3]);
				} else {
					printHelp();
				} 
				break;
			default: printHelp();
		}

	}

	private static void add(String key, String value) {
		API Server = getServer();
		try {
			Server.add(key, value);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	private static String query(String key) {
		API Server = getServer();
		try {
			String value = Server.query(key);
	        if (value == null) {
	        	System.exit(1);  //Instructions indicate to print nothing at all and return an error code
	        }
			return value;
		} catch (RemoteException e) {
			return "Failed Query.\n" + e.getMessage();
		}
	}

	private static void shutdown() {
		API Server = getServer();
		try {
			Server.shutdown();
		} catch (RemoteException | NotBoundException e) {
			System.out.println("Failed Shutdown.\n" + e.getMessage());
		}
	}

	private static void remove(String key) {
		API Server = getServer();
		try {
			Server.remove(key);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	private static API getServer() {
		try {
			Registry registry = LocateRegistry.getRegistry(HOST,PORT);
			return (API) registry.lookup(API.class.getSimpleName());	
		} catch (RemoteException | NotBoundException e) {
			System.out.println("Failed to locate server.");
			System.exit(1);
		}
		return null;
	}
	
	private static void printHelp() {
		System.out.println("Invalid command");
		System.out.println("To add an item: java -jar pa2.jar client add <key> <value>");
		System.out.println("To remove an item: java -jar pa2.jar client remove <key>");
		System.out.println("To query an item: java -jar pa2.jar client query <key>");
		System.out.println("To shutdown the server: java -jar pa2.jar client shutdown");
		
	}
}
