package gameLogic;


public class Board{
	private int x;
	private int y;
	
	public boolean Vlocated;
	public boolean Hlocated;
	public boolean Vavail;
	public boolean Havail;
	public boolean Msovered;
	public Board(int x,int y){
		this.x=x;
		this.y=y;
		Msovered=false;
		Vavail=true;
		Havail=true;
		Vlocated = false;
		Hlocated= false;
	}
	
	public void IsMsOvered(boolean b)
	{
		Msovered=b;
	}

	public int getX() 
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
}
