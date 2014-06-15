import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.File;
import javax.swing.JPanel;
import java.util.*;

public class Game extends JPanel implements Runnable, KeyListener{
    // game window size
    public static final int WIDTH = 1024;
    public static final int HEIGHT = 768;

    private Player player; 
    private Enemy enemy;
    private ArrayList<Enemy> enemies;
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
	enemies = new ArrayList<Enemy>();
	image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	g = (Graphics2D) image.getGraphics();
	isRunning = true;
	BufferedImage playerSprite;
	BufferedImage enemySprite;
	try {
	    playerSprite = ImageIO.read(new File("char.png"));
	    player = new Player(playerSprite,32.0,32.0);
	    enemySprite = ImageIO.read(new File("link.gif"));
	    enemy = new Enemy(enemySprite,300.0,300.0);
	    enemies.add(enemy);
	}
	catch(Exception e) {e.printStackTrace();}
	manager = new LevelSwitcher(player, enemy, g);
	//System.out.println(manager.getPlayer());
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
	    draw();
	    update();
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
	for (Enemy e : enemies) { 
	    e.update(g);	   
	}
	
    }
		
    private void draw(){
	manager.draw(g);
	if (! (manager.getCurrentLevel() instanceof Menu)) {
	    player.draw(g);
	    for (Enemy e : enemies) {
		e.draw(g);
	    }
	}
    }
		
    private void drawToScreen(){
	Graphics g2 = getGraphics();
	g2.drawImage(image, 0, 0, 1024, 768, null);
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


    }
}
    
