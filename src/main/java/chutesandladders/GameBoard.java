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
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

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
		
		Set<Integer> usedPositions = new HashSet<>();
		
		// Generate chutes
		for (int i = 0; i < chuteLadderNum; i++) {
			int startRow = (random.nextInt(8) + 2) * 10;  // Start row between 20 and 90
			int start;
			do {
				start = startRow + random.nextInt(5) + 1;  // Start from current row to 5 steps below
			} while (usedPositions.contains(start) || ladders.containsKey(start)); // Check if position is used by chute or ladder
			usedPositions.add(start);
			
			int end;
			do {
				end = start - (random.nextInt(5) + 1) * 10;  // End is 5 to 15 steps below start
			} while (end < 1 || usedPositions.contains(end)); // Check if position is used
			
			chutes.put(start, end);
		}
		
		// Generate ladders
		for (int i = 0; i < chuteLadderNum; i++) {
			int startRow = (random.nextInt(9) + 1) * 10;  // Start row between 10 and 100
			int start;
			do {
				start = startRow - random.nextInt(5) - 1;  // Start from current row to 5 steps above
			} while (usedPositions.contains(start) || chutes.containsKey(start)); // Check if position is used by ladder or chute
			usedPositions.add(start);
			
			int end;
			do {
				end = start + (random.nextInt(20) + 10);  // End is 10 to 30 steps above start
			} while (end > 100 || usedPositions.contains(end)); // Check if position is used
			
			ladders.put(start, end);
		}
	}
	
	/// ### PLAYER MOVEMENT FUNCTIONS
	
	public int[] movePlayer(int curPos) {
		int newPos;
		int roll;
		roll = rollDice();
		curPos += roll;
		newPos = checkChuteOrLadder(curPos);
		int[] posAndVal = {newPos, roll};
		return posAndVal;
	}
	
	public int rollDice() {
		die1.roll();
		return die1.getSideUp();
	}
	
	public static int checkChuteOrLadder(int position) {
		position = checkForLadder(position);
		position = checkForChute(position);
		return position;
	}
	
	private static int checkForLadder(int position) {
		if (ladders.containsKey(position)) {
			position = ladders.get(position);
			position = checkChuteOrLadder(position);
		}
		return position;
	}
	
	private static int checkForChute(int position) {
		if (chutes.containsKey(position)) {
			position = chutes.get(position);
			position = checkChuteOrLadder(position);
		}
		return position;
	}
}