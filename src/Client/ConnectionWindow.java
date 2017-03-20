package Client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import net.miginfocom.swing.MigLayout;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class ConnectionWindow {

	public JFrame frmConnection;
	private JTextField ipTextField;
	private JTextField serverPortTextField;
	private JTextField usernameTextField;

	
	public ConnectionWindow() {
		initialize("","","");
	}
	
	public ConnectionWindow(String DefaultUsername, String DefaultIP,String DefaultPort) {
		initialize( DefaultUsername, DefaultIP, DefaultPort);
	}

	private void initialize(String DefaultUsername, String DefaultIP,String DefaultPort) {
		frmConnection = new JFrame();
		frmConnection.setTitle("Connection to the chat");
		frmConnection.setBounds(100, 100, 352, 241);
		frmConnection.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmConnection.getContentPane().setLayout(new MigLayout("", "[10:n,grow][right][10px:n][20px:n:100px,grow][60px:n][30px:n,grow]", "[40px:n,grow][][][][][20px:n,grow]"));
		
		JLabel lblIp = new JLabel("IP:");
		lblIp.setHorizontalAlignment(SwingConstants.RIGHT);
		frmConnection.getContentPane().add(lblIp, "cell 1 1,alignx trailing");
		
		ipTextField = new JTextField();
		frmConnection.getContentPane().add(ipTextField, "cell 3 1 2 1,growx");
		ipTextField.setColumns(10);
		
		JLabel lblSocket = new JLabel("Socket:");
		frmConnection.getContentPane().add(lblSocket, "cell 1 2");
		
		serverPortTextField = new JTextField();
		frmConnection.getContentPane().add(serverPortTextField, "cell 3 2 2 1,growx");
		serverPortTextField.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username:");
		frmConnection.getContentPane().add(lblUsername, "cell 1 3");
		
		usernameTextField = new JTextField();
		frmConnection.getContentPane().add(usernameTextField, "cell 3 3 2 1,growx");
		usernameTextField.setColumns(10);
		
		JLabel errorLabel = new JLabel(" ");
		errorLabel.setForeground(Color.RED);
		JButton btnConnect = new JButton("Connect");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					int serverPort = Integer.parseInt(serverPortTextField.getText().trim()); 
					String address = ipTextField.getText().trim();
					String username = usernameTextField.getText().trim();
					String answer;
					
					if(username.trim().equals(""))
						throw new IOException("NoUsername");
					
					InetAddress ipAddress = InetAddress.getByName(address); 
		            Socket socket = new Socket(ipAddress, serverPort);
					
		            DataInputStream in = new DataInputStream(socket.getInputStream());
			        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
	            	out.writeUTF( username );
	            	out.flush();
	            	answer = in.readUTF();
	            	if(answer.equals("au")) {
						throw new IOException("AlreadyUsed");
					}
	            		
		            ChatUserInterface frame = new ChatUserInterface(username, socket, in, out);
					frame.setVisible(true);
					frmConnection.setVisible(false);
				}catch(NumberFormatException e){
					errorLabel.setText("Error: Field \"Socket\" needs to be an integer");
				} catch (UnknownHostException e) {
					errorLabel.setText("Error: no working chat on this IP-adress/socket");
				} catch (IOException e) {
					if(e.getMessage().equals("AlreadyUsed")) 
						errorLabel.setText("This username is already used, try another one");
					else if(e.getMessage().equals("NoUsername"))
						errorLabel.setText("Username field is empty");
					else
						errorLabel.setText("Error: Connection to server has been lost");
				}
				
				
			}
		});
		frmConnection.getContentPane().add(btnConnect, "cell 4 4");
		
		frmConnection.getContentPane().add(errorLabel, "cell 0 5 6 1");
		ipTextField.setText(DefaultIP);
		usernameTextField.setText(DefaultUsername);
		serverPortTextField.setText(DefaultPort);
	}

}



