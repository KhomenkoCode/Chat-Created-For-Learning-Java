package Client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;

public class chatClient {
	private Socket socket;

	public void startClientsPart(){
	
		//Need to ask a nickname 
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
            
            //endSendingUsername: while(true){
            	line = keyboard.readLine(); 
            	out.writeUTF( (line.trim()) );
            	out.flush();
            /*	if((in.readUTF().toString()).equals("AU"))
            		System.out.println("Username already used, try another one");
            	else{
            		System.out.println("Ok");
            		break endSendingUsername;	
            	}
            }*/
            
           while (true) {
                line = keyboard.readLine(); 

                out.writeUTF(line);				//Isn't a good to this send message to a server
                if(line.equals("exit")) break;	//This client & server will close simultaneously
                								//Need to be a careful in realization many clients

                
                out.flush(); 
                line = in.readUTF(); 
                
                System.out.println("Client get : " + line);
 	            System.out.println("Waiting for the next line...");
               
            }
            
 	         in.close();
 		     out.close();
        } catch (Exception x) {
            x.printStackTrace();
        }
	}
}
