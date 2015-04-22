package pa2peters;

public class pa2main {
	
	public static void main(String[] args){
		if(args.length !=0)
			if("server".equals(args[0])){
				PA2Server server = new PA2Server();
				Thread thread = new Thread(server);
				thread.start();
			}
			else if("client".equals(args[0])){
				PA2Client.process(args);	   
			}
	}
}
