package swing_DrawTable;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JPanel;

import gameLogic.Player;

public class board_south extends JPanel{
	
	public static final int printHeight = 100;
	public static final int printMiddle = 300;
	private Player p[]= new Player[2];
	
	public board_south(Player[] p) {
		setLayout(new FlowLayout(0,30,70));
		setSize((boardPanel.SquareSize+boardPanel.BoardMargin)*9, 140);
		setBackground(new Color(120,20,20));
		setVisible(true);
		
		this.p[0]=p[0];
		this.p[1]=p[1];
	
	}
	
	@Override
	public void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		printTurn(g);
		printWall(g);
	}
	
	public void printTurn(Graphics g)
	{
		Color c;
		c=p[boardPanel.Turn].getcolor();
		if(boardPanel.Turn==0)
		{
			g.setColor(c);
			g.setFont(new Font("Arial",Font.BOLD,24));
			g.drawString("BLACK", printMiddle, printHeight);
		}
		else if(boardPanel.Turn==1) {
			g.setColor(c);
			g.setFont(new Font("Arial",Font.BOLD,24));
			g.drawString("WHITE", printMiddle, printHeight);
		}
	}
	
	public void printWall(Graphics g)
	{
		g.setColor(Color.GRAY);
		g.setFont(new Font("Arial",Font.ITALIC,19));

		g.drawString("WALL"+Integer.toString(10-p[0].getWall()), 0, printHeight);
		g.drawString("WALL"+Integer.toString(10-p[1].getWall()), 600, printHeight);
	}
	
	

	
}
