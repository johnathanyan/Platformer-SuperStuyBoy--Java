import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.*;
import java.util.*;

public class Level1 extends Level{
    private Background bg;
    private Player player;
    private TileMap tiles;
    private Graphics2D g;
    private ArrayList<Enemy> enemies;
    private ArrayList<Rectangle> boxes;
    private int event = 0;
    private boolean transition;
    
    public Level1(Player p, ArrayList<Enemy> _e, Graphics2D graphics){
	g = graphics;
	tiles = new TileMap(p,_e,"test.txt", "spritesheet.png", 32);
	try{
	    bg = new Background("/Backgrounds/level1.gif", 1);
	}catch(Exception e){
	    e.printStackTrace();
	}
	player = p;
	enemies = _e;
	boxes = new ArrayList<Rectangle>();
    }
  
    public Background getBackground(){
	return bg;
    }

    public Player getPlayer() { return player; }
    public boolean getNextLevel() {
	return tiles.getNextLevel();
    }

    public void init(){
	tiles.drawTiles(g);
    }
    public void update(){
	if (transition = true)
	    transition();
	bg.update();
	tiles.collider();
	tiles.enemyCollider();
	for (Enemy e : enemies) {
	    if (e.getRight()) { 
		if (e.canMoveRight())
		    e.move(3,0);
		else
		    e.setRight(false);
	    }
	    else {
		if (e.canMoveLeft()) 
		    e.move(-3,0);
		else 
		    e.setRight(true);
	    }
	}
    }

    public void draw(java.awt.Graphics2D g){
	bg.draw(g);
	tiles.drawTiles(g);
	g.setColor(java.awt.Color.BLACK);
	for(int x = 0; x < boxes.size(); x++)
		g.fill(boxes.get(x));
    }

    private void transition() {
		event++;
		if(event == 1) {
			boxes.clear();
			boxes.add(new Rectangle(0, 0, Game.WIDTH, Game.HEIGHT / 2));
			boxes.add(new Rectangle(0, 0, Game.WIDTH / 2, Game.HEIGHT));
			boxes.add(new Rectangle(0, Game.HEIGHT / 2, Game.WIDTH, Game.HEIGHT / 2));
			boxes.add(new Rectangle(Game.WIDTH / 2, 0, Game.WIDTH / 2, Game.HEIGHT));
		}
		if(event > 1 && event < 60) {
			boxes.get(0).height -= 10;
			boxes.get(1).width -= 15;
			boxes.get(2).y += 10;
			boxes.get(3).x += 15;
		}
	}

    private void select() {
    }

    public void keyPressed(int k){
	if (k == KeyEvent.VK_SPACE) 
	    player.jump();
	else if (k == KeyEvent.VK_RIGHT){
	    player.move(5,0);
	    /* if (enemy.right != true){
	       enemy.start = System.nanoTime();*/
	    
	    player.right = true;
	    player.left = false;
	    //  enemy.right = true;
	    // enemy.move(5,0);
	    player.faceLeft = false;
	}
	else if (k == KeyEvent.VK_LEFT) {
	    player.move(-5,0);
	    //enemy.move(-5,0);
	    player.left = true;
	    player.faceLeft = true;
	} 
	else if (k == KeyEvent.VK_UP) {
	    player.move(0,-5);
	    //enemy.move(0,-5);
	}
	else if (k == KeyEvent.VK_DOWN) {
	    player.move(0,5);  
	    //enemy.move(0,5);
 	   }
	}

    public void update(int k){}

    public void keyReleased(int k) {
    	if (k == KeyEvent.VK_RIGHT){ 
    	player.right = false;
	//enemy.right = false;
	//enemy.start = System.nanoTime();
    	}
    	if (k == KeyEvent.VK_LEFT){ 
    	player.left = false;
	//enemy.left = false;
	//  enemy.start = System.nanoTime();
    	}
	}

}
