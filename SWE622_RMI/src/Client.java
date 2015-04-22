import java.rmi.registry.*;

public class Client implements Runnable {
    private static final String HOST = "localhost";
    private static final int PORT = 2000;
    private static Registry registry;
    private String[] args;

    public Client(String [] args) {
        this.args = args;
    }

    public void run(){
        try {
            registry = LocateRegistry.getRegistry(HOST,PORT);
            API remote = (API) registry.lookup(API.class.getSimpleName());
            if (args.length >= 3) {
                if (args.length >= 4) {
                    remote.add(args[2], args[3]);
                } else {
                    switch (args[1].toLowerCase()) {
                    case "remove": remote.remove(args[2]); break;
                    case "query": System.out.println(remote.query(args[2])); break;
                    default: System.err.println("Invalid arguments"); System.exit(0);
                    }
                }
            } else if (args.length == 2) {
                if(args[1].equalsIgnoreCase("shutdown")) {
                    remote.shutdown();
                } else if (args[1].equalsIgnoreCase("printData")) {
                    System.out.println(remote.printData());
                } else {
                    System.err.println("Invalid arguments");
                    System.exit(0);
                } 
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
