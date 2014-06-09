package GameEngine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import GameState.GameStateManager;

public class Game extends JPanel implements Runnable, KeyListener{

	// game window size
	public static final int WIDTH = 640;
	public static final int HEIGHT = 480;
	// public static final int SCALE = 2;

	private Thread thread; // allows for multiple actions at a time
	private boolean isRunning;
	private int FPS = 60; // frames per second
	private long targetTime = 1000 / FPS;

	private BufferedImage image; // canvas
	private Graphics2D g;

	private GameStateManager gsm;

	public Game(){
		super();
		setPreferredSize(new Dimension(WIDTH,HEIGHT));
		setFocusable(true);
		requestFocus(); // makes this main window
	}

	private void init() {
		image = new BufferedImage(WIDTH/2, HEIGHT/2, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) g;
		isRunning = true;
		gsm = new GameStateManager();
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
				Thread.sleep(wait);
			}
			catch(Exception e){
				e.PrintStackTrace();
			}

		}
	}

	private void update(){
		gsm.update();
	}
	private void draw(){
		gsm.draw();
	}
	private void drawToScreen(){
		Graphics g2 = getGraphics();
		g2.drawImage(image, 0, 0, null);
		g2.dispose();
	}

	public void keyTyped(KeyEvent key) {
		gsm.keyTyped(key.getKeyCode());
	}
	public void keyPressed(KeyEvent key) {
		gsm.keyPressed(key.getKeyCode());
	}
	public void keyReleased(KeyEvent key) {
		gsm.keyReleased(key.getKeyCode());
	}

}