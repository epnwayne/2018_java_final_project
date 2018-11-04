package birdgame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Wall {
	//the buffer of wall
	BufferedImage wall;
	BufferedImage[] wallact;
	int number;//the number of animation
	//the center of space
	int x;
	int y;
	
	//the size of wall
	int width;
	int height;
	
	//the space size of center
	int space;
	
	//random y
	Random updown;
	
	//to control the animation of wall
	int act;

	/**
	 * this is the constructor 
	 * and do some initialization
	 * @throws IOException 
	 */
	public Wall(int X1) throws IOException {
		//load the image of wall
		//wall = ImageIO.read(getClass().getResource("wallani/frame_00_delay-0.07s.png"));
		number = 12;
		wallact = new BufferedImage[number];
		for(int i = 0 ; i < number ; i++) {
			wallact[i] = ImageIO.read(getClass().getResource("wallani1/w"+(i)+".png"));
		}
		wall = wallact[0];
		
		//the position of wall
		x = X1;
		updown = new Random();
		y = 290 + updown.nextInt(300)-150;
//		System.out.println(y);
		
		//to get the size of wall
		width = wall.getWidth();
		height = wall.getHeight();
		
		//set the size of space
		space = 300;
	}
	
	public void WallPaint(Graphics g) {
		g.drawImage(wall, x - width/2, y - height/2, null);
		
	}
	
	public void WallRun() {
		x-=2;
	
		if(x +width < 0) {
			x = 800;
			y = 295 + updown.nextInt(300)-150;
		}
		
		//this is to control the animation of wall
		act++;
		wall = wallact[act / 5 % number];
	}

}
