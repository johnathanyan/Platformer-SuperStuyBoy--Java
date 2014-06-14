import java.io.*;
import java.util.*;
import java.awt.image.BufferedImage;
import java.awt.*;
import javax.imageio.*;

public class TileMap{
    private int tileSize, mapWidth, mapHeight;
    private int x, y;
    private int[][] map;
    private Tile[][] tiles;
    private BufferedImage[] sprites;
    private BufferedImage spriteSheet;
    private Scanner sc;
    private File file;

    //reads file of ints to determine which sprites to use
    public TileMap(String path, String sheetPath, int tileSize){
	this.tileSize = tileSize;
	try{
	    file = new File(path);
	    sc = new Scanner(file);
	}catch(Exception e){
	    e.printStackTrace();
	}
	
	//width and height provided in amount of tiles not pixels
	mapWidth = Integer.parseInt(sc.nextLine());
	mapHeight = Integer.parseInt(sc.nextLine());
	map = new int[mapHeight][mapWidth];
	tiles = new Tile[mapHeight][mapWidth];
	sprites = new BufferedImage[mapHeight * mapWidth];

	String toSplit = "\\s+";
	for(int i = 0; i < mapHeight; i++){
	    String line = sc.nextLine();
	    String[] elements = line.split(toSplit);
	    for(int j = 0; j < mapWidth; j++){
		map[i][j] = Integer.parseInt(elements[j]);
	    }
	}

	try{
	    spriteSheet = ImageIO.read(new File(sheetPath));
	}catch(Exception e){
	    e.printStackTrace();
	}
	divideSheet(spriteSheet);
    }

    //splits up sheet into smaller images to be used for tiles/entities
    public void divideSheet(BufferedImage sheet){
	int count = 0;
	int w = sheet.getWidth();
	int h = sheet.getHeight();
	for(int m = 0; m < (w - tileSize); m += tileSize + 1){
	    for(int n = 0; n < (h - tileSize); n += tileSize + 1){
		System.out.println(m + ", " + n);
		sprites[count] = sheet.getSubimage(m, n, tileSize, tileSize);
		count++;
	    }
	}
    }

    //actually draws the tiles on the screen depending on the int[][]
    //made in the contructor
    public void drawTiles(Graphics2D g){
	x = 0;
	y = 0;
	int count = 0;
	int sprite = 0;
	for(int i = 0; i < mapHeight; i++){
	    x = 0;
	    for(int j = 0; j < mapWidth; j++){
		int type = map[i][j];
		if(type == 0){
		    g.drawImage(sprites[3], x, y, null);
		    sprite = 3;
		}
		else if(type == 1){
		    g.drawImage(sprites[0], x, y, null);
		    sprite = 0;
		}
		else if(type == 2){
		    g.drawImage(sprites[6], x, y, null);
		    sprite = 6; 
		}
		tiles[i][j] = new Tile(x, y, tileSize, sprites[sprite]);
		x += tileSize; //move column
            }
	    y += tileSize; //moves over row when 1 is finished (now it should start at 0,0)
	}
    }

    public int[][] getMap(){
	return map;
    }

}
