/**
 * Player class used to play the game "Code Climbers" (Chutes & Ladders")
 * CPSC 224, Spring 2024
 * Final Project
 * Sources: N/A
 *
 * @author William Garlington, Steve Deibert, Manny Uzoma
 * @version 1.0
 */
package chutesandladders;

import java.awt.*;

public class Player {
	private String name;
	public int currentPosition;
	private static int playercount;
	private static Integer playerID;
	private Color myColor;
	
	/**
	 * Initializes a new player with the given name.
	 *
	 * @param name The name of the player.
	 */
	public Player(String name) {
		playercount += 1;
		if (name.isEmpty()) {
			name = "Player" + playercount;
		}
		this.name = name;
		this.currentPosition = 1;
		playerID = playercount;
		assignColor();
	}
	
	// Getter and Setter methods
	
	//get name
	public String getPlayerName() {
		return name;
	}
	
	public void setPlayerName(String input) {
		name = input;
	}
	
	//get ID
	public Integer getPlayerID() {
		return playerID;
	}

	public void resetPlayerID() {
		playercount = 0;
	}
	
	public Color getColor() {
		return myColor;
	}
	
	private void assignColor() {
		switch (playercount) {
			case 1:
				myColor = Color.RED;
				break;
			case 2:
				myColor = Color.green;
				break;
			case 3:
				myColor = Color.MAGENTA;
				break;
			case 4:
				myColor = Color.getHSBColor(0.55f, 0.7f, 0.8f);
				break;
			default:
				myColor = Color.LIGHT_GRAY;
				break;
		}
	}
	
	
	/**
	 * Gets the current position of the player on the board.
	 *
	 * @return The current position of the player.
	 */
	public int getCurrentPosition() {
		return currentPosition;
	}
	
	/**
	 * Sets the current position of the player on the board.
	 *
	 * @param inputPosition The new position to set.
	 */
	public void setCurrentPosition(int inputPosition) {
		if (inputPosition >= 100) {
			currentPosition = 100;
			return;
		}
		currentPosition = inputPosition;
	}
}
