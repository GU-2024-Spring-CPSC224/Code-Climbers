/**
 * Control class used to play the game "Code Climbers" (Chutes & Ladders")
 * CPSC 224, Spring 2024
 * Final Project
 * Sources: N/A
 *
 * @author William Garlington, Steve Deibert, Manny Uzoma
 * @version 1.0
 */
package chutesandladders;

import javax.swing.JFrame;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;

public class Control {
	private final GameBoard board = new GameBoard();
	private Interface UI;
	private final List<Player> playerList;
	private Player activePlayer;
	private final JFrame frame; // Initialize the JFrame
	boolean gameOver = false;
	
	public Control(List<Player> inList) {
		playerList = inList;
		frame = new JFrame("Chutes and Ladders");
		frame.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				if (UI != null) {
					UI.renderPlayers(); // Repaint UI components on resize
				}
			}
		});
	}
	
	public void executeGame() {
		startGame();
	}
	
	public void startGame() {
		createUI(frame); // Pass the JFrame instance to the createUI method
		playGame();
	}
	
	public void playGame() {
		activePlayer = playerList.get(0);
	}
	
	public void endGame() {
		UI.close();
	}
	
	public void playTurn() {
		activePlayer.setCurrentPosition(board.movePlayer(activePlayer.getCurrentPosition()));
		UI.renderPlayers();
		gameOverCheck();
	}
	
	private void gameOverCheck() {
		for (Player player : playerList) {
			int position = player.getCurrentPosition();
			if (position == 100) {
				gameOver = true;
				endGame();
				break;
			}
		}
	}
	
	private void createUI(JFrame frame) {
		UI = new Interface(this, frame);
		frame.add(UI);
		frame.pack();
	}
	
	List<Player> getPlayerList() {
		return playerList;
	}
}