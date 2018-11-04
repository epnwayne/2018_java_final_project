package volley;
import java.awt.Rectangle;

public class BallNet extends GameObject{
	
	protected Rectangle rect;
	public BallNet(int x, int y, int width, int height)
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
