import java.awt.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.*;
import java.lang.Math;

public class Enemy {

    private BufferedImage[] sprites;
    private int sprite = 0;
    private double x;
    private double y;
    private double dx;
    private double dy;
    private double cx,cy,cx1,cy1,cx2,cy2;
   
    public static long start;
    public static long delay;

    private int health;

    public static boolean isJumping, left, right, canMoveLeft, canMoveRight, canMoveUp, canMoveDown;
    //I changed the direction to a boolean which is easier to control
    //in the keylistener. When the key is released you can just set it to false
    //instead of having to use another string for no movement.

    private double walkingSpeed;
    private double xMax; 
    private double yMax; // max velocities for velocities
    private double resistance;
    private double gravity;

    public Enemy(BufferedImage art, double xcor, double ycor) {
    canMoveRight = true;
	canMoveLeft = true;
	canMoveUp = true;
	canMoveDown = true; 
   	sprites = new BufferedImage[4];
	divideSheet(art);
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
	//System.out.println(cx-t.getX() + " " + (cy-t.getY()));
	//System.out.println(colliding);
	return colliding;
    }
    
    public void fixCollisions(ArrayList<Tile> tiles) {
		for (Tile t : tiles) {
		    if (cy-t.getY() > 0) { isJumping = false; }
		    String dir;
		    if (cx < t.getX()) { move((t.getX()-16)-cx,0); }
		    else if (cx > t.getX()) { move(cx-(t.getX()+16),0); }
		    else if (cy > t.getY()) { move(0,cy-(t.getY()+16)); }
		    else { move(0,(t.getY()-16)-cy); }
		  
		}
    }

    public void divideSheet(BufferedImage sheet){
	    int count = 0;
		int tileSize = 32;
		for (int i = 0; i <3; i++){
			sprites[i] = sheet.getSubimage(i * tileSize, 0, tileSize, tileSize);
		}
		sprites[3] = sheet.getSubimage((1 * tileSize), 0, tileSize, tileSize);
	}


    public void draw(Graphics2D g) {
		g.drawImage(sprites[sprite],(int)x,(int)y,null);
    }

    public void update(Graphics2D g) {
   	long elapsed = (System.nanoTime() - start) / 1000000;
	x += dx;
	cx1 += dx;
	cx2 += dx;
	dx = 0;
	y += dy;
	cy1 += dy;
	cy2 += dy;
	if (left || right){
		if (sprite >= 3){
			if (elapsed > 85){
				sprite = 0;
				start = System.nanoTime();
			}
		}
		else{
			if (elapsed > 85){
				sprite++;
				start = System.nanoTime();
			}
		}
	}
	if (!left && !right){
		if (elapsed > 130)
			sprite = 0;
	}
	if (isJumping && y < 600) { dy++; }
	else { 
	    isJumping = false;
	    dy = 0;
	}
        
    }

    public double getX() { return cx; }
    public double getY() { return cy; }
    public double getLeft() { return cx1; }
    public double getRight() { return cx2; }
    public double getBottom() { return cy1; }
    public double getTop() { return cy2; }
    
    
    public BufferedImage getArt() { return sprites[sprite]; }
    
    public void jump(){
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
