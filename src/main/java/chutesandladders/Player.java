package chutesandladders;
public class Player {
    private String name;
    private int currentPosition;
    
    /**
     * Initializes a new player with the given name.
     *
     * @param name The name of the player.
     */
    public Player(String name) {
        this.name = name;
        this.currentPosition = 0;
    }
    
    /**
     * Moves the player a specified number of steps.
     *
     * @param steps The number of steps to move.
     */
    public void move(int steps) {
        // Update currentPosition based on steps
    }
    
    // Getter and Setter methods
    
    /**
     * Gets the current position of the player on the board.
     *
     * @return The current position of the player.
     */
    public int getCurrentPosition() {
        return currentPosition;
    }
    
    /**
     * Sets the current position of the player on the board.
     *
     * @param currentPosition The new position to set.
     */
    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }
}
