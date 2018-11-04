package mygame;


import java.awt.Graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;


import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class GameChoose extends JPanel{
	
	BufferedImage Choosepanel;
//	BufferedImage buttonimg;
	JButton btn_start2 = new JButton("start2");
	JButton btn_start1 = new JButton("start1");
	
	

	
	/**
	 * the constructor of Gamechoose
	 * @throws IOException
	 */
	public GameChoose() throws IOException{
		//to load the backimage
		Choosepanel = ImageIO.read(getClass().getResource("chooseback.png"));
	
		setLocation(0, 0);
		setSize(800, 600);
//		setLayout(null);
		repaint();
		
		this.initialize();
		

	}
	/**
	 * this is to initialize the component in choosepage
	 */
	public void initialize() {
		//to add the button 
				
		btn_start1.setSize(50, 50);
		btn_start1.setLocation(20, 20);
		//btn_start1.setIcon(new ImageIcon(getClass().getResource("icon.png")));
		add(btn_start1);
		

		btn_start2.setSize(50, 50);
		btn_start2.setLocation(20, 100);
		add(btn_start2);
		

		

		
	}
	/**
	 * to draw something in choose page
	 */
	public void paint(Graphics g) {
		//this is to draw the background image
		g.drawImage(Choosepanel, 0, 0, null);

	}


	
	

	
	
}
