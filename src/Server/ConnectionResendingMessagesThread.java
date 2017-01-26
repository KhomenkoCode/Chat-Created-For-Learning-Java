package Server;

import java.io.IOException;
import java.util.ArrayList;

//this class realize receiving messages from a client and
//resending it to other clients 
public class ConnectionResendingMessagesThread extends Thread{
	Connection client;
	String message;
	private ArrayList<Connection> clients;
	ConnectionResendingMessagesThread(Connection tmp, ArrayList<Connection> ConnectionsList){
		client = tmp;
		clients = ConnectionsList;
	}
	
	public void run(){
		try {	
			while(true){
				message = client.In.readUTF();
				if(!message.equals("Me::Disconect"))
					for(Connection con: clients){
						con.Out.writeUTF(message);
						con.Out.flush();
						this.wait(10); //wait 10 milliseconds
					}
				else break;
			}
		} 
		catch (IOException e){
			e.printStackTrace();
		} 
		catch (InterruptedException e){
			e.printStackTrace();
		}
		
	}
}
