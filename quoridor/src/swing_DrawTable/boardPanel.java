package swing_DrawTable;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import gameLogic.Board;
import gameLogic.Player;
import gameLogic.Wall;

public class boardPanel extends JPanel{

	public static final int PanelSize=9;
	public static final int SquareSize = 65;
	public static final int WallSize=10;
	public static final int BoardStartX = 0;
	public static final int BoardMargin = 15;
	public static int Turn = 1; 	//0 : 1p's Turn, 1 : 2p's Turn 
	
	public Wall wallShadow;
	public Player p[]= new Player[2];
	public Board[][] Square = new Board[PanelSize][PanelSize];
	private final Color boardColor = new Color(148,82,0);
	
	boardPanel(){
		setLayout(null);
		setSize((SquareSize+BoardMargin)*9, (SquareSize+BoardMargin)*9);
		setVisible(true);
		
		
		
		for(int i=0;i<PanelSize;i++)
		{
			for(int j=0;j<PanelSize;j++)
			{
				Square[i][j] =new Board(i,j);
			}
		}
		p[0] = new Player(Square[4][8],Color.BLACK,this);
		p[1] = new Player(Square[4][0],Color.WHITE,this);
		System.out.println(p[0].getX()+" "+p[0].getY());
		wallShadow = new Wall();

	}// *boardPanel constructor
	
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		board(g);
		player(g,p[0],Color.BLACK);
		player(g,p[1],Color.WHITE);
		circleInBoard(g);
		printWallShadow(g);
		printWall(g);
	}
	
	public void board(Graphics g)
	{
		g.setColor(boardColor);

		for(int i=0;i<PanelSize;i++)
		{
			for(int j=0;j<PanelSize;j++)
			{
				g.fillRect((SquareSize+BoardMargin)*i+BoardStartX, (SquareSize+BoardMargin)*j, SquareSize, SquareSize);
			}
		}
	}
	

	public void player(Graphics g,Player p,Color c) {
		g.setColor(c);
		g.fillOval((SquareSize+BoardMargin)*p.getX(), (SquareSize+BoardMargin)*p.getY(), SquareSize, SquareSize);
		
	}
	
	public void circleInBoard(Graphics g)
	{
		int x = p[Turn].getX();
		int y= p[Turn].getY();
		
		g.setColor(Color.RED);		
		g.drawString("Turn",(SquareSize+BoardMargin)*(x)+24, (SquareSize+BoardMargin)*(y)+30);
		
	/*	g.setColor(new Color(0,100,100));
		g.fillOval((SquareSize+BoardMargin)*(x+p[Turn].moveable[1])+18, (SquareSize+BoardMargin)*(y)+18, 30, 30);
		g.fillOval((SquareSize+BoardMargin)*(x+p[Turn].moveable[3])+18, (SquareSize+BoardMargin)*(y)+18, 30, 30);
		g.fillOval((SquareSize+BoardMargin)*(x)+18, (SquareSize+BoardMargin)*(y+p[Turn].moveable[0])+18, 30, 30);
		g.fillOval((SquareSize+BoardMargin)*(x)+18, (SquareSize+BoardMargin)*(y+p[Turn].moveable[2])+18, 30, 30);
		for(int i=0;i<4;i++)
			System.out.println(p[Turn].moveable[i]);*/			//플레이어 이동가능 점을 표시해주는 코드
	}
	
	public void printWallShadow(Graphics g)
	{
		g.setColor(Color.gray);
		if(wallShadow.getExist())
		{
			if(wallShadow.IsVertical())
				g.fillRect(wallShadow.getX()*(SquareSize+BoardMargin)-BoardMargin, wallShadow.getY()*(SquareSize+BoardMargin),
						BoardMargin,2*SquareSize+BoardMargin);
			else
				g.fillRect(wallShadow.getX()*(SquareSize+BoardMargin), wallShadow.getY()*(SquareSize+BoardMargin)-BoardMargin,
						2*SquareSize+BoardMargin,BoardMargin);
		}

	}
	public void printWall(Graphics g)		//벽 출력 코드
	{
		g.setColor(Color.black);
		for(int i=0;i<10;i++) {
			if(p[0].myWall[i].IsVertical())
			{
				g.fillRect(p[0].myWall[i].getX()*(SquareSize+BoardMargin)-BoardMargin, p[0].myWall[i].getY()*(SquareSize+BoardMargin),
						BoardMargin,2*SquareSize+BoardMargin);
			}
			else {
				
				g.fillRect(p[0].myWall[i].getX()*(SquareSize+BoardMargin), p[0].myWall[i].getY()*(SquareSize+BoardMargin)-BoardMargin,
						2*SquareSize+BoardMargin,BoardMargin);	
			}
			if(p[1].myWall[i].IsVertical())
			{
				g.fillRect(p[1].myWall[i].getX()*(SquareSize+BoardMargin)-BoardMargin, p[1].myWall[i].getY()*(SquareSize+BoardMargin),
				BoardMargin,2*SquareSize+BoardMargin);	
				}
			else {
					g.fillRect(p[1].myWall[i].getX()*(SquareSize+BoardMargin), p[1].myWall[i].getY()*(SquareSize+BoardMargin)-BoardMargin,
					2*SquareSize+BoardMargin,BoardMargin);
				}
			
		}
	}

	
}
