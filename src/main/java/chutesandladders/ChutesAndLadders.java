/**
 * ChutesAndLadders class used to play the game "Code Climbers" (Chutes & Ladders")
 * CPSC 224, Spring 2024
 * Final Project
 * Sources: N/A
 * 
 * @author William Garlington, Steve Deibert, Manny Uzoma
 * @version 1.0
 */
package chutesandladders;

//    Purpose: Main class to start the game.
//    Create instances of GameBoard and manage game flow.
//    Implement Swing components for player input and game controls.

import java.util.ArrayList;
import java.util.List;

public class ChutesAndLadders {
	public static void main(String[] args) {
		Player player1 = new Player("Player 1");
		List<Player> playerList = new ArrayList<>();
		playerList.add(player1);

		Control control = new Control(playerList);
		control.executeGame();
	}
	public void createPlayers(List<Player> playerList) {

	}
}
