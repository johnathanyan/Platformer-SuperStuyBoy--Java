import java.io.*;
import java.util.*;
import java.awt.image.BufferedImage;
import java.awt.*;
import javax.imageio.*;

public class TileMap{
    
    private int tileSize, mapWidth, mapHeight;
    private int minx, miny, maxx, maxy, x, y;
    private int[][] map;
    private Scanner sc;
    private File file;
    private BufferedImage sprites;


    //reads file of ints to determine which sprites to use
    public TileMap(String path, int tileSize){
	this.tileSize = tileSize;
	try{
	    file = new File(path);
	    sc = new Scanner(file);
	}catch(Exception e){
	    e.printStackTrace();
	}

	mapWidth = Integer.parseInt(sc.nextLine());
	mapHeight = Integer.parseInt(sc.nextLine());
	map = new int[mapHeight][mapWidth];

	minx = 500; //changes based on gamepanel
	miny = 500; //changes based on gamepanel

	String toSplit = "\\s+";

	for(int i = 0; i < mapHeight; i++){
	    String line = sc.nextLine();
	    String[] elements = line.split(toSplit);
	    for(int j = 0; j < mapWidth; j++){
		map[i][j] = Integer.parseInt(elements[j]);
	    }
	}
    }

    public void drawTiles(String path, Graphics2D g){
	x = 0;
	y = 0;
	try{
	    sprites = ImageIO.read(new File(path));
	}catch(Exception e){
	    e.printStackTrace();
	}
	for(int i = 0; i < mapHeight; i++){
	    y += tileSize; //move row when one is done
	    x = 0;
	    for(int j = 0; j < mapWidth; j++){
		if(map[i][j] == 1){
		    g.drawImage(sprites, x, y, null);
		}
		x += tileSize; //move column
            }
	}
    }

    public int[][] getMap(){
	return map;
    }

}
	
