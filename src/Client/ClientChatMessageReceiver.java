package Client;

import java.io.DataInputStream;
import java.io.IOException;

public class ClientChatMessageReceiver extends Thread{
	DataInputStream in;
	ClientChatMessageReceiver(DataInputStream ClientInputStream){
		in = ClientInputStream;
	}
	
	public void run(){
		String tmp;
		while(true)
		{
			try {
				tmp = in.readUTF();
				ChatUserInterface.addIncomingMessage(tmp);
			} catch (IOException e) {
				ChatUserInterface.sendMessageToTheServer("FUCK");
				// If error is find, try to send message to the chat server
				// However, this error still will be find in another (ChatUserInterface) class
				// In any case, stop listening this stream and destroy thread
				break;
			}
			
		}
	}
}
