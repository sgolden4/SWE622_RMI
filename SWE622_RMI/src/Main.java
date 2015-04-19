public class Main {

    public static void main(String args[]) throws Exception {
        if (args.length == 0) {
            System.err.println("Invalid arguments.");
            System.exit(0);
        } else if (args[0].equalsIgnoreCase("client")) {
            Client c = new Client(args);
            Thread t = new Thread(c);
            t.start();
        } else if (args[0].equalsIgnoreCase("server") && args.length == 1) {
            Server s = new Server();
            Thread t = new Thread(s);
            t.start();
        } else {
            System.err.println("Invalid arguments.");
            System.exit(0); 
        }
    }
}