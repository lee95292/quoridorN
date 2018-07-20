package swing_DrawTable;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import clientPlay.ClientUI;
import serverPlay.ServerUI;

public class startMenu extends JFrame{
	
	private JButton[] menu = new JButton[8];
	public JFrame startMenu;
	public startMenu() {
		Container c = getContentPane();
		
		setVisible(true);		
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		c.setBackground(Color.MAGENTA);
		setSize(600, 800);
		
		menu[0] = new JButton("1인용 게임");
		menu[1] = new JButton("2인용 게임");
		menu[2] = new JButton("2인용 네트워크 게임(게임생성)");
		menu[3] = new JButton("2인용  네트워크 게임(게임참여)");
		menu[4] = new JButton("이전게임 Load");
		menu[5] = new JButton("이전게임 복기");
		menu[6] = new JButton("환경설정");
		menu[7] = new JButton("종료");
		
		JLabel title = new JLabel("Quoridor Game");
		title.setBounds(150, 30, 300, 100);
		title.setFont(new Font("Arial", Font.PLAIN, 40));
		add(title);

		for(int i=0;i<8;i++)
		{
			menu[i].setBounds(120, 65*i+140, 350, 50);
			menu[i].addActionListener(new MenuSelected());
			add(menu[i]);
		
		}
		
	}

	class MenuSelected implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JButton b= (JButton)e.getSource();
			
			if(b.getText().equals("2인용 게임"))
			{
				dispose();
				startMenu = new boardFrame();
			}
			
			if(b.getText().equals("종료"))
			{
				dispose();
			}
			
			if(b.getText().equals("2인용 네트워크 게임(게임생성)"))
			{
				dispose();
				startMenu = new ServerUI();
				//startMenu
			}
			if(b.getText().equals("2인용  네트워크 게임(게임참여)"))
			{
				dispose();
				startMenu = new ClientUI();
			}
		}
		
	}
}
