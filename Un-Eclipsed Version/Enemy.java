import java.awt.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.event.*;
import java.awt.image.*;
import java.util.*;
import java.lang.Math;

public class Enemy{

    private BufferedImage sprite;
    private double x;
    private double y;
    private double dx;
    private double dy;
    private double cx,cy,cx1,cy1,cx2,cy2;
    
    public static long start;
    
    private int health; //check when to turn enemy;
    
    private boolean faceLeft, faceRight;
    public static boolean isJumping, isFalling, left, right, canMoveLeft, canMoveRight, canMoveUp, canMoveDown, startJump;

    private double walkingSpeed;
    private double xMax; 
    private double yMax;
    private double resistance;
    private double gravity;

    public Enemy(BufferedImage art, BufferedImage art2, double xcor, double ycor) {
	faceRight = true; 
	canMoveRight = true;
	canMoveLeft = true;
	canMoveUp = true;
	canMoveDown = true;
	try{
	sprite = ImageIO.read(new File("spike.png"));
	}
	catch(Exception e){
		e.printStackTrace();
	}
	x = xcor;
	y = ycor;
	cx = xcor + 16;
	cy = ycor + 16;
	cx1 = xcor;
	cx2 = xcor+32;
	cy1 = ycor+32;
	cy2 = ycor;
    }
    public boolean getRight() { return faceRight; }
    public void setRight(boolean b) { faceRight = b; } 

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
		g.drawImage(sprite,(int)x,(int)y,null);
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
	}
	if(((dy > 0) && canMoveDown) || ((dy < 0) && canMoveUp)){        
	    y += dy;
	    cy += dy;
	    cy1 += dy;
	    cy2 += dy;
	    if (!isJumping)
		dy = 0;
		    
	}
	if (canMoveDown && !isFalling) 
	    isFalling = true;

	if (isFalling){
	    if (!canMoveDown) {
		isFalling = false;
		isJumping = false;
	    }
	}

	
			
	if (isFalling && y < 600) { dy += 4; }
	/*else { 
	  isJumping = false;
	  dy = 0;
	  }*/
    }
    
    public double getX() {return x;}
    public double getY() { return y;}
    /* public double getCX() { return cx; }
    public double getCY() { return cy; }
    public double getLeft() { return cx1; }
    public double getRight() { return cx2; }
    public double getBottom() { return cy1; }
    public double getTop() { return cy2; }*/
    
    public void jump() {
	if (!isJumping && !isFalling) {
	    isJumping = true;
	    startJump = true;
	}
    }
    public boolean canMoveRight() { return canMoveRight; }
    public boolean canMoveLeft() { return canMoveLeft; } 
    public void setMoveRight(boolean b) { canMoveRight = b; }
    public void setMoveUp(boolean b) { canMoveUp = b; }
    public void setMoveDown(boolean b) { canMoveDown = b; }
    public void setMoveLeft(boolean b) { canMoveLeft = b; }
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
    
    public void move(double x, double y){
	dx += x;
	dy += y;
    }
}
