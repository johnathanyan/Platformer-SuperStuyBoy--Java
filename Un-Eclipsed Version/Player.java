import java.awt.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

public class Player {

    private BufferedImage sprite;
    private double x;
    private double y;
    private double dx;
    private double dy;

    private int health;

    private boolean isJumping, left, right;
    //I changed the direction to a boolean which is easier to control
    //in the keylistener. When the key is released you can just set it to false
    //instead of having to use another string for no movement.

    private double walkingSpeed;
    private double xMax; 
    private double yMax; // max velocities for velocities
    private double resistance;
    private double gravity;

    public Player(BufferedImage art, double xcor, double ycor) {
	sprite = art;
	x = xcor;
	y = ycor;
    }

    public void draw(Graphics2D g) {
	g.drawImage(sprite,(int)x,(int)y,null);
    }

    public void update() {
	x += dx;
	y += dy; 
    }

    public void move(){ // continously call this w/ a thread
	if(left){
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
    }
}
