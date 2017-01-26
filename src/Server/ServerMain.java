package Server;

import java.net.*;
import java.util.ArrayList;

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
	        
	         
	      } 
	       catch(Exception x) { 
	    	  x.printStackTrace(); 
	      }
	}
}
