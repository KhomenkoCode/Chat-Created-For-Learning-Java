package chatPackage;



public class Main {

	public static void main(String[] args) {
		chatServer startServerThread = new chatServer();
		startServerThread.start();
		chatClient startClientThread = new chatClient();
		startClientThread.start();
	}

}
