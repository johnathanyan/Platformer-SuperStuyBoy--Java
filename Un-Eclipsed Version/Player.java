import java.awt.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.event.*;
import java.awt.image.*;
import java.util.*;
import java.lang.Math;

public class Player {

    private BufferedImage[] runSprites;
    private BufferedImage[] jumpSprites;
    public static double x;
    public static double y;
    private double dx;
    private double dy;
    private double cx,cy,cx1,cy1,cx2,cy2;
    //x and y are top left corner, cx,cy are center of sprite
    //cx1,cx2,cy1,cy2 are midpoints of edges of sprite

    public static long start;

    private int health, sprite;

    public static boolean isJumping, isFalling, faceLeft, left, right, canMoveLeft, canMoveRight, canMoveUp, canMoveDown, startJump;
    //I changed the direction to a boolean which is easier to control
    //in the keylistener. When the key is released you can just set it to false
    //instead of having to use another string for no movement.

    private double walkingSpeed;
    private double xMax; 
    private double yMax; // max velocities for velocities
    private double resistance;
    private double gravity;

    public Player(BufferedImage art, BufferedImage art2, double xcor, double ycor) {
	canMoveRight = true;
	canMoveLeft = true;
	canMoveUp = true;
	canMoveDown = true;
	runSprites = new BufferedImage[6] ;
	jumpSprites = new BufferedImage[2]; 
	divideSheet(art, 4, runSprites);
	divideSheet(art2, 2, jumpSprites);
	x = xcor;
	y = ycor;
	cx = xcor + 16;
	cy = ycor + 16;
	cx1 = xcor;
	cx2 = xcor+32;
	cy1 = ycor+32;
	cy2 = ycor;
    }
    
    public void divideSheet(BufferedImage sheet, int count, BufferedImage[] sprites){
		int tileSize = 32;
		for (int i = 0; i < count; i++){
			sprites[i] = sheet.getSubimage(i * tileSize, 0, tileSize, tileSize);
		}
		for (int i = count - 1; i > 1; i--)
			sprites[(sprites.length % count)] = sheet.getSubimage(i * tileSize, 0, tileSize, tileSize);
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
    
    public void draw(Graphics2D g) {
    	if ((isJumping || isFalling) && sprite >= 1)
			sprite = 0;
    	if (isJumping || isFalling){
    		if (faceLeft)
    			g.drawImage(getFlippedImage(jumpSprites[sprite]),(int)x,(int)y,null);	
    		else
				g.drawImage(jumpSprites[sprite],(int)x,(int)y,null);
		}
		else{
			if (faceLeft)
				g.drawImage(getFlippedImage(runSprites[sprite]),(int)x,(int)y,null);
			else
				g.drawImage(runSprites[sprite],(int)x,(int)y,null);
		}
    }

    public static BufferedImage getFlippedImage(BufferedImage img) {
        int w = img.getWidth();
        int h = img.getHeight();
        BufferedImage flippedImage = new BufferedImage(w, h, img.getType());
        Graphics2D g = flippedImage.createGraphics();
        g.drawImage(img, 0, 0, w, h, w, 0, 0, h, null);
        g.dispose();
        return flippedImage;
    }

    public void update() {
    	long elapsed = (System.nanoTime() - start) / 1000000;
	if (((dx > 0) && canMoveRight) || ((dx < 0) && canMoveLeft)) {
	    x += dx;
	    cx += dx;
	    cx1 += dx;
	    cx2 += dx;
	}
	dx = 0;
      
        
	if (isJumping) { 
	    if (startJump){
		dy = -20; 
		startJump = false;
		isFalling = true;
	    }
	    //if (dy<0) {dy+=3;}
	    //if (canMoveUp)
	    //	dy++;
	    //else if(canMoveDown){
	    //	isFalling = true;
	    //	dy--;
	    //}
	        
	}
	if(((dy > 0) && canMoveDown) || ((dy < 0) && canMoveUp)){        
	    y += dy;
	    cy += dy;
	    cy1 += dy;
	    cy2 += dy;
	    if (!isJumping)
		dy = 0;
		    
	}
	if (canMoveDown && !isFalling) // if you're not standing on anything you fall
	    isFalling = true;

	if (isFalling){ // makes you stop falling when you hit floor
	    if (!canMoveDown) {
		isFalling = false;
		isJumping = false;
	    }
	}

	if (right)
		move(3,0);
	if (left)
		move(-3,0);

	if (left || right){
	    if (!isJumping || !isFalling){
		if (sprite >= (runSprites.length / 2)){
		    if (elapsed > 85){
			sprite = 0;
			start = System.nanoTime();
		    }
		}
		else{
		    if (elapsed > 100){
			sprite++;
			start = System.nanoTime();
		    }
		}
				
	    }
	}	
	if (!left && !right){
	    if (elapsed > 130)
		sprite = 0;
	}
	if ((isJumping || isFalling) && sprite >= 1)
	    sprite = 0;
			
	if (isFalling && y < 600) { dy += 4; } // makes you fall w gravity
	/*else { 
	  isJumping = false;
	  dy = 0;
	  }*/
    }
    
    public double getX() {return x;}
    public double getY() { return y;}
    public double getCX() { return cx; }
    public double getCY() { return cy; }
    public double getLeft() { return cx1; }
    public double getRight() { return cx2; }
    public double getBottom() { return cy1; }
    public double getTop() { return cy2; }
    
    
    //public BufferedImage getArt() { return sprite; }
    
    public void jump() {
	if (!isJumping && !isFalling) {
	    /*for (int i = 0; i < 10; i++)	
	      dy += -1 * i;*/ 
	    isJumping = true;
	    startJump = true;
	}
    }

    public void setMoveRight(boolean b) { canMoveRight = b; }
    public void setMoveUp(boolean b) { canMoveUp = b; }
    public void setMoveDown(boolean b) { canMoveDown = b; }
    public void setMoveLeft(boolean b) { canMoveLeft = b; }
    public void setDXDY(double a, double b) {dx = a; dy = b;}
    public void setXY(double a, double b) {
	x = a;
	y = b;
	cx = a + 16;
	cy = b + 16;
	cx1 = a;
	cx2 = a+32;
	cy1 = b+32;
	cy2 = b;
    }
    
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
