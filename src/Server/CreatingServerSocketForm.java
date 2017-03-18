package Server;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class CreatingServerSocketForm extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField txtAdmin;
	private static ServerSocket mServerSocket;
	private static ArrayList<Connection> mClients = new ArrayList<Connection>();
	private static boolean isWorking = false;
	
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
		
		textField = new JTextField();
		textField.setText("1234");
		contentPane.add(textField, "cell 2 3,growx");
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel(" ");
		lblNewLabel.setForeground(Color.RED);
		contentPane.add(lblNewLabel, "cell 1 5 3 1");
		
		JButton btnStartProgrammAs = new JButton("Start a chat server");
		btnStartProgrammAs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int port = Integer.parseInt(textField.getText().trim())  ;
					mServerSocket = new ServerSocket(port);
					ServerAddConnectionsThread ConnectionsThread = new ServerAddConnectionsThread(mClients, mServerSocket);
					ConnectionsThread.start();
			    	lblNewLabel.setText("Server is working now. (Close window to stop server work)");
					lblNewLabel.setForeground(Color.MAGENTA);
					btnStartProgrammAs.setEnabled(false);
				} catch(NumberFormatException e){
					lblNewLabel.setForeground(Color.RED);
					lblNewLabel.setText("Error: Field \"Socket\" needs to be an integer");
				} catch (IOException e) {
					lblNewLabel.setForeground(Color.RED);
					lblNewLabel.setText("Error: Socket already using by somebody else");
				}
				
			}
		});
		contentPane.add(btnStartProgrammAs, "cell 3 3");
		
		JLabel lblUsername = new JLabel("Username:");
		contentPane.add(lblUsername, "cell 1 4,alignx trailing");
		
		txtAdmin = new JTextField();
		txtAdmin.setText("Admin");
		contentPane.add(txtAdmin, "cell 2 4,growx");
		txtAdmin.setColumns(10);
	}

}
