package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Connection{
	public Socket Client;
	public String Username;
	public DataInputStream In;
	public DataOutputStream Out;
	
	Connection(Socket Client,String Username, DataInputStream Input,DataOutputStream Output){
		this.Client = Client; 
		this.Username = Username;
		this.In = Input;
		this.Out = Output;
	}
	
	Connection(Connection ObjToCopy)
	{
		this.Client = ObjToCopy.Client; 
		this.Username = ObjToCopy.Username;
		this.Out = ObjToCopy.Out;
		this.In = ObjToCopy.In;
		
	}
}	
