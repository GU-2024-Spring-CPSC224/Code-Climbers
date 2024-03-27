package chutesandladders;

//    Purpose: Main class to start the game.
//    Create instances of GameBoard and manage game flow.
//    Implement Swing components for player input and game controls.

import javax.swing.JFrame;

public class ChutesAndLadders {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Chutes and Ladders");
        GameBoard board = new GameBoard();
        
        // Add board and other Swing components to frame
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
