package volley;
import java.awt.Rectangle;

public class Ball extends GameObject{

	public final int mass = 50;
	//���y�u�|��Ĳ�@�����a�]��Ĳ�Ӧh���|���W�Ĥӧ֡^
	protected boolean touch = false;
	//�P�_�I��
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
