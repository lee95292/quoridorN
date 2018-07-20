package serverPlay;

import java.awt.Color;
import java.awt.Container;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class ServerUI extends JFrame{
	//private ServerSocket  s_socket;
	//private Socket c_socket;
	
	public ServerUI() {
		
		defaultSet();
		createServer();
	}
	
	public void defaultSet()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500,300);
		setResizable(false);
		Container pane= this.getContentPane();
		pane.setBackground(Color.cyan);
		pane.add(new JLabel("Your IP Is..."));
		setVisible(true);
	}
	
	public void createServer()
	{
		try {
			ServerSocket s_socket = new ServerSocket();
			Socket c_socket = s_socket.accept();
			
			receiveThread rec_thread = new receiveThread();
			rec_thread.setSocket(c_socket);
			
			sendThread send_thread = new sendThread();
			send_thread.setSocket(c_socket);
			
			rec_thread.start();
			send_thread.start();
			s_socket.close();
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}
