package volley;
import java.awt.image.BufferedImage;

public class GameObject {
	private int x, y;
	private int width, height;
	
	private BufferedImage image;
	
	public void setX(int x)
	{
		this.x = x;
	}
	public void setY(int y)
	{
		this.y = y;
	}
	public void setWidth(int width)
	{
		this.width = width;
	}
	public void setHeight(int height)
	{
		this.height = height;
	}
	
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
	public int getWidth()
	{
		return width;
	}
	public int getHeight()
	{
		return height;
	}
	public void setImage(BufferedImage image)
	{
		this.image = image;
	}
	public BufferedImage getImage() 
	{
		return image;
	}
	
//	public abstract boolean OutofBound(); // out of the window size or not
}
