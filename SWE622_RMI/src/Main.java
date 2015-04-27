public class Main {

    public static void main(String args[]) throws Exception {
        if (args.length == 0) {
            System.err.println("No arguments given.");
            return;
        } else if (args[0].equalsIgnoreCase("client")) {
            Client c = new Client(args);
            Thread t = new Thread(c);
            t.start();
        } else if (args[0].equalsIgnoreCase("server")) {
        	if (args.length > 1) {
        		System.err.println("Too many arguments for \"server\"");
        		return;
        	}
            Server s = new Server();
            Thread t = new Thread(s);
            t.start();
        } else {
            System.err.println("First argument must be either \"client\" or \"server\".");
            return;
        }
    }
}