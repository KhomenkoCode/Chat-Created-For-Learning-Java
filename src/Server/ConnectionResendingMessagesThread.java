package Server;

import java.io.IOException;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;

//this class realize receiving messages from a client and
//resending it to other clients 
public class ConnectionResendingMessagesThread extends Thread{
	private Connection mClient;
	private String mMessage;
	private ArrayList<Connection> mListOfClients;
	ConnectionResendingMessagesThread(Connection tmp, ArrayList<Connection> ConnectionsList){
		mClient = tmp;
		mListOfClients = ConnectionsList;
	}
	
	public void run(){
		try {
			while(true){
				try{
				mMessage = mClient.In.readUTF();
				String CurrentTime = (new SimpleDateFormat("HH:mm:ss")).format(new Date());
					for(Connection con: mListOfClients){
						con.Out.writeUTF(mClient.Username +": "+ mMessage + "\t(" + CurrentTime +")");
						con.Out.flush();
					}
					
				}catch(java.net.SocketException e) {
					for(int i = mListOfClients.size()-1; i>=0; i--){
						if(mListOfClients.get(i).equals(mClient))
							mListOfClients.remove(i);
						else
						{
							mListOfClients.get(i).Out.writeUTF("User " + mClient.Username + " was disconected");
							mListOfClients.get(i).Out.flush();
						}
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
