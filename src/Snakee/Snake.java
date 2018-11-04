package Snakee;

import javax.swing.*;

public class Snake {

	public static void main(String[] args) {
		
		JFrame frame = new JFrame();
		frame.setBounds(300, 50, 800, 600);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		SnakePanel panel = new SnakePanel();
		frame.add(panel);
		
		frame.setVisible(true);
		
	}

}
