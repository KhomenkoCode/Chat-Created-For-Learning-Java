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
		//New server connection attributes
		Socket socket;
		DataInputStream in; 
		DataOutputStream out; 
		String Username;
		Connection tempConnection;
		
		boolean isRightNickname = true; 
		ConnectionResendingMessagesThread Resender; //Thread, that resends all messages from current user to all others
		
		while(!stopConnectingTheUsers){
			
			try {
				socket = server.accept();
				in = new DataInputStream(socket.getInputStream());
				out = new DataOutputStream(socket.getOutputStream());
				
				while(true){
				isRightNickname = true;
				Username = in.readUTF();
				
				for(Connection client: clients) //checking for already existing user name 
					if(Username.equals(client.Username))
						isRightNickname = false;
				
					if(isRightNickname == false){ 
						out.writeUTF("au");
						out.flush();
					}else break;
				}
				
				out.writeUTF("ok");
				
				tempConnection = new Connection(socket, Username, in, out);
			
				clients.add(tempConnection);
				
				Resender = new ConnectionResendingMessagesThread(tempConnection, clients);
				Resender.start();
				
				System.out.println("User " + Username + " connected");
				
				for(Connection con: clients)
				{
					con.Out.writeUTF(Username + " connected to a lobby");
					con.Out.flush();
				}
				
			} catch (IOException e) {	
				e.printStackTrace();
			} 
			
		}
	}
}
