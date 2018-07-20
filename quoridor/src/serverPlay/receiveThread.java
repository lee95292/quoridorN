package serverPlay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class receiveThread extends Thread {

	private Socket m_socket;
	@Override
	public void run() {
		super.run();
		
		try {
			String receiveString;
			BufferedReader tmpbuf = new BufferedReader(new InputStreamReader(m_socket.getInputStream()));
			while(true)
			{
				receiveString = tmpbuf.readLine();
				//if(receiveString==게임종료메시지)
				//else if -> 게임진행 메시지
			}
			
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	public void setSocket(Socket _socket)
	{
		m_socket= _socket;
	}
}
