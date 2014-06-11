import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;

public class Level1 extends Level{
    private Background bg;
    

    public Level1(){
	try{
	    bg = new Background("/Background/black.gif", 1);
	}catch(Exception e){
	    e.printStackTrace();
	}
    }
  
    public Background getBackground(){
	return bg;
    }

    public void init(){}
    public void update(){
	bg.update();
    }
    public void draw(java.awt.Graphics2D g){
	bg.draw(g);
    }

    private void select() {
    }

    public void keyPressed(int k){	
    }

    public void update(int k){}

    public void keyReleased(int k) {
    };

}
