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

    @BeforeEach
    void setUp() {
        board = new GameBoard();
        List<Player> players = new ArrayList<>();
        player = new Player("Test Player");
        players.add(player);
        gameControl = new Control(players);
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
    void movePlayerPositionUpdatesCorrectly() {
        int initialPosition = player.getCurrentPosition();
        int roll = board.rollDice();
        int[] moveResults = board.movePlayer(initialPosition);
        Assertions.assertEquals(initialPosition + roll, moveResults[0], "Player position should update correctly based on die roll.");
    }

    @Test
    void landingOnChuteMovesPlayerDown() {
        // Assume there's a chute at position 8 going to position 4
        GameBoard.chutes.put(8, 4);
        player.setCurrentPosition(7); // Set the player just before the chute
        board.movePlayer(player.getCurrentPosition()); // Move player, which should land on the chute
        Assertions.assertEquals(4, player.getCurrentPosition(), "Player should move down to position 4 after landing on a chute.");
    }

    @Test
    void landingOnLadderMovesPlayerUp() {
        // Assume there's a ladder at position 2 going to position 15
        GameBoard.ladders.put(2, 15);
        player.setCurrentPosition(1); // Set the player just before the ladder
        board.movePlayer(player.getCurrentPosition()); // Move player, which should land on the ladder
        Assertions.assertEquals(15, player.getCurrentPosition(), "Player should move up to position 15 after landing on a ladder.");
    }

     @Test
    void activePlayerCyclesCorrectly() {
        List<Player> players = new ArrayList<>();
        players.add(new Player("Player 1"));
        players.add(new Player("Player 2"));
        Control localControl = new Control(players);
        localControl.playTurn(); // First player's turn
        Assertions.assertEquals("Player 2", localControl.getActivePlayer().getPlayerName(), "Active player should be 'Player 2' after first turn.");
        localControl.playTurn(); // Second player's turn
        Assertions.assertEquals("Player 1", localControl.getActivePlayer().getPlayerName(), "Active player should cycle back to 'Player 1' after second turn.");
    }

    @Test
    void noMoveBeyondBoardLimit() {
        player.setCurrentPosition(98); // Set the player close to the end
        board.die1 = new Die(6, 6); // Force the die to roll 6
        board.movePlayer(player.getCurrentPosition()); // Move player, trying to exceed position 100
        Assertions.assertEquals(98, player.getCurrentPosition(), "Player should not move if roll exceeds board limit.");
    }
}
