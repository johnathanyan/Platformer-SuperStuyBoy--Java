import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;

public class Credits extends Level {
    private Background bg;
    private Font font;
    private LevelSwitcher manager;

    public Credits (LevelSwitcher manager) {
	this.manager = manager;

	try{
	    bg = new Background("/Backgrounds/credit.gif", 1);
	    bg.bgVectors(0, -1.5);

	    font = new Font("Arial", Font.PLAIN, 24);
	}
	catch(Exception e){
	    e.printStackTrace();
	}
    }

    public void init(){}
    public void update(){
	bg.update();
    }

    public Background getBackground(){
	return bg;
    }

    public void draw(java.awt.Graphics2D g){
	bg.draw(g);
	g.setFont(font);
	g.setColor(Color.YELLOW);
	g.drawString("EXIT", 15, 755);
    }

    private void select() {
    	bg.setPosition(0,0);
	    manager.setlevel(0);
    }

    public void keyPressed(int k){
		if (k == KeyEvent.VK_ENTER){
		    select();
		}
	}

    public void update(int k){}

    public void keyReleased(int k) {
    }
	
}
