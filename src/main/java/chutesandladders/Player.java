package chutesandladders;


public class Player {
    private String name;
    private int score;
    private int currentPosition;
    private static int playercount;
    private Die myDie;
    
    /**
     * Initializes a new player with the given name.
     *
     * @param name The name of the player.
     */
    public Player(String name) {
        playercount += 1;
        if (name.isEmpty()) {
            name = "Player" + playercount;
        }
        myDie = new Die(6, 6);
        this.name = name;
        this.currentPosition = 0;
        this.score = 0;
    }
    
    /**
     * Moves the player a specified number of steps.
     *
     * @param steps The number of steps to move.
     */
    public int move(int steps) {
        // Update currentPosition based on steps
        this.currentPosition += steps;
        return this.currentPosition;
    }

    public Integer takeTurn() {
        Integer steps = 0;
        //roll dice
        myDie.roll();
        steps = myDie.getSideUp();
        return steps;
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
