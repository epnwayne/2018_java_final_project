package mygame;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;

import Snakee.SnakePanel;
import birdgame.BirdPage;
import volley.mainScene;


public class MyFrame extends JFrame implements ActionListener{
	//this is to get some information of pc 
	Toolkit tool;
	//this is the size of screen
	int s_width;
	int s_height;
	//this is the size of window
	int w_width;
	int w_height;
	
	//declare the choose page
	GameChoose choosepanel;
	//declare the bird page
	BirdPage birdpage;
	//declare the volley page
	mainScene volleypage;
	//declare the snake page
	SnakePanel snakepage;
	
	//declare which game we need to choose
	int whichgame;//0 = choosepage
	
	//create a new thread for bird page 
	//Thread t_bird;
	
	
	/**
	 * this is use to start the program and control the function
	 * @throws IOException
	 * @throws InterruptedException 
	 */
	public void start() throws IOException, InterruptedException {
		//MyFrame frame = new MyFrame();
		this.setVisible(true);

	
	}
	
	/**
	 * the construction of my frame
	 * @throws IOException
	 * @throws InterruptedException 
	 */
	public MyFrame() throws IOException, InterruptedException {
		
		//to get the size of screen
		tool = Toolkit.getDefaultToolkit();
		s_width = tool.getScreenSize().width;
		s_height = tool.getScreenSize().height;
		//set the size of window
		w_width = 800;
		w_height = 600;
		
		//this is to set the attribute of window
		setLocation((s_width - w_width) / 2, (s_height - w_height) / 2);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(w_width, w_height);
		setResizable(false);
//		setLayout(null);
		//setVisible(true);
		
		this.initialize();
		pagevisible(0);
	}
	
	/**
	 * this is to initialize the component of Frame
	 * @throws IOException
	 * @throws InterruptedException 
	 */
	public void initialize() throws IOException, InterruptedException {
		
		/**
		 * this is to construct choosepage
		 */
		choosepanel = new GameChoose();
		add(choosepanel);
		//to listen the button in choose page
//		choosepanel.btn_start.addActionListener(this);
		choosepanel.btn_start1.addActionListener(this);
		choosepanel.btn_start2.addActionListener(this);
		
		/**
		 * this is to construct bird page
		 */
		birdpage = new BirdPage();
		//and this is to start the thread of birdpage
		//t_bird = new Thread(birdpage);
		//t_bird.start();
		//and add a birdpage to frame
		add(birdpage);
		
		/**
		 * this is to construct volley page
		 */
		volleypage = new mainScene();
		getContentPane().add(volleypage);
		volleypage.setVisible(true);
		
		
		/**
		 * this is to construct snake page
		 */
		snakepage = new SnakePanel();
		add(snakepage);
		snakepage.setVisible(false);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("start1")) {
			System.out.println("123");
			try {
				this.pagevisible(1);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		if(e.getActionCommand().equals("start2")) {
			System.out.println("456");
			try {
				this.pagevisible(2);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	/**
	 * this is to make which page can be visible
	 * @param page
	 * 0 = choosepage
	 * 1 = bird
	 * @throws InterruptedException 
	 */
	public void pagevisible(int page) throws InterruptedException {
		switch (page) {
		case 0:{
			choosepanel.setVisible(true);
			birdpage.setVisible(false);
			snakepage.setVisible(false);
			volleypage.setVisible(false);
			break;
		}
		case 1:{
			choosepanel.setVisible(false);
			birdpage.setVisible(true);
			snakepage.setVisible(false);
			volleypage.setVisible(false);
			//add(birdpage);
			break;
		}
		case 2:{
			choosepanel.setVisible(false);
			birdpage.setVisible(false);
			snakepage.setVisible(true);
			volleypage.setVisible(false);
			//add(birdpage);
			break;
		}
		
		default:
			break;
		}
	}
	

	

}
