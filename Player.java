import java.awt.*;
import javax.imageio.ImageIO;
import java.io.File;

public class Player {

    private double x;
    private double y;
    private double xSpeed;
    private double ySpeed;

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

    public void move(){ // continously call this w/ a thread
	if(left){
	    xSpeed = xSpeed - walkingSpeed;
	    if (xSpeed < (xMax * -1))
		xSpeed = -1 * xMax;
	}
	else if(right){
	    xSpeed = xSpeed + walkingSpeed;
	    if (xSpeed < (xMax))
		xSpeed = xMax;
	}
	else { // no movement, decceleration
	    if (xSpeed > 0) { // if original direction is right
		xSpeed -= resistance;
		if (xSpeed < 0){
		    xSpeed = 0;
		}
	    }
	    if (xSpeed < 0) { // if original direction is left
		xSpeed -= resistance;
		if (xSpeed > 0){
		    xSpeed = 0;
		}
	    }
	}
    }
}
