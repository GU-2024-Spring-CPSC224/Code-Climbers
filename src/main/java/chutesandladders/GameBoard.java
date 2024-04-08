package chutesandladders;
import javax.swing.JPanel;
import java.awt.Graphics;
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
