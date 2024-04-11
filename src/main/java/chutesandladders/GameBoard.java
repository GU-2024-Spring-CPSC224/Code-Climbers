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
import java.util.HashMap;
import java.util.Random;

// Define board dimensions, chutes, ladders, etc.
public class GameBoard {
	
	// Hashmap for ladders, key, value int pair of start, end
	static HashMap<Integer, Integer> ladders;
	
	// Hashmap for chutes, key, value int pair of start, end
	static HashMap<Integer, Integer> chutes;
	
	// Stored Player Position used in chute and ladder checks
	static int playerposition;

	Die die1 = new Die(6, 1);
	
	public GameBoard() {
		chutes = new HashMap<>();
		ladders = new HashMap<>();
		generateChutesAndLadders();
		playerposition = 0;
	}
	
	private void generateChutesAndLadders() {
		Random random = new Random();
		int chuteLadderNum = 5;
		// Generate chutes
		for (int i = 0; i < chuteLadderNum; i++) {
			int startRow = (random.nextInt(8) + 2) * 10;  // Start row between 20 and 90
			int start = startRow + random.nextInt(5) + 1;  // Start from current row to 5 steps below
			int end = start - (random.nextInt(5) + 1) * 10;  // End is 5 to 15 steps below start
			if (end < 1) {
				end = 1;
			}
			chutes.put(start, end);
		}


		// Generate ladders
		for (int i = 0; i < chuteLadderNum; i++) {
			int startRow = (random.nextInt(9) + 1) * 10;  // Start row between 10 and 100
			int start = startRow - random.nextInt(5) - 1;  // Start from current row to 5 steps above
			int end = start + (random.nextInt(20) + 10);  // End is 10 to 30 steps above start
			if (end > 100) {
				end = 100;
			}
			ladders.put(start, end);
		}
	}
	
	/// ### PLAYER MOVEMENT FUNCTIONS

	public int rollDice() {
		die1.roll();
		return die1.getSideUp();
	}
	
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
