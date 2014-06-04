import java.awt.*;

public class Entity {
    
    protected int _hp;
    protected int _xcor;
    protected int _ycor;
    protected Ability _special;
   
    public int getX() { return _xcor; }
    public int getY() { return _ycor; }
    public int getHP() { return _hp; }
   
    public void setX(int x) { _xcor = x; }
    public void setY(int y) { _ycor = y; } 

    public boolean isJumping;
    public boolean isFalling;

    public int gravity = 10;
    public float dx;
    public float dy;

    public thread thread;

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            dx = -1;
        }
        if (key == KeyEvent.VK_RIGHT) {
            dx = 1;
        }
        if (key == KeyEvent.VK_UP) {
            dy = -1;
        }
        if (key == KeyEvent.VK_DOWN) {
            dy = 1;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }
        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }
        if (key == KeyEvent.VK_UP) {
            dy = 0;
        }
        if (key == KeyEvent.VK_DOWN) {
            dy = 0;
        }
    }

    public void moving(){
    	_xCor += dx;
    	_ycor += dy;
    }
	    

}
