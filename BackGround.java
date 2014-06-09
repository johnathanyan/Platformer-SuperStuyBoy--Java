package TileMap;

import java.awt.image.*;
import java.awt.*;
import javax.imageio.ImageIO;
import GameEngine.Game;

public class BackGround {
	private BufferedImage image;

	private double x;
	private double y;
	private double dx;
	private double dy;

	private double moveScale; // how fast the background moves

	public BackGround(String s, double ms) {
		try {
			image = ImageIO.read(getClass().getResourceAsStream(s));
			moveScale = ms;
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	public void setPosition(double x, double y) {
		this.x = (x * moveScale) % (GamePanel.WIDTH/2);
		this.y = (y * moveScale) % (GamePanel.HEIGHT/2);
	}

	public void bgVectors(double dx, double dy) {
		this.dx = dx;
		this.dy = dy;
	}

	public void update() {
		x += dx;
		y += dy;
	}

	public void draw(Graphics2D g) {
		g.drawImage(image, (int)x, (int)y, null);
		if (x < 0) {
			g.drawImage(image, (int)x + GamePanel.WIDTH/2, (int)y, null); // to make automatically scroll;
		}
		if (x > 0) {
			g.drawImage(image, (int)x - GamePanel.WIDTH/2, (int)y, null); // to make automatically scroll;
		}
	}

}

