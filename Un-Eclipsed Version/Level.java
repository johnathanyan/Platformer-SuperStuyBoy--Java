public abstract class Level {
    protected LevelSwitcher manager;
    public abstract void init();
    public abstract Background getBackground();
    public abstract void update();
    public abstract void draw(java.awt.Graphics2D g);
    public abstract void keyPressed(int k);
    public abstract void keyReleased(int k);
}
