import java.util.ArrayList;

public class LevelSwitcher {
    private ArrayList<Level> levels;
    private Player player;
    private int currentLevel;
    public static final int MENU = 0;
    public static final int LEVEL1 = 1;
	
    public LevelSwitcher(Player p) {
	levels = new ArrayList<Level>();
	levels.add(new Menu(this));
	player = p;
	levels.add(new Level1(player));
	currentLevel = MENU;
	
    }
    
    public ArrayList<Level> getLevels(){
	return levels;
    }

    public void setlevel(int level){
	currentLevel = level;
	levels.get(currentLevel).init();
    }

    public void update() {
	levels.get(currentLevel).update();
    }

    public void draw(java.awt.Graphics2D g) {
	levels.get(currentLevel).draw(g);
    }

    public void keyPressed(int k) {
	if (levels.get(currentLevel) instanceof Menu) 
	    levels.get(currentLevel).keyPressed(k);
	else 
	    ((Level1)(levels.get(currentLevel))).keyPressed(k);
	 
    }

    public Level getCurrentLevel() { return levels.get(currentLevel); }
    public Player getPlayer() { return player; }

    public void keyReleased(int k) {
	levels.get(currentLevel).keyReleased(k);
    }
}
