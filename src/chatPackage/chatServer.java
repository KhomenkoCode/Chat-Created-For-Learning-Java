package chatPackage;

import java.net.*;
import java.io.*;

public class chatServer extends Thread{
	private ServerSocket ss;
	public void run(){
		
		int port = 1234; 
	       try {
	         ss = new ServerSocket(port);
	         System.out.println("Waiting for a client...");

	         Socket socket = ss.accept(); 
	         System.out.println("Client connected");
	         System.out.println();

	         InputStream sin = socket.getInputStream();
	         OutputStream sout = socket.getOutputStream();

	         DataInputStream in = new DataInputStream(sin);
	         DataOutputStream out = new DataOutputStream(sout);

	         String line = null;
	         while(true) {
	           line = in.readUTF();
	           if(line == "<!!!>") {
               	break;
               }
	          // System.out.println("Server gets : " + line);
	           out.writeUTF(line);
	           out.flush(); 
	         }
	      } catch(Exception x) { x.printStackTrace(); }
	}
}
