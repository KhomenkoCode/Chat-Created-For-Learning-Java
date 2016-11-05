package Server;

import java.net.*;
import java.util.ArrayList;
//import java.io.*;

public class ServerMain{
	private ServerSocket ss;
	public final int port = 1234; 
	private ArrayList<Connection> clients = new ArrayList<Connection>();
	//private boolean stop = false;
	
	
	public void startServerPart(){
	       try {
	         ss = new ServerSocket(port);
	         ServerAddConnectionsThread ConnectionsThread = new ServerAddConnectionsThread(clients, ss);
	         ConnectionsThread.start();
	        
	         /*System.out.println("Waiting for a client...");
	        
	         Socket socket = ss.accept(); //waiting user connection
	         
	         DataInputStream in = new DataInputStream(socket.getInputStream());
	         DataOutputStream out = new DataOutputStream(socket.getOutputStream());
	         

	         String line = null;
	         while(true) {
	           
	           line = in.readUTF();				//After 1 user is gone, a server close = bad idea
	           if(line.equals("exit")) break;	//very bad
	           									//TODO this shit works normally(
	           
	           out.writeUTF(line);
	           out.flush(); 
	         }
	         
	         in.close();
	         out.close();*/
	      } 
	       catch(Exception x) { 
	    	  x.printStackTrace(); 
	      }
	}
}
