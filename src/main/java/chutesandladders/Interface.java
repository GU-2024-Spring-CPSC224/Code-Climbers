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
import javax.swing.JButton;
import java.awt.*;
import java.util.HashMap;




public class Interface extends JPanel {
	GameBoard board;
	Control control;
	
	static HashMap<Integer, Point> tileCoordinates = new HashMap<>();
	
	public Interface(GameBoard inputBoard, Control inControl) {
		this.board = inputBoard;
		this.control = inControl;
		// HashMap to store center coordinates of each tile
		HashMap<Integer, Point> tileCoordinates = Interface.tileCoordinates;  // Get tile coordinates
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(900, 570);  // Set the preferred size of the panel
	}
	
	@Override
	// Draw the game board using Swing graphics
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// Draw grid
		g.setColor(Color.BLACK);
		drawTileGrid(g);
		
		// Draw border around the board
		g.drawRect(35, 35, 10 * 50, 10 * 50);

		//draw box for dice
		JButton rollButton = new JButton("ROLL");
		rollButton.setVisible(false);
		g.setColor(Color.BLACK);
		drawDiceBox(g, rollButton);
		this.add(rollButton);
		
		// Draw chutes
		g.setColor(Color.RED);
		paintChutesAndLadders(g, GameBoard.chutes);
		
		// Draw ladders
		g.setColor(Color.GREEN);
		paintChutesAndLadders(g, GameBoard.ladders);
	}
	
	public void drawTileGrid(Graphics g) {
		
		int startX = 35;  // Initial X position
		int startY = 35;  // Initial Y position
		int cellSize = 50;  // Size of each cell
		FontMetrics fm = g.getFontMetrics();  // Get font metrics to a center text
		
		for (int i = 1; i <= 100; i++) {
			// Calculate the position based on the current number
			int row = 9 - (i - 1) / 10;  // Reverse the row order
			int col = (row % 2 == 0) ? ((100 - i) % 10) : (9 - ((100 - i) % 10));  // Column number
			
			int x = startX + col * cellSize;
			int y = startY + row * cellSize;
			
			// Calculate center position for text
			int textX = x + (cellSize - fm.stringWidth(Integer.toString(i))) / 2;
			int textY = y + ((cellSize - fm.getHeight()) / 2) + fm.getAscent();
			
			tileCoordinates.put(i, new Point((x + 25), (y + 25)));
			
			// Draw the number
			g.drawString(Integer.toString(i), textX, textY);
			
			// Draw vertical lines to separate cells
			if (col < 9) {
				g.drawLine(x + cellSize, y, x + cellSize, y + cellSize);
			}
			
			// Draw horizontal lines to separate rows
			if (row < 9) {
				g.drawLine(x, y + cellSize, x + cellSize, y + cellSize);
			}
		}
	}

	private void drawDiceBox(Graphics g, JButton button) {
		int centerX = 670;
		int centerY = 400;
		button.setBounds(centerX - 50, centerY + 75, 100, 50);
		button.setVisible(true);
		g.drawLine(centerX - 75, centerY - 75, centerX + 75, centerY - 75); //bottom line
		g.drawLine(centerX - 75, centerY + 75, centerX + 75, centerY + 75); //top line
		g.drawLine(centerX - 75, centerY - 75, centerX - 75, centerY + 75); //left line
		g.drawLine(centerX + 75, centerY - 75, centerX + 75, centerY + 75); //right line

		button.addActionListener(event -> control.playTurn());
	}

	private void paintChutesAndLadders(Graphics g, HashMap<Integer, Integer> chutes) {
		for (HashMap.Entry<Integer, Integer> entry : chutes.entrySet()) {
			Point start = tileCoordinates.get(entry.getKey());
			Point end = tileCoordinates.get(entry.getValue());
			
			g.drawLine((int) start.getX(), (int) start.getY(), (int) end.getX(), (int) end.getY());
		}
	}

	public void renderPlayers(int position) {
		
		tileCoordinates.get(position);
	}
}
