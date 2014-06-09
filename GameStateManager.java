package GameState;

import java.util.ArrayList;

public class GameStateManager {
	private ArrayList<GameState> gameStates;
	private int currentState;
	public static final int MENU = 0;
	public static final int LEVEL1 = 1;

	public GameStateManager() {
		gameStates = new ArrayList<GameState>();
		gameStates.add(new MenuState(this));
		currentState = MENU;
	}

	public void setState(int state){
		currentState = state;
		gameStates.get(currentState).init();
	}

	public void update() {
		gameStates.get(currentState).update();
	}

	public void draw(java.awt.Graphics2D g) {
		gameStates.get(currentState).draw();
	}

	public void keyPressed(int k) {
		gameStates.get(currentState).keyPressed();
	}

	public void keyReleased(int k) {
		gameStates.get(currentState).keyReleased();
	}

}