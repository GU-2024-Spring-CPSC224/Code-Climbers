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
        return new Dimension(800, 800);  // Set the preferred size of the panel
    }
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// Draw the game board using Swing graphics
		g.setColor(Color.BLACK);
        drawTileBox(g);
		// Draw chutes
		g.setColor(Color.RED);
		paintChutesAndLadders(g, GameBoard.chutes);
		
		// Draw ladders
		g.setColor(Color.GREEN);
		paintChutesAndLadders(g, GameBoard.ladders);
	}
	
    private void drawTileBox(Graphics g) {
        g.fillRect(10, 10, 500, 500);
        g.setColor(Color.WHITE);
        int x1 = 10;
        int y1 = 60;
        int x2 = 510;
        int y2 = 60;
        for (int i = 0; i < 9; i++) {
            g.drawLine(x1, y1, x2, y2);
            y1 += 50;
            y2 += 50;
        }
        x1 = 60;
        x2 = 60;
        y1 = 10;
        y2 = 510;
        for (int i = 0; i < 9; i++) {
            g.drawLine(x1, y1, x2, y2);
            x1 += 50;
            x2 += 50;
        }
		
		for (int i = 0; i < 100; i++) {
			int startX = getXPosition(i);
			int startY = getYPosition(i);
		}

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
		return ((position % 10) * 50) + 35;  // Assuming each square is 50 pixels wide + 35 from the wall and center tile
	}
	
	private int getYPosition(int position) {
		return (500 - (position / 10) * 50) + 35;  // Assuming each square is 50 pixels high + 35 for wall and center
	}
}
