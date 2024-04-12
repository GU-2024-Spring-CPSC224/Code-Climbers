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

import javax.swing.JFrame;
import java.util.ArrayList;
import java.util.List;

public class ChutesAndLadders {
	public static void main(String[] args) {
		Player player1 = new Player("Player 1");
		Player player2 = new Player("Player 2");
		List<Player> playerList = new ArrayList<>();
		playerList.add(player1);

		Control control = new Control(playerList);
        createUI(control.getBoard(), control);
		control.executeGame();
	}


	public void createPlayers(List<Player> playerList) {

	}

	private static void createUI(GameBoard board, Control control) {
        Interface UI = new Interface(board, control);
        JFrame frame = new JFrame("Chutes and Ladders");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(UI);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
