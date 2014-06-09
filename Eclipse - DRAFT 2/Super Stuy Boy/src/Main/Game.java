package Main;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

import javax.swing.JPanel;

import GameState.GameStateManager;

public class Game extends JPanel implements Runnable, KeyListener{
	// game window size
		public static final int WIDTH = 640;
		public static final int HEIGHT = 480;

		private Thread thread; // allows for multiple actions at a time
		private boolean isRunning;
		private int FPS = 60; // frames per second
		private long targetTime = 1000 / FPS;

		private BufferedImage image; // canvas
		private Graphics2D g;

		private GameStateManager manager;

		public Game(){
			super();
			setPreferredSize(new Dimension(WIDTH,HEIGHT));
			setFocusable(true);
			requestFocus(); // makes this main window
		}

		private void init() {
			image = new BufferedImage(WIDTH/2, HEIGHT/2, BufferedImage.TYPE_INT_RGB);
			g = (Graphics2D) image.getGraphics();
			isRunning = true;
			manager = new GameStateManager();
		}

		public void addNotify() {
			super.addNotify();
			if (thread == null) {
				thread = new Thread(this);
				addKeyListener(this);
				thread.start();
			}
		}

		public void run() {
			init();
			long startTime;
			long elapsedTime;
			long waitTime;

			while(isRunning) {

				startTime = System.nanoTime();
				update();
				draw();
				drawToScreen();
				elapsedTime = System.nanoTime() - startTime;
				waitTime = targetTime - elapsedTime / 1000000;
				try {
					if (waitTime < 0)
						waitTime = 0;
					Thread.sleep(waitTime);
				}
				catch(Exception e){
					e.printStackTrace();
				}

			}
		}

		private void update(){
			manager.update();
		}
		
		private void draw(){
			manager.draw(g);
		}
		
		private void drawToScreen(){
			Graphics g2 = getGraphics();
			g2.drawImage(image, 0, 0, 640, 480, null);
			g2.dispose();
		}

		public void keyTyped(KeyEvent key) {
		}
		
		public void keyPressed(KeyEvent key) {
			manager.keyPressed(key.getKeyCode());
		}
		public void keyReleased(KeyEvent key) {
			manager.keyReleased(key.getKeyCode());
		}
}
