package chatPackage;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JLabel;

import Client.ConnectionWindow;
import Server.CreatingServerSocketForm;
//import Client.chatClient;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;
import javax.swing.Box;

public class StartWindow {

	private JFrame frmWelcome;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartWindow window = new StartWindow();
					window.frmWelcome.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public StartWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmWelcome = new JFrame();
		frmWelcome.setTitle("Welcome");
		frmWelcome.getContentPane().setFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 10));
		frmWelcome.setBounds(100, 100, 352, 106);
		frmWelcome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmWelcome.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JLabel lblWelcomeToA = new JLabel("Welcome to a simple chat Application");
		frmWelcome.getContentPane().add(lblWelcomeToA);
		
		JButton btnConnectToChat = new JButton("Connect to already existing chat");
		btnConnectToChat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//chatClient newClient = new chatClient();
				//newClient.startClientsPart();
				ConnectionWindow window = new ConnectionWindow();
				window.frmConnection.setVisible(true);
				frmWelcome.setVisible(false);
			}
		});
		frmWelcome.getContentPane().add(btnConnectToChat);
		
		JButton btnCreateChat = new JButton("Create chat");
		btnCreateChat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreatingServerSocketForm Serv = new CreatingServerSocketForm(); 
				Serv.setVisible(true);
				frmWelcome.setVisible(false);
			}
		});
		frmWelcome.getContentPane().add(btnCreateChat);
	}

}
