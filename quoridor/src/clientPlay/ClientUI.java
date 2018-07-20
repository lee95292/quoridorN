package clientPlay;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class ClientUI extends JFrame{
	private String IPaddr;
	public ClientUI()
	{
		setLayout(null);
		setSize(500,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		
		Container pane = this.getContentPane();
		pane.setBackground(Color.cyan);
		
		JLabel title = new JLabel();
		title.setFont(new Font("Arial",Font.ITALIC,25));
		title.setBounds(120,20,300,100);
		title.setText("Game Participate");
		pane.add(title);
		JTextArea ipInp = new JTextArea();
		ipInp.setFont(new Font("Arial",Font.BOLD,16));
		ipInp.setText("Input Server IPAdress(EX:12.34.789.0)");
		ipInp.setBounds(80,100,300,30);
		pane.add(ipInp);
		
		IPaddr= null;
		
		JButton okBtn  = new JButton("OK");
		okBtn.setBounds(400, 100, 80, 30);
		okBtn.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton b = (JButton)e.getSource();
				if(b.equals(okBtn))
				{
					IPaddr = ipInp.getText();
				}
			}
		});
		pane.add(okBtn);
		setVisible(true);
	}
	
	public void createClient()
	{
		try {
			Socket c_socket= new Socket("210.117.188.34",8883);
			
			receiveThread rec_thread = new receiveThread();
			rec_thread.setSocket(c_socket);
			
			sendThread send_thread = new sendThread();
			send_thread.setSocket(c_socket);
			
			send_thread.start();
			send_thread.start();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}
