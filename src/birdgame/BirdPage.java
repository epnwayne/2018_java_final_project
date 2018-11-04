package birdgame;

import java.awt.Color;
import java.awt.Font;
//import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class BirdPage extends JPanel implements Runnable , MouseListener{
	//the buffer of background image
	BufferedImage birdbackground;
	int backx;
	
	//the buffer of start image
	BufferedImage startimg;
	BufferedImage startani[];
	int startact;
	
	//the buffer of end image
	BufferedImage endimg;
	BufferedImage endani[];
	int endact;
	
	//the object of walls
	Wall[] walls;
	LinkedList<Wall> Walls;
	int number_walls;//the number of walls
	int now_walls;
	//the object of bird
	Bird bird;
	
	//the flag to check if the game start
	boolean start;
	//the flag is to check if the bird is die
	boolean die;
	
	//to record the score
	int score;
	
	
	//the following is at myframe but not sure if it can write at this class
	Thread t_bird;
	
	/**
	 * this is the constructor 
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public BirdPage() throws IOException, InterruptedException {
		setLocation(0, 0);
		setSize(800, 600);
		setLayout(null);
		//to load the image of background
		birdbackground = ImageIO.read(getClass().getResource("background1.png"));	
	
		//to load the image of start and start image
		startani = new BufferedImage[4];
		endani = new BufferedImage[4];
		for(int i = 0 ; i < 4 ; i ++) {
			startani[i] = ImageIO.read(getClass().getResource("startani/s"+i+".png"));
			endani[i] = ImageIO.read(getClass().getResource("endani/e"+i+".png"));
		}
		startimg = startani[0];
		endimg = endani[0];

		
		
		//to add a mouse listener
		addMouseListener(this);
		
		//the following is at myframe but not sure if it can write at this class
		t_bird = new Thread(this);
		//make the thread start
		t_bird.start();
	
		
		//use the initialize method
		this.initialize();
		
	}
	/**
	 * this is to initialize the component in Birdpage
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public void initialize() throws IOException, InterruptedException {
		//set the back ground
		//backx = 0;
		
		//set score == 0
		score = 0;
		//at beginning start is false and bird is alive
		start = false;
		die = false;
		
		//to create the array of the wall
		number_walls = 4;
		//walls = new Wall[number_walls];	
		Walls = new LinkedList<Wall>();
		for(int i = 0 ; i < number_walls ; i++) {
			//walls[i] = new Wall(700 + 209*i);
			Walls.add(new Wall(700 + 209*i));
		}
		
		//to create the bird
		bird = new Bird(200,300);
	}

	
	/**
	 * this is use to paint something on the birdpage
	 */
	public void paint(Graphics g) {
		//paint the background 
		g.drawImage(birdbackground, backx, 0, null);
		
		if(start == false && die == false) {
			g.drawImage(startimg, 57, 100, null);
		}
		if(start == true) {
			bird.BirdPaint(g);
		}
	
		for(Wall wall : Walls) {
			wall.WallPaint(g);
		}

		if(die == true) {
			g.drawImage(endimg, 70, 100, null);
		}
		
		g.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 50));
		g.setColor(Color.GREEN);
		g.drawString("Score : " + score, 20, 55);
		
	}
	
	/**
	 * this is to check if the bird is dead
	 */
	public void BirdDie() {
		//because if the collision is true, then start is false
//		die = bird.BirdCollision(walls);
		die = bird.BirdCollision(Walls);
//		System.out.println(die);
		if(die == true) {
			start = false;
		}
	}
	
	/**
	 * this is to implement runnable
	 * 
	 */
	public void run() {
		//run and not stop
		while(true) {
			
			//this is to check when will the component start to run
			if(start == true) {
				//wall run
				for (Wall wall : Walls) {
					wall.WallRun();
				}
				//bird run
				bird.BirdRun();
				//when will bird die
				this.BirdDie();
				this.backroll();
				this.scorecount();

			}
			else {
				this.startanimation();
			}
			if(die == true) {
				this.endanimation();
			}
			repaint();
			//Thread.currentThread();
			try {
				Thread.sleep(1000/60);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * this is to make the map roll
	 */
	public void backroll() {
		backx--;
		if(backx < -1568) {
			backx = 0;
		}
	}
	
	/**
	 * this is to make the start animation
	 */
	public void startanimation() {
		startact++;
		startimg = startani[startact / 20 % 4];
	}
	
	/**
	 * this is to make the end animation
	 */
	public void endanimation() {
		endact++;
		endimg = endani[endact / 30 % 4];
	}
	/**
	 * this is to count the score
	 */
	public void scorecount() {
		if(bird.birdpass(Walls) == true) {
			score++;
			System.out.println(score);
		}
	}
	
	/**
	 * the following is to override the interface==>mouselistener
	 * @param arg0
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(die == false) {
			start = true;
		}
		bird.BirdFly();
		//System.out.println("1");
		
		if(die == true) {
			try {
				Walls.removeAll(Walls);
				initialize();
				
			} catch (IOException | InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
