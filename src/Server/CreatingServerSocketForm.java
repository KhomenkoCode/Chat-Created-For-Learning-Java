package Server;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Client.ChatUserInterface;
import Client.ConnectionWindow;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Color;

//this class used to get info from user
//which need to create a new chat session
public class CreatingServerSocketForm extends JFrame {

	private JPanel contentPane;
	private JTextField txtPort;
	private JTextField txtUsername;
	private static ServerSocket mServerSocket;
	private static volatile ArrayList<Connection> mClients = new ArrayList<Connection>();
	
	public CreatingServerSocketForm() {
		setTitle("Server");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 202);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow][][100px:n,grow][50px:n][grow]", "[grow][][5px][][][20px:n][][grow]"));
		
		JLabel lblWriteTheServer = new JLabel("Write the server port, that will be used to comunicate with users");
		contentPane.add(lblWriteTheServer, "cell 1 1 3 1");
		
		JLabel lblPort = new JLabel("Port:");
		contentPane.add(lblPort, "cell 1 3,alignx trailing");
		
		JLabel lblUsername = new JLabel("Username:");
		contentPane.add(lblUsername, "cell 1 4,alignx trailing");
		
		txtUsername = new JTextField();
		txtUsername.setText("Admin");
		
		contentPane.add(txtUsername, "cell 2 4,growx");
		txtUsername.setColumns(10);
		txtPort = new JTextField();
		txtPort.setText("1234");
		contentPane.add(txtPort, "cell 2 3,growx");
		txtPort.setColumns(10);
		
		JButton btnConnectToChat = new JButton("Connect to the chat");
		btnConnectToChat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ConnectionWindow window = new ConnectionWindow(txtUsername.getText(), "127.0.0.1", "1234");
				window.frmConnection.setVisible(true);
			}
		});
		btnConnectToChat.setEnabled(false);
		contentPane.add(btnConnectToChat, "cell 3 4");
		
		JLabel infoLabel = new JLabel(" ");
		infoLabel.setForeground(Color.RED);
		contentPane.add(infoLabel, "cell 1 5 3 1");
		
		JButton btnStartProgrammAs = new JButton("Start a chat server");
		btnStartProgrammAs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int port = Integer.parseInt(txtPort.getText().trim())  ;
					mServerSocket = new ServerSocket(port);
					ServerAddConnectionsThread ConnectionsThread = new ServerAddConnectionsThread(mClients, mServerSocket);
					ConnectionsThread.start();
			    	infoLabel.setText("Server is working now. (Close window to stop server work)");
					infoLabel.setForeground(Color.MAGENTA);
					btnStartProgrammAs.setEnabled(false);
					btnConnectToChat.setEnabled(true);
				} catch(NumberFormatException e){
					infoLabel.setForeground(Color.RED);
					infoLabel.setText("Error: Field \"Socket\" needs to be an integer");
				} catch (IOException e) {
					infoLabel.setForeground(Color.RED);
					infoLabel.setText("Error: Socket already using by somebody else");
				}
				
			}
		});
		contentPane.add(btnStartProgrammAs, "cell 3 3");
		
		
	}

}
