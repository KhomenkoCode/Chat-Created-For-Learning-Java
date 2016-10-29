package chatPackage;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class chatClient extends Thread{
	private Socket socket;

	public void run(){
	

		int serverPort = 1234; 
        String address = "127.0.0.1"; 

        try {
            InetAddress ipAddress = InetAddress.getByName(address); 
            socket = new Socket(ipAddress, serverPort);

            InputStream sin = socket.getInputStream();
            OutputStream sout = socket.getOutputStream();

            DataInputStream in = new DataInputStream(sin);
            DataOutputStream out = new DataOutputStream(sout);

            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
            String line = null;
            System.out.println("Type in something and press enter. Will send it to the server and tell ya what it thinks.");
            System.out.println();

            while (true) {
                line = keyboard.readLine(); 
                
                if(line == "<!!!>") {
                	break;
                }
                
                out.writeUTF(line); 
                out.flush(); 
                
                line = in.readUTF(); 
                
                System.out.println("Client get : " + line);
 	            System.out.println("Waiting for the next line...");
               
            }
        } catch (Exception x) {
            x.printStackTrace();
        }
	}
}
