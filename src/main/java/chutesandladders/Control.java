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
        createUI(board);
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

    private void playTurn() {

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
