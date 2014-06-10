import java.util.ArrayList;

public class LevelSwitcher {
    private ArrayList<Level> levels;
    private int currentLevel;
    public static final int MENU = 0;
    public static final int LEVEL1 = 1;
	
    public LevelSwitcher() {
	levels = new ArrayList<Level>();
	levels.add(new Menu(this));
	currentLevel = MENU;
    }

    public void setlevel(int level){
	currentLevel = level;
	levels.get(currentLevel).init();
    }

    public Level getCurrentLevel() { return levels.get(currentLevel); }
    

    public void update() {
	levels.get(currentLevel).update();
    }

    public void draw(java.awt.Graphics2D g) {
	levels.get(currentLevel).draw(g);
    }

    public void keyPressed(int k) {
	levels.get(currentLevel).keyPressed(k);
    }

    public void keyReleased(int k) {
	levels.get(currentLevel).keyReleased(k);
    }
}
