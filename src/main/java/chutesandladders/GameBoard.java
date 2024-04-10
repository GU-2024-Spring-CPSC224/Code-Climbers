/**
 * GameBoard class used to play the game "Code Climbers" (Chutes & Ladders")
 * CPSC 224, Spring 2024
 * Final Project
 * Sources: N/A
 * 
 * @author William Garlington, Steve Deibert, Manny Uzoma
 * @version 1.0
 */
package chutesandladders;
import javax.swing.JPanel;
import java.awt.*;
import java.util.HashMap;
import java.util.Random;


public class GameBoard {
	// Define board dimensions, chutes, ladders, etc.
    Integer[] boardArray = new Integer[100];
    ///array of 100 zeros
	
	// Hashmap for ladders, key, value int pair of start, end
	static HashMap<Integer, Integer> ladders;
	
	// Hashmap for chutes, key, value int pair of start, end
	static HashMap<Integer, Integer> chutes;
	
	static int playerposition;
	
	public GameBoard() {
		chutes = new HashMap<>();
		ladders = new HashMap<>();
		generateChutesAndLadders();
	}
	
	private void generateChutesAndLadders() {
		Random random = new Random();
		int chuteLadderNum = 5;
		// Generate chutes
		for (int i = 0; i < chuteLadderNum; i++) {
			int start = random.nextInt(94) + 6;  // Start from 6 to 99 to ensure space for chutes
			int end = start - (random.nextInt(5) + 1) * 10;  // End is 5 to 15 steps below start
			if (end < 1) {
				end = 1;
			}
			chutes.put(start, end);
		}

		// Generate ladders
		for (int i = 0; i < chuteLadderNum; i++) {
			int start = random.nextInt(90) + 1;  // Start from 1 to 90 to ensure space for ladders
			int end = start + (random.nextInt(20) + 10);  // End is 10 to 30 steps above start
			if (end > 100) {
				end = 100;
			}
			ladders.put(start, end);
		}
	}
	
	/// ### PLAYER MOVEMENT FUNCTIONS
	
	public static int checkChuteOrLadder(int position) {
		checkForLadder(position);
		checkForChute(position);
		return playerposition;
	}
	
	private static void checkForLadder(int position) {
		if (ladders.containsKey(position)){
			playerposition = ladders.get(position);
			checkChuteOrLadder(playerposition);
		}
	}
	
	private static void checkForChute(int position) {
		if (chutes.containsKey(position)){
			playerposition = chutes.get(position);
			checkChuteOrLadder(playerposition);
		}
	}

	/// ### GETTERS AND SETTERS

	public int getPlayerPosition() {
		return playerposition;
	}
	
	public void setPlayerPosition(int position) {
		playerposition = position;
	}
}
