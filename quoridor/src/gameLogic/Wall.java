package gameLogic;

import java.awt.Color;

public class Wall {
	private int x;
	private int y;
	private Color myC;
	private boolean Exist;
	private boolean IsVrt;
	
	public Wall(){
		Exist=false;
		Color myC = Color.BLACK;
	}
	
	public void setLocation(int x,int y, boolean Isvertical)
	{
		this.x=x;
		this.y=y;
		this.IsVrt=Isvertical;		
	}
	
	public int getX()
	{
		return x;
	}

	public int getY()
	{
		return y;
	}
	
	public boolean getExist()
	{
		return Exist;
	}
	
	public void setExist(boolean e)
	{
		Exist = e;
	}
	public boolean IsVertical()
	{
		return IsVrt;
	}
	public void setVrt(boolean vrt)
	{
		IsVrt = vrt;
	}
}
