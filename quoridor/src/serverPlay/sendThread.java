package serverPlay;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class sendThread extends Thread{
	private Socket m_socket;
	@Override
	public void run() {
		super.run();
		
		String sendString = null;
		try {
			PrintWriter sendWriter = new PrintWriter(m_socket.getOutputStream());
			
			sendString = "Connected";
			while(true)
			{
				sendWriter.println(sendString);
				sendWriter.flush();
			}
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	public void setSocket(Socket _socket)
	{
		m_socket = _socket;
	}
}
