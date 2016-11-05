package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Connection{
	public Socket Client;
	public String Username;
	public DataInputStream Out;
	public DataOutputStream In;
	
	Connection(Socket Client,String Username, DataInputStream Input,DataOutputStream Output){
		this.Client = Client; 
		this.Username = Username;
		this.Out = Input;
		this.In = Output;
	}
}	
