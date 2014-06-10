import java.awt.image.*;
import java.awt.*;

import javax.imageio.ImageIO;

public class Background{
	private BufferedImage image;
	private Game game = new Game();

	private double x;
	private double y;
	private double dx;
	private double dy;

	private double speed; // how fast the background moves

	public Background(String s, double ms) {
		try {
			image = ImageIO.read(getClass().getResourceAsStream(s));
			speed = ms;
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	public void setPosition(double x, double y) {
		this.x = (x * speed) % (game.WIDTH/2);
		this.y = (y * speed) % (game.HEIGHT/2);
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
			g.drawImage(image, (int)x + game.WIDTH/2, (int)y, null); // to make automatically scroll;
		}
		if (x > 0) {
			g.drawImage(image, (int)x - game.WIDTH/2, (int)y, null); // to make automatically scroll;
		}
	}

}

