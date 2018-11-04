package volley;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Player extends GameObject{

	//�i���ʪ��d��Arange_0�N��̥��Arange_1�N��̥k
	private int range_0, range_1;
	//���y���O�D
	public final int Force = 50;
	//�ˬd���S���b�Ť�
	protected boolean jumping = false;
	//�x�s��eY¾�קK�p���নINT�Q�˱�
	protected double buf_y, buf_y_0;
	//�P�_�I��
	protected Rectangle rect;
	//�P�_���b����
	protected boolean walking = false;
	//�P�_������V
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
