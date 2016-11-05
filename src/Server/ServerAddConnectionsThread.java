package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class ServerAddConnectionsThread extends Thread{
	private ArrayList<Connection> clients;
	private boolean stopConnectingTheUsers = false;
	ServerSocket server;
	
	ServerAddConnectionsThread(ArrayList<Connection> ConnectionsList, ServerSocket ss){
		clients = ConnectionsList;
		server = ss;
	}
	
	public void run(){
		while(!stopConnectingTheUsers){
			Socket socket;
			DataInputStream in; 
			DataOutputStream out; 
			String Username;
			Connection tempConnection;
			try {
			
			socket = server.accept();
			in = new DataInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());
			
			//endSendingUsername: while(true){ //check for already existing username 
				Username = in.readUTF();
			
				/*for(Connection s: clients){ 
					if(s.Username == Username){
						out.writeUTF("AU"); // AU - Already Used
						out.flush();
						}
					else {
						out.writeUTF("Ok"); 
						out.flush();
						break endSendingUsername;
					}
					
				}
			}*/
			
			tempConnection = new Connection(socket, Username, in, out);
			
			clients.add(tempConnection);
			
			System.out.println("User " + Username + " connect");
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} //waiting user connection
			
		}
	}
}
