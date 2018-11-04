package volley;
import java.awt.Rectangle;

public class Ball extends GameObject{

	public final int mass = 50;
	//讓球只會接觸一次玩家（接觸太多次會往上衝太快）
	protected boolean touch = false;
	//判斷碰撞
	protected Rectangle rect;
	public Ball(int x, int y, int width, int height)
	{
		setX(x);
		setY(y);
		setWidth(width);
		setHeight(height);
		rect = new Rectangle(x, y, width, height);
	}
	
	public void set_rect(int x, int y)
	{
		rect.setLocation(x, y);
	}

}
