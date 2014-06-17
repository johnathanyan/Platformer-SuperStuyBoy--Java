import java.util.ArrayList;
import java.awt.*;

public class LevelSwitcher {
    private ArrayList<Level> levels;
    private Player player;
    private ArrayList<Enemy> enemies;  
    private ArrayList<Trap> traps;
    private int currentLevel;
    private Graphics2D g;
    public static final int MENU = 0;
    public static final int LEVEL1 = 1;
    
	
    public LevelSwitcher(Player p, ArrayList<Enemy> _e, ArrayList<Trap> _t, Graphics2D graphics) {
	g = graphics;
	levels = new ArrayList<Level>();
	levels.add(new Menu(this));
	player = p;
	enemies = _e;
	traps = _t; 
	levels.add(new Level1(player, enemies, traps, g));
	levels.add(new Level2(player, enemies, traps, g));
	levels.add(new Credits(this));
	currentLevel = MENU;
    Game.menu = true;
	
    }
    
    public ArrayList<Level> getLevels(){
	return levels;
    }

    public void setlevel(int level){
	currentLevel = level;
	//player.setXY(0,0);
	levels.get(currentLevel).init();
    }

    public void update() {
	levels.get(currentLevel).update();
	if (levels.get(currentLevel).getNextLevel()) 
	    setlevel(currentLevel+1);
    }

    public void draw(java.awt.Graphics2D g) {
	if (levels.get(currentLevel) != null)
        levels.get(currentLevel).draw(g);
    else {
        g.setColor(java.awt.Color.BLACK);
        g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
    }
    }

    public void keyPressed(int k) {
	if (levels.get(currentLevel) instanceof Menu) 
	    levels.get(currentLevel).keyPressed(k);
	else 
	    (levels.get(currentLevel)).keyPressed(k);
	 
    }

    public Level getCurrentLevel() { return levels.get(currentLevel); }
    public Player getPlayer() { return player; }

    public void keyReleased(int k) {
	levels.get(currentLevel).keyReleased(k);
    }
}
