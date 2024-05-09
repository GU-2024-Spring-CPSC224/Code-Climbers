package chutesandladders;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ChutesAndLaddersTest {
    private GameBoard board;
    private Player player;
    private Control gameControl;
    private List<Player> players;

    @BeforeEach
    void setUp() {
        board = new GameBoard();
        players = new ArrayList<>(); // Corrected the shadowing issue
        player = new Player("Test Player");
        players.add(player);
        gameControl = new Control(players);
        // Assume all chutes and ladders are predefined in GameBoard's constructor
    }

    @Test
    void playerStartsAtPositionOne() {
        Assertions.assertEquals(1, player.getCurrentPosition(), "Player should start at position 1.");
    }

    @Test
    void dieRollsWithinValidRange() {
        int roll = board.rollDice();
        Assertions.assertTrue(roll >= 1 && roll <= 6, "Die roll should be between 1 and 6.");
    }


    @Test
    void noMoveBeyondBoardLimit() {
        player.setCurrentPosition(98); // Set the player close to the end
        board.die1 = new Die(6, 6); // Force the die to roll 6
        int[] moveResults = board.movePlayer(player.getCurrentPosition());
        Assertions.assertEquals(98, player.getCurrentPosition(), "Player should not move if roll exceeds board limit.");
    }
}
