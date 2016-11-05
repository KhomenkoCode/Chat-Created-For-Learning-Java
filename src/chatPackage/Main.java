package chatPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import Client.chatClient;
import Server.ServerMain;

public class Main {

	public static void main(String[] args){
		BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
		String choise;
		System.out.println("write \"server\" to run in Server mode");
		System.out.println("write \"client\" to run in Client mode");
		
		try{
			chosen: 
			while(true){
				
				choise = ((keyboard.readLine()).toLowerCase());
				
				switch(choise){
				
					case "server":	
						ServerMain newServer = new ServerMain();
						newServer.startServerPart();
						break chosen;
					case "client":
						chatClient newClient = new chatClient();
						newClient.startClientsPart();
						break chosen;
					default:
						break;
				}
				
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

}
