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
		Interface UI = new Interface(board);
		Player player1 = new Player("Player 1");
		Player player2 = new Player("Player 2");
		
		// Add board and other Swing components to frame
		
		// Example function calls to demonstrate dependencies
		board.movePlayer(player1, 3);
		board.movePlayer(player2, 4);

		// Check for chutes or ladders after moving players
		int position1 = player1.getCurrentPosition();
		int position2 = player2.getCurrentPosition();
		board.checkChuteOrLadder(position1);
		board.checkChuteOrLadder(position2);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}
