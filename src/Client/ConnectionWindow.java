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
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	
	public ConnectionWindow() {
		initialize();
	}

	private void initialize() {
		frmConnection = new JFrame();
		frmConnection.setTitle("Connection to the chat");
		frmConnection.setBounds(100, 100, 352, 241);
		frmConnection.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmConnection.getContentPane().setLayout(new MigLayout("", "[10:n,grow][right][10px:n][20px:n:100px,grow][60px:n][30px:n,grow]", "[40px:n,grow][][][][][][20px:n,grow]"));
		
		JLabel lblIp = new JLabel("IP:");
		lblIp.setHorizontalAlignment(SwingConstants.RIGHT);
		frmConnection.getContentPane().add(lblIp, "cell 1 1,alignx trailing");
		
		textField = new JTextField();
		frmConnection.getContentPane().add(textField, "cell 3 1 2 1,growx");
		textField.setColumns(10);
		
		JLabel lblSocket = new JLabel("Socket:");
		frmConnection.getContentPane().add(lblSocket, "cell 1 2");
		
		textField_1 = new JTextField();
		frmConnection.getContentPane().add(textField_1, "cell 3 2 2 1,growx");
		textField_1.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username:");
		frmConnection.getContentPane().add(lblUsername, "cell 1 3");
		
		textField_2 = new JTextField();
		frmConnection.getContentPane().add(textField_2, "cell 3 3 2 1,growx");
		textField_2.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		frmConnection.getContentPane().add(lblPassword, "cell 1 4");
		
		textField_3 = new JTextField();
		frmConnection.getContentPane().add(textField_3, "cell 3 4 2 1,growx");
		textField_3.setColumns(10);
		
		JLabel lblNewLabel = new JLabel(" ");
		lblNewLabel.setForeground(Color.RED);
		JButton btnConnect = new JButton("Connect");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					int serverPort = Integer.parseInt(textField_1.getText().trim()); 
					String address = textField.getText().trim();
					String username = textField_2.getText().trim();
					String password = textField_3.getText().trim();
					String answer;
					
					
					InetAddress ipAddress = InetAddress.getByName(address); 
		            Socket socket = new Socket(ipAddress, serverPort);
					
		            DataInputStream in = new DataInputStream(socket.getInputStream());
			        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
	            	out.writeUTF( username );
	            	out.flush();
	            	answer = in.readUTF();
	            	if(answer.equals("au")) {
						throw new IOException("au");
					}
	            		
		            ChatUserInterface frame = new ChatUserInterface(username, socket, in, out);
					frame.setVisible(true);
					frmConnection.setVisible(false);
				}catch(NumberFormatException e){
					lblNewLabel.setText("Error: Field \"Socket\" needs to be an integer");
				} catch (UnknownHostException e) {
					lblNewLabel.setText("Error: no working chat on this IP-adress/socket");
				} catch (IOException e) {
					if(e.getMessage().equals("au")) 
						lblNewLabel.setText("This username is already used, try another one");
					else
						lblNewLabel.setText("Error: Connection to server has been lost");
				}
				
				
			}
		});
		frmConnection.getContentPane().add(btnConnect, "cell 4 5");
		
		frmConnection.getContentPane().add(lblNewLabel, "cell 0 6 6 1");
	}

}



