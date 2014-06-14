import java.io.*;
import java.util.*;
import java.awt.image.BufferedImage;
import java.awt.*;
import javax.imageio.*;

public class Tile{
    private boolean type = false;
    private double left, right;
    private double bot, top;
    private double x, y, size;
    private BufferedImage sprite;

    public Tile(int x, int y, int size, BufferedImage s, boolean t){
	type = t;
	left = (double)x;
	right =(double)(x + 32);
	top = (double)y;
	bot = (double)(y - 32);
	sprite = s;
    }

    
    public double getTop(){ return top;}
    public double getBottom(){ return bot;}
    public double getLeft(){ return left;}
    public double getRight(){ return right;}
    public boolean isSolid() { return type; }

}
