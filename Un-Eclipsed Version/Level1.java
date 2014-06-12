import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;

public class Level1 extends Level{
    private Background bg;
    private Player player;
    private TileMap tiles;
    
    public Level1(Player p){
	tiles = new TileMap("test.txt",33);
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

    public void init(){}
    public void update(){
	bg.update();
    }
    public void draw(java.awt.Graphics2D g){
	bg.draw(g);
	tiles.drawTiles("testsprite.png",g);
    }

    private void select() {
    }

    public void keyPressed(int k){
	System.out.println(tiles.getMap());
	if (k == KeyEvent.VK_SPACE) 
	    player.jump();
	else if (k == KeyEvent.VK_RIGHT) 
	    player.move(1,0);
	else if (k == KeyEvent.VK_LEFT) 
	    player.move(-1,0);
	else if (k == KeyEvent.VK_UP) 
	    player.move(0,-1);
	else { player.move(0,1); } 
    }

    public void update(int k){}

    public void keyReleased(int k) {
    };

}
