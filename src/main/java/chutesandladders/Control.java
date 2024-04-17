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
import java.awt.*;
import java.util.List;

public class Control {
    private final GameBoard board = new GameBoard();
    private Interface UI;
    private final List<Player> playerList;
    private Player activePlayer;

    public Control(List<Player> inList) {
        playerList = inList;        
    }

    public void executeGame() {
        startGame();
    }

    public void startGame() {
        System.out.println(GameBoard.chutes);
        System.out.println(GameBoard.ladders);
        createUI(board);
        playGame();
    }

    public void playGame() {
        boolean gameOver = false;
        activePlayer = playerList.get(0);
    }

    public void endGame() {
        UI.close(frame);
    }

    public void playTurn() {
        activePlayer.setCurrentPosition(board.movePlayer(activePlayer.getCurrentPosition()));
        UI.repaint();
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

    public GameBoard getBoard() {
        return board;
    }

    private void createUI(GameBoard board) {
        UI = new Interface(board, this);
	    JFrame frame = new JFrame("Chutes and Ladders");
        frame.setMinimumSize(new Dimension(800, 600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(UI);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    List<Player> getPlayerList() {
        return playerList;
    }
}
