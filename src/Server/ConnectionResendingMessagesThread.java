package Server;

import java.io.IOException;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;

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
				String CurrentTime = (new SimpleDateFormat("HH:mm:ss")).format(new Date());
				if(!message.equals("Me::Disconect"))
					for(Connection con: clients){
						con.Out.writeUTF(client.Username +": "+ message + "\t(" + CurrentTime +")");
						con.Out.flush();
					}
				else {
					System.out.println("User " + client.Username + " was disconected");
					for(Connection con: clients){
						con.Out.writeUTF("User " + client.Username + " was disconected");
						con.Out.flush();
					}
					break;
				}
			}
		} 
		catch (IOException e){
			e.printStackTrace();
		} 
	}
}
