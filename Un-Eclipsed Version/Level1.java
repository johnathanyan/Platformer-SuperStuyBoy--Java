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
    private Enemy enemy;
    
    public Level1(Player p,Graphics2D graphics){
	g = graphics;
	tiles = new TileMap(p,"test.txt", "spritesheet.png", 32);
	try{
	    bg = new Background("/Backgrounds/level1.gif", 1);
	}catch(Exception e){
	    e.printStackTrace();
	}
	player = p;
    }
  
    public Background getBackground(){
	return bg;
    }

    public Player getPlayer() { return player; }

    public void init(){
	tiles.drawTiles(g);
    }
    public void update(){
	bg.update();
	player.fixCollisions(tiles.getCollisions());
	//tiles.checkCollisions();
    }
    public void draw(java.awt.Graphics2D g){
	bg.draw(g);
	tiles.drawTiles(g);
    }

    private void select() {
    }

    public void keyPressed(int k){
	if (k == KeyEvent.VK_SPACE) 
	    player.jump();
	else if (k == KeyEvent.VK_RIGHT){
	    player.move(32,0);
	    if (enemy.right != true){
	    	enemy.start = System.nanoTime();
	    }
	    enemy.right = true;
	    enemy.move(3,0);
	}
	else if (k == KeyEvent.VK_LEFT) 
	    player.move(-3,0);

	else if (k == KeyEvent.VK_UP) 
	    player.move(0,-3);
	else if (k == KeyEvent.VK_DOWN) 
	    player.move(0,3);  
    }

    public void update(int k){}

    public void keyReleased(int k) {
    	if (k == KeyEvent.VK_RIGHT){ 
	    enemy.right = false;
	    enemy.start = System.nanoTime();
    	}
	}

}
