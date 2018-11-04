package volley;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Player extends GameObject{

	//可移動的範圍，range_0代表最左，range_1代表最右
	private int range_0, range_1;
	//擊球的力道
	public final int Force = 50;
	//檢查有沒有在空中
	protected boolean jumping = false;
	//儲存當前Y職避免小數轉成INT被捨棄
	protected double buf_y, buf_y_0;
	//判斷碰撞
	protected Rectangle rect;
	//判斷正在走路
	protected boolean walking = false;
	//判斷走的方向
	protected boolean right = false, left = false;
	
	private BufferedImage walk_img, jump_img;
	
	public Player(int x, int y, int width, int height, int range_0, int range_1)
	{
		setX(x);
		setY(y);
		setWidth(width);
		setHeight(height);
		setRange_0(range_0);
		setRange_1(range_1);
		rect = new Rectangle(x, y, width, height);
	}
	
	public void set_rect(int x, int y)
	{
		rect.setLocation(x, y);
	}
	
	public int getRange_0()
	{
		return range_0;
	}
	
	public int getRange_1()
	{
		return range_1;
	}
	
	public void setRange_0(int range_0)
	{
		this.range_0 = range_0;
	}
	public void setRange_1(int range_1)
	{
		this.range_1 = range_1;
	}
	
	public void setWalk_img(BufferedImage img)
	{
		this.walk_img = img;
	}
	
	public BufferedImage getWalk_img()
	{
		return walk_img;
	}
	
	public void setJump_img(BufferedImage img)
	{
		this.jump_img = img;
	}
	
	public BufferedImage getJump_img()
	{
		return jump_img;
	}
	
}
