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

import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;

public class Control {
	private final GameBoard board = new GameBoard();
	private Interface UI;
	private final List<Player> playerList;
	private Player activePlayer;
	private int activeNumber  = 0;
	private final JFrame frame; // Initialize the JFrame
	private final int playerNum;
	boolean gameOver = false;
	
	public Control(List<Player> inList) {
		playerList = inList;
		frame = new JFrame("Chutes and Ladders");
		frame.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				if (UI != null) {
					UI.renderUpdate(); // Repaint UI components on resize
				}
			}
		});
		playerNum = setPlayerNum();
	}
	
	public void executeGame() {
		startGame();
	}
	
	public void startGame() {
		createUI(frame); // Pass the JFrame instance to the createUI method
		playGame();
	}
	
	public void playGame() {
		activePlayer = playerList.get(activeNumber);
	}
	
	public void playTurn() {
        int[] inVals = board.movePlayer(activePlayer.getCurrentPosition());
		activePlayer.setCurrentPosition(inVals[0]);
        UI.updateRollValue(inVals[1]);
		UI.renderUpdate();
		gameOverCheck();
		advanceActivePlayer();
	}
	
	private void gameOverCheck() {
        for (Player player : playerList) {
            int position = player.getCurrentPosition();
	        if (position == 100) {
		        gameOver = true;
                int option = JOptionPane.showConfirmDialog(frame, "Game over! Do you want to restart?", "Game Over", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
					player.resetPlayerID(); // I know this is terrible and i have no excuses
                    restartGame();
                } else {
                    UI.close();
                }
                break;
	        }
        }
    }
	
	private void restartGame() {
        frame.dispose(); // Close the current frame
        // Restart the program by relaunching the main method
        SwingUtilities.invokeLater(() -> {
            PlayerSelectionGUI playerSelectionGUI = new PlayerSelectionGUI();
			playerSelectionGUI.setVisible(true);
        });
    }
    
    private void closeGame() {
        frame.dispose(); // Close the current frame
        System.exit(0); // Exit the program
    }
	
	private void createUI(JFrame frame) {
		UI = new Interface(this, frame);
		frame.add(UI);
		frame.pack();
	}
	
	List<Player> getPlayerList() {
		return playerList;
	}

    public Player getActivePlayer() {
        return activePlayer;
    }
	
	private void advanceActivePlayer() {
		if ((playerNum - 1) == activeNumber) {
			//cur player is last in the list
			activeNumber = 0;
		} else {
			activeNumber++;
		}
		activePlayer = playerList.get(activeNumber);
	}
	
	private int setPlayerNum() {
		int playerCount = 0;
		
		for (Player player : playerList) {
			playerCount++;
		}
		return playerCount;
	}
}