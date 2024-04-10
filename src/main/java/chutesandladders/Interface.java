/**
 * Interface class used to play the game "Code Climbers" (Chutes & Ladders")
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



public class Interface extends JPanel {
    GameBoard board;
    
    public Interface(GameBoard inputBoard) {
        this.board = inputBoard;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 800);  // Set preferred size of the panel
    }
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// Draw the game board using Swing graphics
		g.setColor(Color.BLACK);
        drawTileBox(g);
		// Draw chutes
		g.setColor(Color.RED);
		paintChutesAndLadders(g, board.chutes);
		
		// Draw ladders
		g.setColor(Color.GREEN);
		paintChutesAndLadders(g, board.ladders);
	}
	
    private void drawTileBox(Graphics g) {
        g.fillRect(10, 10, 500, 500);
        g.drawLine(10, 10, 510, 10);
        g.drawLine(10,10, 10, 510);
        g.drawLine(510, 10, 510, 510);
        g.drawLine(10,510,510,510);
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
}
