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

    public Integer takeTurn() {
        //to take turn
        //roll dice
        //measure steps needed for and pass back "steps"
        return 0;
    }
    
    /**
     * Moves the player on the board.
     *
     * @param player The player to move.
     * @param steps  Number of steps to move.
     */
    public void movePlayer(Player player, int steps) {
        // Update player position based on steps
	    playerposition = player.move(steps);
        // handle moving the piece or dealing with what happens when the player lands where they do
    }
    
    /**
     * Checks if the current position has a chute or ladder.
     *
     * @param position The current position of the player.
     * @return True if there is a chute or ladder, false otherwise.
     */
    public boolean checkChuteOrLadder(int position) {
        // Check if the current position has a chute or ladder
	    checkForLadder(playerposition);
		checkForChute(playerposition);
        return false;
    }
	
	private boolean checkForLadder(int position) {
		// check position for chute using ladder hashmap
		return true;
	}
	private boolean checkForChute(int position) {
		// check position for chute using chute hashmap
		return true;
	}
}
