import java.io.*;

public class TileMap{
    
    private int tileSize, mapWidth, mapHeight;
    private int minx, miny, maxx, maxy, x, y;
    private int[][] map;


    //reads file of ints to determine which sprites to use
    public TileMap(String path, int tileSize){
	this.tileSize = tileSize;
	try{
	    File file = new File(path);
	    Scanner sc = new Scanner(file);
	}catch(Exception e){
	    e.printStackTrace();
	}

	mapWidth = Integer.parseInt(sc.nextLine());
	mapHeight = Integer.parseInt(sc.nextLine());
	map = new int[mapheight][mapWidth];

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

    public void drawTiles(String path){
	//will get proper image to make sprite depending on 
	//the integer read from file in the constructor
    }
}
	
