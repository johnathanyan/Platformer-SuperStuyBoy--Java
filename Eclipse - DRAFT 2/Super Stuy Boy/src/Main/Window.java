package Main;

import javax.swing.JFrame;

public class Window {

	public static void main(String[] args){
		JFrame mainFrame = new JFrame("Super Stuy Boy");
		mainFrame.setContentPane(new Game());
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setResizable(false);
		mainFrame.pack();
	}

}