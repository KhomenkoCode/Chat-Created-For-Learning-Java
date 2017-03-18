package Client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javafx.scene.input.KeyEvent;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class ChatUserInterface extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private static Socket mSocket;
	private static DataInputStream mSocketInputStream;
	private static DataOutputStream mSocketOutputStream;
	private static JTextArea mtextArea;
	private JScrollPane scrollPane;
	
	public ChatUserInterface(String username, Socket socket, DataInputStream in, DataOutputStream out) {
		mSocket = socket;
		mSocketInputStream = in;
		mSocketOutputStream = out;
		setTitle(username);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[::10px][grow][::60px,grow][::10px]", "[::10px][grow][::5px][][::10px]"));
		
		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "cell 1 1 2 1,grow");
		
		mtextArea = new JTextArea();
		scrollPane.setViewportView(mtextArea);
		mtextArea.setEditable(false);
		
		
		textField = new JTextField();
		contentPane.add(textField, "cell 1 3,growx");
		textField.setColumns(10);
		
		JButton btnSend = new JButton("Send");
		
		textField.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(java.awt.event.KeyEvent arg0) {
				if(arg0.getKeyCode() == 10){
					sendMessageToTheServer(textField.getText());
                	textField.setText("");
                }
			}

			@Override
			public void keyReleased(java.awt.event.KeyEvent arg0) {
				
			}

			@Override
			public void keyTyped(java.awt.event.KeyEvent arg0) {
				
			}

		     
		});
		
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sendMessageToTheServer(textField.getText());
				textField.setText("");
			}
		});
		
		contentPane.add(btnSend, "cell 2 3");
		
		ClientChatMessageReceiver Receiver = new ClientChatMessageReceiver(mSocketInputStream);
        Receiver.start();
			
	}
	
	protected static void addIncomingMessage(String message){
		mtextArea.append(message+"\n");
	}
	
	protected static void sendMessageToTheServer(String message){
		try {
			mSocketOutputStream.writeUTF(message);
			mSocketOutputStream.flush();  
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Connection to server has been lost");
			mtextArea.append("Connection to server has been lost"+"\n");
		}					 
	}
}
