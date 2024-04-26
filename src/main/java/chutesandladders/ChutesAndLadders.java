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

import javax.swing.*;

public class ChutesAndLadders {
	public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PlayerSelectionGUI playerSelectionGUI = new PlayerSelectionGUI();
            playerSelectionGUI.setVisible(true);
        });
    }
}
