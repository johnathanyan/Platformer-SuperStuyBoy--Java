import java.awt.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

public class Enemy {

    private BufferedImage[] sprites;
    private int sprite = 0;
    private double x;
    private double y;
    private double dx;
    private double dy;
    private double cx1,cy1,cx2,cy2;
   

    private int health;

    public boolean isJumping, left, right;
    //I changed the direction to a boolean which is easier to control
    //in the keylistener. When the key is released you can just set it to false
    //instead of having to use another string for no movement.

    private double walkingSpeed;
    private double xMax; 
    private double yMax; // max velocities for velocities
    private double resistance;
    private double gravity;

    public Enemy(BufferedImage art, double xcor, double ycor) {
   	sprites = new BufferedImage[3];
	divideSheet(art);
	x = xcor;
	y = ycor;
	cx1 = xcor-16;
	cx2 = xcor+16;
	cy1 = ycor-16;
	cy2 = ycor+16;
    }

    public void divideSheet(BufferedImage sheet){
	    int count = 0;
		int tileSize = 32;
		for (int i = 0; i <3; i++){
			sprites[i] = sheet.getSubimage(i * tileSize, 0, tileSize, tileSize);
		}
	}


    public void draw(Graphics2D g) {
	g.drawImage(sprites[sprite],(int)x,(int)y,null);
    }

    public void update(Graphics2D g) {
	x += dx;
	cx1 += dx;
	cx2 += dx;
	dx = 0;
	y += dy;
	cy1 += dy;
	cy2 += dy;
	if (left || right){
		if (sprite >= 2)
			sprite = 0;
		else
			sprite++;
	}
	if (isJumping && y < 600) { dy++; }
	else { 
	    isJumping = false;
	    dy = 0;
	}
        
    }
    public double getCx1() { return cx1; }
    public double getCx2() { return cx2; }
    public double getCy1() { return cy1; }
    public double getCy2() { return cy2; }
    
    
    public BufferedImage getArt() { return sprites[sprite]; }
    
    public void jump() {
	dy = -10; 
	isJumping = true;
    }

    public void move(int x, int y){ // continously call this w/ a thread
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
