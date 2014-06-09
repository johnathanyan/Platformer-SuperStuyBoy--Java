package GameState;

import java.awt.*;
import java.awt.event.*;
import TileMap.Background;

public class Menu extends GameState {

	private Background bg;

	private String[] options = {
		"Start",
		"Credits",
		"Exit"
	};

	private Color titleColor;
	private Font titleFont;
	private Font font;
	private int currentChoice;

	public Menu (GameStateManager gsm) {
			this.gsm = gsm;
			currentChoice = 0;

			try{
				bg = new Background("/Background/menu.gif", 1);
				bg.setVector(-0.1, 0);

				titleColor = new Color(128, 0, 0);
				titleFont = new Font("Times New Roman", Font.PLAIN, 32);

				font = new Font("Arial", Font.PLAIN, 12);
			}
			catch(Exception e){
				e.printStackTrace();
			}
	}

	private Background background;

	public abstract void init(){}
	public abstract void update(){
		bg.update();
	}
	public abstract void draw(java.awt.Graphics2D g){
			bg.draw(g);
			g.setColor(titleColor);
			g.setFont(titleFont);
			g.drawString("Super Stuy Boy", 80, 70); // make an automatically centering method
			g.setFont(font);
			for(int i = 0; i < options.length; i++){
					if (i == currentChoice) {
						g.setColor(Color.BLACK);
					}
					else{
						g.setColor(Color.GREY);
					}
					g.drawString(options[i], 145, 140+i*15);
			}
	}

	private void select() {
		if (currentChoice = 0){ // start
			//
		}
		if (currentChoice == 1){ // credits
			//
		}
		if (currentChoice == 2){ // quit
			System.exit(0);
		}
	}

	public abstract void keyPressed(int k){
		if (k == KeyEvent.VK_ENTER){
			select();
		}
		if (k == KeyEvent.VK.DOWN){
			currentChoice++;
			if(currentChoice == options.length){
				currentChoice = 0;
			}
		}
		if (k == KeyEvent.VK.UP){
			currentChoice--;
			if(currentChoice == -1){
				currentChoice = options.length -1;
			}
		}	
	}

	public abstract void update(int k){};

}