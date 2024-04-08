package chutesandladders;
import javax.swing.JPanel;
import java.awt.*;
import java.util.HashMap;


public class GameBoard extends JPanel {
	// Define board dimensions, chutes, ladders, etc.
	
	// Hashmap for ladders, key, value int pair of start, end
	HashMap<Integer, Integer> ladders;
	
	// Hashmap for chutes, key, value int pair of start, end
	HashMap<Integer, Integer> chutes;
	
	int playerposition;
	
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
		checkChuteOrLadder(playerposition);  // Check for chutes or ladders after moving
	}
	
	
	/**
	 * Checks if the current position has a chute or ladder.
	 *
	 * @param position The current position of the player.
	 */
	public void checkChuteOrLadder(int position) {
		if (!checkForLadder(position)) {
			checkForChute(position);
		}
	}
	
	private boolean checkForLadder(int position) {
		if (ladders.containsKey(position)) {
			playerposition = ladders.get(position);  // Update player position to ladder end
			return true;
		}
		return false;
	}
	
	private void checkForChute(int position) {
		if (chutes.containsKey(position)) {
			playerposition = chutes.get(position);  // Update player position to chute end
		}
	}
}
