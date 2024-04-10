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


public class GameBoard extends JPanel {
	
	// Hashmap for ladders, key, value int pair of start, end
	HashMap<Integer, Integer> ladders;
	
	// Hashmap for chutes, key, value int pair of start, end
	HashMap<Integer, Integer> chutes;
	
	int playerposition;
	
	public GameBoard() {
		this.chutes = new HashMap<>();
		this.ladders = new HashMap<>();
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
	
	@Override
    public Dimension getPreferredSize() {
        return new Dimension(600, 600);  // Set the preferred size of the panel
    }
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// Draw the game board using Swing graphics
		
		// Draw chutes
		g.setColor(Color.RED);
		paintChutesAndLadders(g, chutes);
		
		// Draw ladders
		g.setColor(Color.GREEN);
		paintChutesAndLadders(g, ladders);
	}
	
	private void paintChutesAndLadders(Graphics g, HashMap<Integer, Integer> chutes) {
		for (HashMap.Entry<Integer, Integer> entry : chutes.entrySet()) {
			int startX = getXPosition(entry.getKey());
			int startY = getYPosition(entry.getKey());
			int endX = getXPosition(entry.getValue());
			int endY = getYPosition(entry.getValue());
			g.drawLine(startX, startY, endX, endY);
		}
	}
	
	private int getXPosition(int position) {
		return (position % 10) * 50;  // Assuming each square is 50 pixels wide
	}
	
	private int getYPosition(int position) {
		return 500 - (position / 10) * 50;  // Assuming each square is 50 pixels high
	}
	
	
	/**
	 * Moves the player on the board.
	 *
	 * @param player The player to move.
	 * @param steps  Number of steps to move.
	 */
	public void movePlayer(Player player, int steps) {
		playerposition = player.move(steps);  // Assuming the Player class has a move method
		//if (checkChuteOrLadder(playerposition))
		// Check for chutes or ladders after moving
		//if yes then move player to end point
		if (checkForChute(playerposition)) {
			moveOnChute(playerposition);
		}
		else if (checkForLadder(playerposition)) {
			moveOnLadder(playerposition);
		}
	}
	
	/// ### PPLAYER MOVEMENT FUNCTIONS
	
	/**
	 * Checks if the current position has a chute or ladder.
	 *
	 * @param position The current position of the player.
	 * @return whether position is chute/ladder
	 */
	public boolean checkChuteOrLadder(int position) {
		if (!checkForLadder(position)) {
			checkForChute(position);
		}
		return false;
	}
	
	private boolean checkForLadder(int position) {
		return ladders.containsKey(position);
	}

	private void moveOnLadder(int position) {
		playerposition = ladders.get(position);  // Update player position to the ladder end
	}
	
	private boolean checkForChute(int position) {
		return chutes.containsKey(position);
	}

	private void moveOnChute(int position) {
		playerposition = chutes.get(position);  // Update player position to the chute end
	}

	/// ### GETTERS AND SETTERS

	public int getPlayerPosition() {
		return playerposition;
	}
}
