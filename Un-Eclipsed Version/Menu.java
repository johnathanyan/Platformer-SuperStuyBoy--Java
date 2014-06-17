import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;

public class Menu extends Level {
    private Background bg;

    private String[] options = {
	"Start",
	"Credits",
	"Exit"
    };

    private Color titleColor;
    private Font titleFont;
    private Font font;
    private static Sound music;
    private int currentChoice;
    private LevelSwitcher manager;

    public Menu (LevelSwitcher manager) {
	this.manager = manager;
	currentChoice = 0;
	music = new Sound("menusong.wav");
	music.play();

	try{
	    bg = new Background("/Backgrounds/menu.gif", 1);
	    bg.bgVectors(-0.1, 0);

	    titleColor = new Color(128, 0, 0);
	    titleFont = new Font("Times New Roman", Font.PLAIN, 32);

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

    public Sound getMusic(){
	return music;
    }

    public void draw(java.awt.Graphics2D g){
	bg.draw(g);
	g.setFont(font);
	for(int i = 0; i < options.length; i++){
	    if (i == currentChoice) {
		g.setColor(Color.WHITE);
	    }
	    else{
		g.setColor(Color.GRAY);
	    }
	    g.drawString(options[i], 512, 476+i*32);
	}
    }
    public boolean getNextLevel(){ return false; }
    private void select() {
	if (currentChoice == 0){ // start
	    manager.setlevel(1);
	    manager.update();
	    manager.getLevels().get(1).getBackground();
	    Player.x = 200;
	    Player.y = 300;
	    music.stop();
	    
	}
	if (currentChoice == 1){ // credits
	    manager.setlevel(2);
	    music.stop();
	}
	if (currentChoice == 2){ // quit
	    System.exit(0);
	}
    }

    public void keyPressed(int k){
	if (k == KeyEvent.VK_ENTER){
	    select();
	}
	if (k == KeyEvent.VK_DOWN){
	    currentChoice++;
	    if(currentChoice == options.length){
		currentChoice = 0;
	    }
	}
	if (k == KeyEvent.VK_UP){
	    currentChoice--;
	    if(currentChoice == -1){
		currentChoice = options.length -1;
	    }
	}	
    }

    public void update(int k){}

    public void keyReleased(int k) {
    };
	
}
