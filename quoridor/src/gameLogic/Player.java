package gameLogic;

import java.awt.Color;

import swing_DrawTable.boardPanel;

public class Player {

	private int x;
	private int y;
	private Color myC;
	private Board current;
	private boardPanel myPanel;
	private int wallLeft;
	
	public Wall[] myWall = new Wall[10];
	public int[] moveable = new int[4];
	/*
	 *  0 : top / 1 : right /2 :bot / 3:left 
	 */
	public Player(Board curr,Color c,boardPanel b)
	{
		wallLeft=0;
		myPanel = b;
		current = curr;
		myC=c;
		x=curr.getX();
		y=curr.getY();
		
		for(int i=0;i<10;i++)
		{
			myWall[i] = new Wall();
			if(i<4)
				moveable[i]=1;
		}
		
		if(curr.getY()==0)
			moveable[0]=0;
		if(curr.getY()==boardPanel.PanelSize-1)
			moveable[2]=0;
		
		
		
	}
	
	
	public boolean setLocation(int x,int y)
	{
		if(x>8||y>8)
			return false;
		
		this.x=x;
		this.y=y;
		current = myPanel.Square[x][y];
		return true;
	}
	public boolean IsNearBy(Board b)
	{
		if(Math.abs(this.x-b.getX())+Math.abs(this.y-b.getY())==1)
			return true;
		else 
			return false;
	}
	
	public Board getCurr()
	{
		return current;
	
	}
	
	public Color getcolor()
	{
		return myC;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public void leftWallplus()
	{
		wallLeft++;
	}
	
	public int getWall()
	{
		return wallLeft;
	}
}
