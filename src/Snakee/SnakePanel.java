package Snakee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class SnakePanel extends JPanel implements KeyListener, ActionListener{
	

	ImageIcon up = new ImageIcon("up_resized.png");
	ImageIcon down = new ImageIcon("down_resized.png");
	ImageIcon left = new ImageIcon("left_resized.png");
	ImageIcon right = new ImageIcon("right_resized.png");
	ImageIcon title = new ImageIcon("title.jpg");
	ImageIcon food = new ImageIcon("food_resized.png");
	ImageIcon body = new ImageIcon("body_resized.png");
	ImageIcon fire = new ImageIcon("fire_resized.png");
	
	//��������
	int[] snakex = new int[1000];
	int[] snakey = new int[1000];
	int len = 3;
	String direction = "R"; //R�  L撌�  U銝�  D銝�
	
	//�璈���
	Random r = new Random();
	int foodx = r.nextInt(31)*25;
	int foody = r.nextInt(22)*25;
	
	//���
	int c = r.nextInt(5) + 1;
	int[] firex = new int[5];
	int[] firey = new int[5];
	
	//�������
	boolean isStarted = false;
	
	//����憭望��
	boolean isFailed = false;
	
	Timer timer = new Timer(150, this);
	
	//蝯梯��
	int score = 0;
	
	//����
	//int score_count = 0;
	
	public SnakePanel() {
		this.setFocusable(true);
		initSnake();//�蝵桅����
		this.addKeyListener(this); //瘛餃������
		timer.start();
		this.setVisible(true);
	}
	
	//���������
	public void initSnake() {
		isStarted = false;
		isFailed = false;
		len = 3;
		score = 0;
		direction = "R";
		snakex[0] = 100;
		snakey[0] = 100;
		snakex[1] = 75;
		snakey[1] = 100;
		snakex[2] = 50;
		snakey[2] = 100;
		
		for(int i = 0; i < len; i++) {
			while(foodx == snakex[i] && foody == snakey[i]) {
				foodx = r.nextInt(31)*25;
				foody = r.nextInt(22)*25;
			}
		}
		
		//�璈��
		for(int i = 0; i < c; i++) {
			firex[i] = r.nextInt(31)*25;
			firey[i] = r.nextInt(22)*25;
			
			for(int j = 0; j < len; j++) {
				while(firex[i] == snakex[j] && firey[i] == snakey[j] || foodx == firex[i] && foody == firey[i]) {
					firex[i] = r.nextInt(31)*25;
					firey[i] = r.nextInt(22)*25;
				}
			}
		}
		
	}
	
	public void paint(Graphics g) {
		//�撣�憿
		this.setBackground(Color.BLACK);
		g.fillRect(0, 0, 800, 600);
		//璅��
		//title.paintIcon(this, g, 325, 11);
		
		//���
		if(direction.equals("R")) {
			right.paintIcon(this, g, snakex[0], snakey[0]);
		}else if(direction.equals("L")) {
			left.paintIcon(this, g, snakex[0], snakey[0]);
		}else if(direction.equals("U")) {
			up.paintIcon(this, g, snakex[0], snakey[0]);
		}else if(direction.equals("D")) {
			down.paintIcon(this, g, snakex[0], snakey[0]);
		}
		//���澈
		for(int i = 1; i < len; i++) {
			body.paintIcon(this, g, snakex[i], snakey[i]);
		}
		
		//�����內隤�
		if(!isStarted) {
			g.setColor(Color.WHITE);
			g.setFont(new Font("arial", 2, 30));
			g.drawString("Press Enter To Start/Pause", 200, 250); //�����
		}
		
		//�憭望��內隤�
		if(isFailed) {
			g.setColor(Color.WHITE);
			g.setFont(new Font("arial", 2, 30));
			g.drawString("Game Over, Press Enter To Restart", 150, 250); //�����
			g.drawString("Score: "+score, 325, 300);
		}
		
		// �憌
		food.paintIcon(this, g, foodx, foody);
		
		// ���
		for(int i = 1; i < c; i++) {
			fire.paintIcon(this, g, firex[i], firey[i]);
		}
		
		//�����摨衣�絞閮�
		/*g.setColor(Color.BLACK);
		g.setFont(new Font("arial", 1, 15));
		g.drawString("Score: "+score, 750, 30);
		g.drawString("Length: "+len, 750, 50);*/
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if(keyCode == KeyEvent.VK_ENTER) {
			if(isFailed) {
				initSnake();
			}else{
				isStarted = !isStarted;
			}
			repaint();
		}else if(keyCode == KeyEvent.VK_UP && !direction.equals("D") && isStarted && !isFailed) {
			direction = "U";
		}else if(keyCode == KeyEvent.VK_DOWN && !direction.equals("U") && isStarted && !isFailed) {
			direction = "D";
		}else if(keyCode == KeyEvent.VK_LEFT && !direction.equals("R") && isStarted && !isFailed) {
			direction = "L";
		}else if(keyCode == KeyEvent.VK_RIGHT && !direction.equals("L") && isStarted && !isFailed) {
			direction = "R";
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	/* 1.��摰�洹���
	 * 2.��宏���
	 * 3.���撣�
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		timer.start();
		
		if(isStarted && !isFailed) {
			//蝘餃�澈擃�
			for(int i = len; i > 0; i--) {
				snakex[i] = snakex[i - 1];
				snakey[i] = snakey[i - 1];
			}
			//�蝘餃��
			if(direction.equals("R")) {
				//璈怠��� + 25
				snakex[0] = snakex[0] + 25;
				if(snakex[0] > 800) {
					snakex[0] = 0;
				}
			}else if(direction.equals("L")) {
				//璈怠��� - 25
				snakex[0] = snakex[0] - 25;
				if(snakex[0] < 0) {
					snakex[0] = 800;
				}
			}else if(direction.equals("U")) {
				//蝮勗��� - 25
				snakey[0] = snakey[0] - 25;
				if(snakey[0] < 0) {
					snakey[0] = 600;
				}
			}else if(direction.equals("D")) {
				//蝮勗��� + 25
				snakey[0] = snakey[0] + 25;
				if(snakey[0] > 600) {
					snakey[0] = 0;
				}
			}
			
			//����
			if(snakex[0] == foodx && snakey[0] == foody) {
				len++;
				score++;
				//score_count++;
				
				//��鋆賡��
				foodx = r.nextInt(31)*25;
				foody = r.nextInt(22)*25;
				for(int i = 0; i < len; i++) {
					while(foodx == snakex[i] && foody == snakey[i]) {
						foodx = r.nextInt(31)*25;
						foody = r.nextInt(22)*25;
					}
				}
				
				//��鋆賡���
				c = r.nextInt(5) + 1;
				for(int i = 0; i < c; i++) {
					firex[i] = r.nextInt(31)*25;
					firey[i] = r.nextInt(22)*25;
					
					for(int j = 0; j < len; j++) {
						while(firex[i] == snakex[j] && firey[i] == snakey[j] || foodx == firex[i] && foody == firey[i]) {
							firex[i] = r.nextInt(31)*25;
							firey[i] = r.nextInt(22)*25;
						}
					}
				}
			}
			
			//��蝣啣頨恍���香鈭�
			for(int i = 1; i < len; i++) {
				if(snakex[i] == snakex[0] && snakey[i] == snakey[0]) {
					isFailed = true;

				}
			}
			
			//��蝣啣���嚗�香鈭�
			for(int i = 0; i < c; i++) {
				if(firex[i] == snakex[0] && firey[i] == snakey[0]) {
					isFailed = true;

				}
			}
		}
		
		repaint();
	}
}
