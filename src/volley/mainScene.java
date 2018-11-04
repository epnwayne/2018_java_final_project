package volley;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class mainScene extends JComponent implements KeyListener{

	//FPS
	final int FPS = 60;
	//球x方向的速度
	final double speed_x = 1;
	//球的加速度，初始化為0
	private double V0_y = 0, V_y = 0, loc_x = 0, V_x = 0;
	//timer
	private Timer timer;
	//timer 的 counter 
	private int counter, walk_rate;
	private TimerTask refresh;
	//background
	private Image background;
	//player
	private Player player1, player2;
	//ball
	private Ball ball;
	//net
	private BallNet ballnet;
	
	public mainScene()
	{
		this.addKeyListener(this);
		this.setFocusable(true); //可以被關照
		this.requestFocusInWindow();
		System.out.println("dsss");
		player2 = new Player(600, 450, 110, 90, 420, 630);
		player1 = new Player(50, 450, 110, 90, 0, 250);
		ball = new Ball(50, 0, 45, 45);
		ballnet = new BallNet(350, 400, 60, 400);
		
		//-----loading picture-----//
		try {
			ball.setImage(ImageIO.read(new File("src/volley/ball.jpg")));
			background = ImageIO.read(new File("src/volley/background.jpg"));
			player1.setImage(ImageIO.read(new File("src/volley/player1.jpg")));
			player1.setWalk_img(ImageIO.read(new File("src/volley/player1_walk.png")));
			player1.setJump_img(ImageIO.read(new File("src/volley/player1_hit.jpg")));
			
			player2.setImage(ImageIO.read(new File("src/volley/player2_walk.jpg")));
			player2.setWalk_img(ImageIO.read(new File("src/volley/player2.jpg")));
			player2.setJump_img(ImageIO.read(new File("src/volley/player2_hit.jpg")));
			ballnet.setImage(ImageIO.read(new File("src/volley/BallNet.jpg")));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		refresh = new TimerTask()
				{
					public void run() 
					{
						//-----detect touch-----//
						touchBall();
						//-----update moving-----//
						moveRefresh();
						//-----repaint the screen-----//
						repaint();
						
						counter++;
						walk_rate = counter/5;
						
		//				System.out.println("ball:" + ball.rect.getMaxY());
		//				System.out.println("ballnet:" + ballnet.rect.getMinY());
					}
				};
	
		timer = new Timer();
		
		//-----call the refresh function at 60fps-----//
		timer.scheduleAtFixedRate(refresh, 0, 1000/FPS);		
			
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		//
		System.out.println("aaaaa");
		//
		super.paintComponent(g);
		//-----clear the screen-----//
		g.clearRect(0, 0, 800, 600);
		//-----draw the background-----//
		g.drawImage(background, 0, 0, null);
		//-----draw player1-----//
		if(player1.walking)
			if(walk_rate%2 == 0)
				g.drawImage(player1.getImage(), player1.getX(), player1.getY(), player1.getWidth(), player1.getHeight(), null);
			else
				g.drawImage(player1.getWalk_img(), player1.getX(), player1.getY(), player1.getWidth(), player1.getHeight(), null);
		else if(player1.jumping)
			if(walk_rate%2 == 0)
					g.drawImage(player1.getWalk_img(), player1.getX(), player1.getY(), player1.getWidth(), player1.getHeight(), null);
			else
				g.drawImage(player1.getJump_img(), player1.getX(), player1.getY(), player1.getWidth(), player1.getHeight(), null);
		else
			g.drawImage(player1.getImage(), player1.getX(), player1.getY(), player1.getWidth(), player1.getHeight(), null);
		//-----draw player2-----//
		if(player2.walking == true)
			if(walk_rate%2 == 0)
				g.drawImage(player2.getImage(), player2.getX(), player2.getY(), player2.getWidth(), player2.getHeight(), null);
			else
				g.drawImage(player2.getWalk_img(), player2.getX(), player2.getY(), player2.getWidth(), player2.getHeight(), null);
		else if(player2.jumping)
			if(walk_rate%2 == 0)
					g.drawImage(player2.getWalk_img(), player2.getX(), player2.getY(), player2.getWidth(), player2.getHeight(), null);
			else
				g.drawImage(player2.getJump_img(), player2.getX(), player2.getY(), player2.getWidth(), player2.getHeight(), null);
		else
			g.drawImage(player2.getImage(), player2.getX(), player2.getY(), player2.getWidth(), player2.getHeight(), null);
		
		//-----draw the ball-----//
		g.drawImage(ball.getImage(), ball.getX(), ball.getY(), ball.getWidth(), ball.getHeight(), null);
		//-----draw the net-----//
		g.drawImage(ballnet.getImage(), ballnet.getX(), ballnet.getY(), ballnet.getWidth(), ballnet.getHeight(), null);
	}
	
	
	
	public static void main(String args[])
	{
		JFrame frame = new JFrame("VolleyBallGame");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setLayout(new BorderLayout());
		
		mainScene game = new mainScene();
	//	game.setVisible(false);
		frame.getContentPane().add(game);
		frame.setSize(800, 600);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);	
	}
	
	
	//--------------object moving---------------//
	private void moveRefresh()
	{
		//--------------ball-------------//
		//--------X direction--------//
		loc_x = ball.getX() + V_x;
		if(ball.getX() >= -2 && ball.getX() <= 760 && ball.getY() <= 500)
			ball.setX((int)(loc_x));
		if(ball.getX() <= 0 || ball.getX() >= 760)
			ball.setX((int)(-loc_x));
		
		
		//--------Y direction--------//
		V_y = V0_y + 0.08;
		if(ball.getY() <= 500)
			ball.setY((int)(ball.getY() + V_y));//只估算呈現的畫面類似自由落體	
		V0_y = V_y;
	
		//-----------player1-----------//
		//--------X direction--------//
		if(player1.walking)
		{
			//-----move right-----//
			if(player1.right && player1.getX() < player1.getRange_1())
				player1.setX(player1.getX() + 8);
			//-----move left-----//
			if(player1.left && player1.getX() > player1.getRange_0())
				player1.setX(player1.getX() - 8);
			
		}
		//--------Y direction--------//
		if(player1.jumping)
		{
			player1.buf_y = player1.buf_y_0 + 0.15;
			player1.setY((int)(player1.getY() + player1.buf_y));
			player1.buf_y_0 = player1.buf_y;
			
			if(player1.getY() >= 450)
				player1.jumping = false;
		}
		//-----------player2-----------//
		//--------X direction--------//
		if(player2.walking)
		{
			//-----move right-----//
			if(player2.right && player2.getX() < player2.getRange_1())
				player2.setX(player2.getX() + 8);
			//move left
			if(player2.left && player2.getX() > player2.getRange_0())
				player2.setX(player2.getX() - 8);
			
		}
		//--------Y direction--------//
		if(player2.jumping)
		{
			player2.buf_y = player2.buf_y_0 + 0.15;
			player2.setY((int)(player2.getY() + player2.buf_y));
			player2.buf_y_0 = player2.buf_y;
			
			if(player2.getY() >= 450)
				player2.jumping = false;
		}
		
	}
	
	//--------------touch the ball---------------//
	private void touchBall()
	{
		//-----refresh rectangle-----//
		player1.set_rect(player1.getX(), player1.getY());
		player2.set_rect(player2.getX(), player2.getY());
		ball.set_rect(ball.getX(), ball.getY());

		//-----detect the ball out of the bound-----//
		if(ball.getX() <= 0)
			V_x = 1;
		if(ball.getX() >= 700)
			V_x = -1;
		//-----player1 touch the ball-----//
		if(player1.rect.intersects(ball.rect))
		{
			if(ball.touch == false && ball.rect.getMinX()+10 >= player1.rect.getMaxX())
			{
				V_x = 5*speed_x;
				ball.touch = true;
			}
			//-----touch the top-----//
			else if(ball.touch == false)
			{
				//-----Y direction-----//
				V_y =  -6;
				V0_y = V_y;
				//-----X direction-----//
				V_x = speed_x;
				ball.touch = true;
			}
			
				
		}	
		//-----player2 touch the ball-----//
		if(player2.rect.intersects(ball.rect))
		{
			if(ball.touch == false && ball.rect.getMaxX() <= player2.rect.getMinX()+10)
			{
				V_x = -5*speed_x;
				ball.touch = true;
			}
			//-----touch the top-----//
			else if(ball.touch == false)
			{
				//-----Y direction-----//
				V_y = -6;
				V0_y = V_y;
				//-----X direction-----//
				V_x = -speed_x;
				ball.touch = true;
			}
			
		}
		if(player1.rect.intersects(ball.rect) || player2.rect.intersects(ball.rect) )
			ball.touch = true;
		else
			ball.touch = false;
		
		//-----the ball touch the net-----//
		if(ball.rect.intersects(ballnet.rect))
		{
			if(ball.rect.getMaxY() <= ballnet.rect.getMinY()+10)//碰到上面
			{
				V_y = -5;
				V0_y = V_y;
			}
			else//碰到兩邊
				V_x = -V_x;
		}
	}
	
	@Override
	public void keyPressed(KeyEvent event) {
		// TODO Auto-generated method stub
		switch(event.getKeyCode())
		{
			//右邊玩家
			case KeyEvent.VK_RIGHT:
			{
				player2.walking = true;
				player2.right = true;
				break;
			}	
			
			case KeyEvent.VK_LEFT:
			{
				player2.walking = true;
				player2.left = true;
				break;
			}
			
			case KeyEvent.VK_UP:
			{
				if(player2.jumping == false)
				{
					player2.jumping = true;
					player2.buf_y = -9;
					player2.buf_y_0 = player2.buf_y;
				}
				break;
			}
		}
			//左邊玩家
		switch(event.getKeyCode())
		{
			case KeyEvent.VK_D:
			{
				player1.walking = true;
				player1.right = true;
				break;
			}	
			
			case KeyEvent.VK_A:
			{
				player1.walking = true;
				player1.left = true;
				break;
			}
			case KeyEvent.VK_W:
			{
				if(player1.jumping == false)
				{
					player1.jumping = true;
					player1.buf_y = -9;
					player1.buf_y_0 = player1.buf_y;
				}
				break;
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent event) {
		// TODO Auto-generated method stub
		switch(event.getKeyCode())
		{
			case KeyEvent.VK_RIGHT:
			{
				player2.walking = false;
				player2.right = false;
				break;
			}
			case KeyEvent.VK_LEFT:
			{
				player2.walking = false;
				player2.left = false;
				break;
			}
		}
		switch(event.getKeyCode())
		{	
			case KeyEvent.VK_D:
			{
				player1.walking = false;
				player1.right = false;
				break;
			}
			case KeyEvent.VK_A:
			{
				player1.walking = false;
				player1.left = false;
				break;
			}
		}
			
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
