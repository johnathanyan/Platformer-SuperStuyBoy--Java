package GameEngine;

import javax.swing.JFrame;

public class Window {

	public static init(){
		JFrame mainFrame = new JFrame("Super Stuy Boy");
		mainFrame.setContentPane(new Game());
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.pack();
	}

}