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
    private Player player; 

    //reads file of ints to determine which sprites to use
    public TileMap(Player p, String path, String sheetPath, int tileSize){
	this.player = p;
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

    public void checkCollisions() {
	Tile cur;
	int mapX = 0;
	int mapY = 0;
	//Finds current tile of player
	for (int i = 0; i < tiles.length; i++) {
	    for (int n = 0; n < tiles[i].length; n++) {		
		cur = tiles[i][n];
		/*	System.out.println(player.getX());
		System.out.println(player.getY());
		System.out.println(cur.getRight());
		System.out.println(cur.getTop());
		System.out.println(cur.getBottom());
		System.out.println(cur.getLeft());*/
		if (player.getX() >= cur.getLeft() && player.getX() < cur.getRight() && player.getY() > cur.getBottom()&& player.getY() < cur.getTop() ) { 
		    mapX = n;
		    mapY = i;
		    break;
		}
	    }
	}
	System.out.println(mapX);
	System.out.println(mapY);
	Tile right;
	Tile left;
	Tile top;
	Tile bottom;
	
	//Sets the four directional tiles
	if (mapX!=map[0].length-1) {
	    right = tiles[mapX+1][mapY];
	    //System.out.println(right.isSolid());
	}
	else
	    right = null;
	if (mapX!=0) {
	    left = tiles[mapX-1][mapY];
	    //System.out.println(left.isSolid());
	}
	    else
	    left = null;
	if (mapY!=0)
	    top = tiles[mapX][mapY+1];
	else
	    top = null;
	System.out.println(mapX + " " + (mapY-1));
	if (mapY!=map.length-1)
	    bottom = tiles[mapX][mapY+1];
	else 
	    bottom = null;
	
	//Sets player booleans
	if (right!=null && player.getRight() >= right.getLeft()-1 && right.isSolid()) 
	    player.setMoveRight(false);
	else 
	    player.setMoveRight(true);
	if (left != null && player.getLeft() <= left.getRight()+1 && left.isSolid()) 
	    player.setMoveLeft(false);
	else 
	    player.setMoveLeft(true);
	if (top != null && player.getTop() >= top.getBottom()-1 && top.isSolid() )
	    player.setMoveUp(false);
	else 
	    player.setMoveUp(true);
	if (bottom != null && player.getBottom() <= bottom.getTop()+1 && bottom.isSolid()) 
	    player.setMoveDown(false);
	else 
	    player.setMoveDown(true);
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
	boolean solid = false;
	for(int i = 0; i < mapHeight; i++){
	    x = 0;
	    for(int j = 0; j < mapWidth; j++){
		int type = map[i][j];
		if(type == 0){
		    g.drawImage(sprites[3], x, y, null);
		    sprite = 3;
		    solid = false;
		}
		else if(type == 1){
		    g.drawImage(sprites[0], x, y, null);
		    sprite = 0;
		    solid = true;
		}
		else if(type == 2){
		    g.drawImage(sprites[6], x, y, null);
		    sprite = 6; 
		    solid = true;
		}
		tiles[i][j] = new Tile(x, y, tileSize, sprites[sprite],solid);
		x += tileSize; //move column
            }
	    y += tileSize; //moves over row when 1 is finished (now it should start at 0,0)
	}
    }

    public int[][] getMap(){
	return map;
    }

}
