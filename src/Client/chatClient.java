package Client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;

public class chatClient {
	private Socket socket;

	///TODO function throws SOCKETEXCEPTION when server is down; try/catch 
	
	public void startClientsPart(){
	
		int serverPort = 1234; 
        String address = "127.0.0.1"; 

        try {
            InetAddress ipAddress = InetAddress.getByName(address); 
            socket = new Socket(ipAddress, serverPort);

            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
            String line = null;
            System.out.print("Type your Username here:  ");
            
           while(true){
            	line = keyboard.readLine(); 
            	out.writeUTF( (line.trim()) );
            	out.flush();

         	   System.out.println("meow1");
            	line = in.readUTF();
            	if(line.equals("au"))
            		System.out.println("Username is already used, try another one");
            	else if(line.equals("ok")){
            		System.out.println("ok");
            		break;	
            	}
            }
            
           ClientChatMessageReceiver Receiver = new ClientChatMessageReceiver(in);
           Receiver.start();
           
           while (true) {
                line = keyboard.readLine(); 

                out.writeUTF(line);					
                out.flush();   
            }
           
        } catch (Exception x) {
            x.printStackTrace();
        }
	}
}
