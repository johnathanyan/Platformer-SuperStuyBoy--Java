import java.io.*;
import java.util.*;
import java.awt.image.BufferedImage;
import java.awt.*;
import javax.imageio.*;

public class Tile{
    private int left, right;
    private int bot, top;
    private int x, y, size;
    private BufferedImage sprite;

    public Tile(int x, int y, int size, BufferedImage sprite){
	left = x;
	right = x + 32;
	top = y;
	bot = y - 32;
    }

    public int getTop(){ return top;}
    public int getBot(){ return bot;}
    public int getLeft(){ return left;}
    public int getRight(){ return right;}

}
