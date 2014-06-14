import java.awt.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.*;
import java.lang.Math;

public class Player {

    private BufferedImage sprite;
    private double x;
    private double y;
    private double dx;
    private double dy;
    private double cx,cy,cx1,cy1,cx2,cy2;
    //x and y are top left corner, cx,cy are center of sprite
    //cx1,cx2,cy1,cy2 are midpoints of edges of sprite

    private int health;

    private boolean isJumping, canMoveLeft, canMoveRight, canMoveUp, canMoveDown;
    //I changed the direction to a boolean which is easier to control
    //in the keylistener. When the key is released you can just set it to false
    //instead of having to use another string for no movement.

    private double walkingSpeed;
    private double xMax; 
    private double yMax; // max velocities for velocities
    private double resistance;
    private double gravity;

    public Player(BufferedImage art, double xcor, double ycor) {
	canMoveRight = true;
	canMoveLeft = true;
	canMoveUp = true;
	canMoveDown = true; 
	sprite = art;
	x = xcor;
	y = ycor;
	cx = xcor + 16;
	cy = ycor + 16;
	cx1 = xcor;
	cx2 = xcor+32;
	cy1 = ycor+32;
	cy2 = ycor;
    }
    
    public boolean checkCollision(Tile t) {
	boolean colliding = false; 
	if ((Math.abs(cx-t.getX()) < 32)&&(Math.abs(cy-t.getY()) < 32))
	    colliding = true;
	//System.out.println(colliding);
	return colliding;
    }
    
    public void fixCollisions(ArrayList<Tile> tiles) {
	for (Tile t : tiles) {
	    if (cy-t.getY() > 0) { isJumping = false; }
	    move(cx-t.getX(),cy-t.getY());
	}
    }
    
    public void draw(Graphics2D g) {
	g.drawImage(sprite,(int)x,(int)y,null);
    }

    public void update() {
	//if (((dx > 0) && canMoveRight) || ((dx < 0) && canMoveLeft)) {
	x += dx;
	cx1 += dx;
	cx2 += dx;
	//}
	dx = 0;
	//if (((dy > 0) && canMoveDown) || ((dy < 0) && canMoveUp)) {        
	y += dy;
	cy1 += dy;
	cy2 += dy;
	//}
	if (isJumping) { dy++; }
	else { 
	    dy = 0;
	}
    }
    public double getX() { return cx; }
    public double getY() { return cy; }
    public double getLeft() { return cx1; }
    public double getRight() { return cx2; }
    public double getBottom() { return cy1; }
    public double getTop() { return cy2; }
    
    
    public BufferedImage getArt() { return sprite; }
    
    public void jump() {
	dy = -10; 
	isJumping = true;
    }

    public void setMoveRight(boolean b) { canMoveRight = b; }
    public void setMoveUp(boolean b) { canMoveUp = b; }
    public void setMoveDown(boolean b) { canMoveDown = b; }
    public void setMoveLeft(boolean b) { canMoveLeft = b; }

    public void move(double x, double y){ // continously call this w/ a thread
	/*	if(left){
	    dx = dx - walkingSpeed;
	    if (dx < (xMax * -1))
		dx = -1 * xMax;
	}
	else if(right){
	    dx = dx + walkingSpeed;
	    if (dx < (xMax))
		dx = xMax;
	}
	else { // no movement, decceleration
	    if (dx > 0) { // if original direction is right
		dx -= resistance;
		if (dx < 0){
		    dx = 0;
		}
	    }
	    if (dx < 0) { // if original direction is left
		dx -= resistance;
		if (dx > 0){
		    dx = 0;
		}
	    }
	}
	}*/
	dx += x;
	dy += y;
    }
}
