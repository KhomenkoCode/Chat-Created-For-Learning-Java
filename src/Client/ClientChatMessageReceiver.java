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
				System.out.println(tmp);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
}
