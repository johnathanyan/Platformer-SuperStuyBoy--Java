import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.File;
import javax.swing.JPanel;

public class Game extends JPanel implements Runnable, KeyListener{
    // game window size
    public static final int WIDTH = 640;
    public static final int HEIGHT = 480;

    private Player player; 
    private Thread thread; // allows for multiple actions at a time
    private boolean isRunning;
    private int FPS = 60; // frames per second
    private long targetTime = 1000 / FPS;

    private BufferedImage image; // canvas
    private Graphics2D g;

    private LevelSwitcher manager;

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
	BufferedImage playerSprite;
	try {
	    playerSprite = ImageIO.read(new File("sprite2.gif"));
	    player = new Player(playerSprite,1.0,1.0);
	}
	catch(Exception e) {e.printStackTrace();}
	manager = new LevelSwitcher(player);
	System.out.println(manager.getPlayer());
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
	player.update();
    }
		
    private void draw(){
	manager.draw(g);
	if (! (manager.getCurrentLevel() instanceof Menu)) 
	    player.draw(g);
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
    public static void main(String[] args) {
	Game g = new Game();
	g.init();
	LevelSwitcher ls = new LevelSwitcher(g.player);	

    }
}
    
