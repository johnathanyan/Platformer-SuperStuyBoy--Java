import java.io.*;
import java.util.*;
import java.awt.image.BufferedImage;
import java.awt.*;
import javax.imageio.*;
import java.io.File;
import java.lang.Math;

public class Trap { 
    private double x,y,dx,dy,spawnX,spawnY;
    private BufferedImage sprite;
    
    public Trap(String path, double xcor, double ycor, double xrate, double yrate) {
	try { 
	    sprite = ImageIO.read(new File("trap.png"));
	}
	catch(Exception e) { e.printStackTrace(); } 
	x = xcor;
	y = ycor;
	spawnX = x; 
	spawnY = y; 
	dx = xrate;
	dy = yrate;
    }
    
    public void update() {
	x+=dx;
	y+=dy;
	if ( Math.abs(x) > 1024 || Math.abs(y) > 768 ) {
	    setX(spawnX);
	    setY(spawnY);
	}
    }

    public void draw(Graphics2D g) {
	g.drawImage(sprite,(int)x,(int)y,null);
    }
    
    public double getX() { return x; } 
    public double getY() { return y; }
    public void setX(double a) { x = a; }
    public void setY(double a) { y = a; }
}
