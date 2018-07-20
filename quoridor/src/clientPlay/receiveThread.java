package clientPlay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class receiveThread extends Thread{
	private Socket m_socket;
	String receiveString;
	
	@Override
	public void run() {
		super.run();
		
		try {
			BufferedReader tmpbuf = new BufferedReader(new InputStreamReader(m_socket.getInputStream()));
			
			while(true)
			{
				receiveString = tmpbuf.readLine();
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void setSocket(Socket _socket) 
	{
		m_socket = _socket;
	}
}
