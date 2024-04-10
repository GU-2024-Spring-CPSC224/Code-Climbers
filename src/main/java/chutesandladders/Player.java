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
	private int currentPosition;
    private static int playercount;
    private Integer playerID;
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
        playerID = playercount;
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

    //get name
    public String getPlayerName() {
        return name;
    }

    public void setPlayerName(String input) {
        name = input;
        return;
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
     * @param currentPosition The new position to set.
     */
    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }
}
