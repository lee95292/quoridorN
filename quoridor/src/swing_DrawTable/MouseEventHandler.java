package swing_DrawTable;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import gameLogic.Player;
import gameLogic.Wall;
public class MouseEventHandler extends MouseAdapter {
	
	private int sqX,csrX;
	private int sqY,csrY;
	private boardPanel b;
	private board_south s;
	private Player currP;
	private Player nextP;
	private int flag;	//Turn end flag


	public MouseEventHandler(boardPanel b,board_south s) {		
		this.b=b;
		this.s=s;
		flag=1;
		currP = b.p[b.Turn];
		}
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		super.mouseMoved(e);
		getWall(e);
		b.repaint();
		s.repaint();
	}
	
	@Override
	public void mousePressed(MouseEvent e) 
	{
		super.mousePressed(e);
		
		setWall(e);		//  if mouse on WallShadow, create wall
		setMoveable(e);	// whenever mouse clicked, set currPlayer's movable point
		movePlayer(e);	//locate player to click point
		
		flag=1;
		System.out.println("sss"+b.Square[3][3].Vlocated+" "+b.Square[3][3].Hlocated);
		b.repaint();
		s.repaint();
	}
	
	public int getSq(int mouse)		//simple calculate for get index
	{
		return Math.floorDiv(mouse,boardPanel.SquareSize+boardPanel.BoardMargin);
	}
	
	public void setWall(MouseEvent e)	//Locate wall 
	{
		currP = b.p[b.Turn];
		if(b.wallShadow.getExist() && currP.getWall()<10)
		{
			Wall w = currP.myWall[currP.getWall()];
			currP.leftWallplus();
			int shX = b.wallShadow.getX();
			int shY = b.wallShadow.getY();
			
			w.setExist(true);		// Locate wall
			w.setLocation(shX,shY,b.wallShadow.IsVertical());
			
			if(b.wallShadow.IsVertical())
				b.Square[shX][shY].Vlocated=true;
			else
				b.Square[shX][shY].Hlocated=true;
		
			if(b.wallShadow.IsVertical())		//수직인경우,  restrict overlap of wall
			{	
				b.Square[shX][shY].Vavail=false;
				if(shY+1<boardPanel.PanelSize)
					b.Square[shX][shY+1].Vavail=false;
				if(shY>0)
					b.Square[shX][shY-1].Vavail=false;
				if(shX>0&&shX<boardPanel.SquareSize)
					b.Square[shX-1][shY+1].Havail=false;				
			}else							//수평인경우  restrict overlap of wall
			{
				b.Square[shX][shY].Havail=false;
				
				if(shX>0)
					b.Square[shX-1][shY].Havail=false;
				if(shX+1<boardPanel.PanelSize)
					b.Square[shX+1][shY].Havail=false;
				if(shY>0&&shY<boardPanel.PanelSize)
					b.Square[shX+1][shY-1].Vavail=false;			
			}
			
			flag=0; 				//Turn Over flag
			if(b.Turn==1)b.Turn=0;	//Turn Change
			else b.Turn=1;
		}
	}
	
	public void getWall(MouseEvent e)	//Loacate wallshadow	
	{									//이미 존재하는 벽이거나 벽 끝부분일 시 쉐도우 비활성화 코드 추가, 길찾기 알고리즘 기반 설치 불가능 벽 비활성화 코드 추가
		Player currP = b.p[b.Turn];
		csrX=e.getX();
		csrY=e.getY();
		int OnMarginX = csrX-getSq(e.getX())*(boardPanel.SquareSize+boardPanel.BoardMargin);
		int OnMarginY = csrY-getSq(e.getY())*(boardPanel.SquareSize+boardPanel.BoardMargin);
		
		boolean vflag = true;		//distinct locatable Wall
		boolean hflag = true;
		
		System.out.println(vflag+" "+hflag);
		if(b.wallShadow.IsVertical())				//distinct locatable wall
		{
			if(!b.Square[b.wallShadow.getX()][b.wallShadow.getY()].Vavail)
				vflag=false;
		}else
		{
			if(!b.Square[b.wallShadow.getX()][b.wallShadow.getY()].Havail)
				hflag=false;
		}
		
		if(currP.getWall()<10&&getSq(e.getX())+1<boardPanel.PanelSize&&getSq(e.getY())+1<boardPanel.PanelSize) {	
			if(OnMarginX>=boardPanel.SquareSize&&OnMarginX<boardPanel.SquareSize+boardPanel.BoardMargin&&vflag)	//Vertical wallshadow
			{
				b.wallShadow.setLocation(getSq(e.getX())+1, getSq(e.getY()), true);
				b.wallShadow.setExist(true);
			}
			else if(OnMarginY>boardPanel.SquareSize&&hflag)				//horizontal wallshadow
			{
				b.wallShadow.setLocation(getSq(e.getX()), getSq(e.getY())+1, false);				
				b.wallShadow.setExist(true);
			}
			else 
				b.wallShadow.setExist(false);
		}
		System.out.println(b.wallShadow.getX()+" "+b.wallShadow.getY());
		
	}
	public void setMoveable(MouseEvent e)
	{
		currP = b.p[b.Turn];
		if(b.Turn==1)
			nextP = b.p[0];
		else 
			nextP = b.p[1];
		
		int x = currP.getX();
		int y = currP.getY();
		
		
		for(int i=0;i<4;i++)
		{
			currP.moveable[i]=1;
		}
		// =====벽의 위치에 따라 이동을 제한하는 코드=====
		if(b.Square[x][y].Hlocated) 	
			currP.moveable[0]=0;
		
		if(b.Square[x][y].Hlocated) 	//left		
			currP.moveable[3]=0;
		
		if(x>0&&b.Square[x-1][y].Hlocated)
			currP.moveable[0]=0;
		
		if(x<boardPanel.PanelSize-1&&b.Square[x+1][y].Vlocated) 	// right 	
			currP.moveable[1]=0;
		
		if(y>0&&x<boardPanel.PanelSize-1&&b.Square[x+1][y-1].Vlocated)
			currP.moveable[1]=0;
		
		if(y<boardPanel.PanelSize-1&&b.Square[x][y+1].Hlocated) //bottom  
			currP.moveable[2]=0;
		
		if(x>0&&y<boardPanel.PanelSize-1&&b.Square[x-1][y+1].Hlocated)
			currP.moveable[2]=0;
		
		if(y>0&&b.Square[x][y-1].Hlocated)
			currP.moveable[3]=0;

			//=====다른 플레이어와 근접했을 때, 점프해주도록 설정====
		
		if(currP.getY()-nextP.getY()==1&&currP.getX()==nextP.getX())	//next player on top
			currP.moveable[0]=2;			
		
		if(currP.getX()-nextP.getX()==-1&&currP.getY()==nextP.getY())	//right	
			currP.moveable[1]=2;			
		
		if(currP.getY()-nextP.getY()==-1&&currP.getX()==nextP.getX())	//bottom
				currP.moveable[2]=2;			
		
		if(currP.getX()-nextP.getX()==1&&currP.getY()==nextP.getY())	//left
				currP.moveable[3]=2;			
		
			
		
	}
	
		

	public void movePlayer(MouseEvent e)
	{
		currP = b.p[b.Turn];
		
		int moveX =getSq(e.getX()) - currP.getX();
		int moveY =getSq(e.getY()) -currP.getY();
		int x = currP.getX();
		int y = currP.getY();
		
		if(flag==1&&(moveX==0||moveY==0))		//게임 턴일 때, setmovable의 결과에 따라 플레이어를 움직이는 알고리즘/
		{		
			if((moveY==-1||moveY==-2)&&currP.moveable[0]!=0)		//move top dir square
			{
				currP.setLocation(x,y-currP.moveable[0]);		
				flag=0;
				if(b.Turn==1)b.Turn=0;
				else b.Turn=1;
			}
			else if((moveX==1||moveX==2)&&currP.moveable[1]!=0) 
			{
				currP.setLocation(x+currP.moveable[1],y);
				flag=0;
				if(b.Turn==1)b.Turn=0;
				else b.Turn=1;
			}
			else if((moveY==1||moveY==2)&&currP.moveable[2]!=0)
			{
				currP.setLocation(x,y+currP.moveable[2]);
				flag=0;
				if(b.Turn==1)b.Turn=0;
				else b.Turn=1;
			}
			else if((moveX==-1||moveX==-2)&&currP.moveable[3]!=0)
			{
				currP.setLocation(x-currP.moveable[3],y);
		  		flag=0;
				if(b.Turn==1)b.Turn=0;
				else b.Turn=1;
			}
			
			//if(/*game end condition*/)
			// move condition
			
		}
	}
	
}
