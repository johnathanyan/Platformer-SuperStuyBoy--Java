import java.io.*;
import java.util.*;
import java.awt.image.BufferedImage;
import java.awt.*;
import javax.imageio.*;

public class TileMap{
    private int tileSize, mapWidth, mapHeight;
    private int x, y;
    private int[][] map, tiles;
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

    public void divideSheet(BufferedImage sheet){
	int count = 0;
	int w = sheet.getWidth();
	int h = sheet.getHeight();
	for(int m = 0; m < (w - tileSize); m += tileSize){
	    for(int n = 0; n < (h - tileSize); n += tileSize){
		System.out.println(m + ", " + n);
		sprites[count] = sheet.getSubimage(m, n, tileSize, tileSize);
		count++;
	    }
	}
    }
		
	
	

    public void drawTiles(Graphics2D g){
	x = 0;
	y = 0;
	for(int i = 0; i < mapHeight; i++){
	    x = 0;
	    for(int j = 0; j < mapWidth; j++){
		if(map[i][j] == 1){
		    g.drawImage(sprites[0], x, y, null);
		}else{
		    g.drawImage(sprites[1], x, y, null);
		}
		x += tileSize; //move column
            }
	    y += tileSize; //moves over row when 1 is finished (now it should start at 0,0)
	}
    }

    public int[][] getMap(){
	return map;
    }

}
	
