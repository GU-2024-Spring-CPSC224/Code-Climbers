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

public class ChutesAndLadders {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Chutes and Ladders");
		GameBoard board = new GameBoard();
		Player player1 = new Player("Player 1");
		Player player2 = new Player("Player 2");

		createUI(board);
		
		player1.takeTurn();
		
		// Add board and other Swing components to frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	private static void createUI(GameBoard board) {
		Interface UI = new Interface(board);
		JFrame frame = new JFrame("Chutes and Ladders");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(UI);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
