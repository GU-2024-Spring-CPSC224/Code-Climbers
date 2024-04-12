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
import java.util.ArrayList;
import java.util.List;

public class Control {
    private GameBoard board = new GameBoard();
    private List<Player> playerList;
    private Player activePlayer;

    public Control(List<Player> inList) {
        playerList = inList;        
    }

    public void executeGame() {
        startGame();
    }

    public void startGame() {
        System.out.println(board.chutes);
        System.out.println(board.ladders);
        createUI(board);
        playGame();
    }

    public void playGame() {
        boolean gameOver = false;
        activePlayer = playerList.get(0);
    }
      
    public void endGame() {
          
    }

    public void playTurn() {
        activePlayer.setCurrentPosition(board.movePlayer(activePlayer.getCurrentPosition()));
        System.out.println(playerList.get(0).getCurrentPosition());
    }

    public GameBoard getBoard() {
        return board;
    }

    private void createUI(GameBoard board) {
        Interface UI = new Interface(board, this);
        JFrame frame = new JFrame("Chutes and Ladders");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(UI);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    
    
    
}
