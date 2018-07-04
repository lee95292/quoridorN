/*
 @author Myung Kyu Lee 
 Definite main game frame(board).
*/

package swing_DrawTable;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class boardFrame  extends JFrame{
		BorderLayout bdrLayout = new BorderLayout(10,10);
		
		public boardFrame() {
			super("table");
			//frame
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setLayout(bdrLayout);
			this.setSize((boardPanel.SquareSize+boardPanel.BoardMargin)*9,(boardPanel.SquareSize+boardPanel.BoardMargin)*9+200);
			this.setResizable(false);

			//add pane : gamePanel on contentPane
			boardPanel gamePanel = new boardPanel();
			board_south southPanel = new board_south(gamePanel.p);
			
			MouseEventHandler hdler = new MouseEventHandler(gamePanel,southPanel);
			
			Container contentPane =this.getContentPane();
			gamePanel.addMouseListener(hdler);
			gamePanel.addMouseMotionListener(hdler);
			
			contentPane.add(gamePanel, BorderLayout.CENTER);
			contentPane.add(southPanel,BorderLayout.SOUTH);
			
			
			setVisible(true);
		}
		
		
}
