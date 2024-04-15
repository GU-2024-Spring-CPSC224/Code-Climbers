/**
 * Player class used to play the game "Code Climbers" (Chutes & Ladders")
 * CPSC 224, Spring 2024
 * Final Project
 * Sources: N/A
 * 
 * @author William Garlington, Steve Deibert, Manny Uzoma
 * @version 1.0
 */
package chutesandladders;

public class Player {
    private String name;
	public int currentPosition;
    private static int playercount;
    private final Integer playerID;
    private static final Die die = new Die(6, 6);
    
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
        this.name = name;
        this.currentPosition = 1;
	    playerID = playercount;
    }
    
    /**
     * Moves the player a specified number of steps.
     *
     * @param steps The number of steps to move.
     */
    public void move(int steps) {
        // Update currentPosition based on steps
        this.currentPosition += steps;
        currentPosition = GameBoard.checkChuteOrLadder(this.currentPosition);
    }

    public void takeTurn() {
        int steps;
        //roll dice
        die.roll();
        steps = die.getSideUp();
        move(steps);
    }
    
    // Getter and Setter methods

    //get name
    public String getPlayerName() {
        return name;
    }

    public void setPlayerName(String input) {
        name = input;
    }

    //get ID
    public Integer getPlayerID() {
        return playerID;
    }


    
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
     * @param inputPosition The new position to set.
     */
    public void setCurrentPosition(int inputPosition) {
        if (inputPosition >= 100) {
            currentPosition = 100;
            return;
        }
        currentPosition = inputPosition;
    }
}
