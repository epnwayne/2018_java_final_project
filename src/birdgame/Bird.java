package birdgame;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;

public class Bird {
	//the buffer of bird's animation and buffer
	BufferedImage bird;
	BufferedImage[] birdact;
	int act;//to control the action of bird
	
	//the center of bird
	int x;
	int y;
	
	//the size of bird
	int height;
	int width;
	
	//the physical parameter
	double gravity;
	double v_i;
	double v0;
	double v1;
	double t;
	double distance;
	
	
	//the angle
	double angle;
	
	/**
	 * this is the constructor
	 * @throws IOException 
	 */
	public Bird(int x1,int y1) throws IOException {
		//get the information of position
		x = x1;
		y = y1;
		
		//to load the image of bird action
		birdact = new BufferedImage[4];
		for(int i = 0 ; i < 4 ; i++) {
			birdact[i] = ImageIO.read(getClass().getResource("birdani/c"+(i)+".png"));
		}	
		bird = birdact[0];
		//get the size of bird;
		height = bird.getHeight();
		width = bird.getWidth();
		
		//initialize the physical parameter
		gravity = 3;
		v_i = 13;
		t = 0.25;
		
	}
	
	public void BirdPaint(Graphics g) {		
//		g.drawImage(bird, x - width/2, y - height/2, null);
		
	
		Graphics2D g2d = (Graphics2D) g;
		g2d.rotate(angle, x , y - height/2);
		
		//use x , y ,and make it be the center of bird

		g.drawImage(bird, x - width/2 -20, y - height/2, null);
		
		g2d.rotate(-angle, x , y - height/2);
		
	}
	
	/**
	 * this is to control bird's action
	 */
	public void	BirdRun() {
		//to make it change it action(animation)
		act++;
		bird = birdact[act/10 % 3];
		BirdFall();
		//count the angle of bird
		angle = -Math.atan(distance/15);
	}
	
	/**
	 * this method will make bird freefall
	 */
	public void BirdFall() {
		//to count the next speed
		v1 = v0 - gravity * t;
		distance = v0 - 0.5*gravity*t*t;
		v0 = v1;
		
		if(y - (int)distance > 580) {
			y = y;
		}	
		else {
			y =y - (int)distance;
		}
	}
	
	/**
	 * while call this method once will make bird jump once
	 * the way is to set it initial speed
	 */
	public void BirdFly() {
		v0 = v_i;
	}
	
	/**
	 * this is to check if bird hit something
	 * @return
	 */
	public boolean BirdCollision(LinkedList<Wall> wall) {
		//this is to check if bird hit the ceiling and ground
		if(y + height/2 > 560 || y - height/2 < 0) {
			return true;
		}
		
		//use the method to check if bird hit the walls
		if(hitwall(wall) == true) {
			return true;
		}
		return false;
	}
	
	/**
	 * this is to check if the thing which the bird hit is wall
	 * @param wall
	 * @return
	 */
	public boolean hitwall(LinkedList<Wall> wall) {
		//for each wall
		for (Wall wall1 : wall) {
			//to check if x overlap
			if(x + width/2 -20 > wall1.x- wall1.width/2 +5 && x - width/2 + 30 < wall1.x + wall1.width/2 - 10) {
				//if x overlap but y not overlap is pass
				if( y - height /2 > wall1.y - wall1.space/2 && y + height/2 < wall1.y + wall1.space/2) {
					return false;
				}
				//if x overlap but y overlap then is die
				else {
					return true;
				}
				
			}
		}
		return false;
	}
	
	/**
	 * this is to check if bird pass the column
	 */
	public boolean birdpass(LinkedList<Wall> wall) {
		for (Wall wall2 : wall) {
			//because every unit time x will - 2
			if(this.x - wall2.x == 1 || this.x - wall2.x == 0) {
				return true;
			}			
		}
		return false;
	}
	
}
