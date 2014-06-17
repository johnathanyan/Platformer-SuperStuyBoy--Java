import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.File;
import javax.swing.JPanel;
import java.util.*;
import java.lang.Math;

public class Game extends JPanel implements Runnable, KeyListener{
    // game window size
    public static final int WIDTH = 1024;
    public static final int HEIGHT = 768;

    private Player player; 
    private Enemy enemy;
    private ArrayList<Trap> traps;
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
	traps = new ArrayList<Trap>();
	image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	g = (Graphics2D) image.getGraphics();
	isRunning = true;
	BufferedImage playerSprite;
	BufferedImage playerSprite2;
	BufferedImage enemySprite;
	try {
	    playerSprite = ImageIO.read(new File("running.png"));
	    playerSprite2 = ImageIO.read(new File("jumping.png"));
	    player = new Player(playerSprite, playerSprite2, 200.0,300.0);
	    enemySprite = ImageIO.read(new File("link.gif"));
	    enemy = new Enemy(enemySprite,enemySprite,300.0,300.0);
	    enemies.add(enemy);
	    enemies.add(new Enemy(enemySprite,enemySprite,200.0,200.0));
	    traps.add(new Trap("trap.png",15,15,5,0));
	    traps.add(new Trap("trap.png",15,600,5,0));
	}
	catch(Exception e) {e.printStackTrace();}
	manager = new LevelSwitcher(player, enemies, g);
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
	    checkDeaths();
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
    
    private void checkDeaths() {
	boolean eDeath = false;
	boolean tDeath = false;
	for (Enemy e : enemies) {
	    if (Math.abs(e.getX()-(player.getX())) < 10 && Math.abs(e.getY()-(player.getY())) < 10) {
		eDeath = true;
	    }
	}
	for (Trap t : traps) { 
	    if (Math.abs(t.getX()-(player.getX())) < 10 && Math.abs(t.getY()-(player.getY())) < 10) {
		tDeath = true;
	    }
	}
	if (eDeath || tDeath) {
	    player.setXY(1,1);
	    player.setMoveLeft(false);
	    player.setMoveRight(false);
	    player.setMoveUp(false);
	    player.setMoveDown(false);
	    manager.setlevel(3);
	}
    }
		
    
    private void update(){
	manager.update();
	player.update();
	for (Enemy e : enemies) { 
	    e.update();	   
	}
	for (Trap t : traps) { 
	    t.update();
	}
	
    }
		
    private void draw(){
	manager.draw(g);
	if (! (manager.getCurrentLevel() instanceof Menu)) {
	    player.draw(g);
	    for (Enemy e : enemies) {
		e.draw(g);
	    }
	    for (Trap t : traps) { 
		t.draw(g);
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
    
