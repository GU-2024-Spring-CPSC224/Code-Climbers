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

    public Control(List<Player> inList) {
        playerList = inList;        
    }

    public void executeGame() {
        startGame();
    }

    public void startGame() {
        System.out.println(board.chutes);
        System.out.println(board.ladders);
    
    }

    public void playGame() {
        boolean gameOver = false;
        while (!gameOver) {

        }
    }
      
    public void endGame() {
          
    }

    public void playTurn() {
        playerList.get(0).setCurrentPosition(board.movePlayer(playerList.get(0).getCurrentPosition()));
        System.out.println(playerList.get(0).getCurrentPosition());
    }

    public GameBoard getBoard() {
        return board;
    }

    
    
    
}
