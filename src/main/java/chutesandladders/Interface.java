/**
 * Interface class used to play the game "Code Climbers" (Chutes & Ladders")
 * CPSC 224, Spring 2024
 * Final Project
 * Sources: N/A
 *
 * @author William Garlington, Steve Deibert
 * @version 1.0
 */
package chutesandladders;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Interface extends JPanel {
	JButton rollButton; // Declare roll button as a class-level field
	private JPanel mainPanel;
	private final Control control;
	private final HashMap<Integer, Point> tileCoordinates = new HashMap<>();
	
	public Interface(Control control, JFrame frame) {
		this.control = control;
		// Initialize components and configure frame if needed
		initializeFrame(frame);
		initializeComponents(frame);
		renderBoard(); // Call renderBoard to add the game board panel
		frame.setVisible(true); // Set frame visibility to true
	}
	
	private void initializeFrame(JFrame frame) {
		frame.setMinimumSize(new Dimension(800, 700));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000, 700); // Set initial size to a larger dimension
		frame.setResizable(true); // Allow frame to be resizable
		frame.setLocationRelativeTo(null); // Center the frame on the screen
		frame.setVisible(true);
	}
	
	private void initializeComponents(JFrame frame) {
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		// Initialize the roll button
		rollButton = new JButton("ROLL");
		rollButton.addActionListener(event -> control.playTurn());
		
		// Add the roll button to a panel at the bottom
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(rollButton);

		// Add panel for scoreboard
		JPanel scoreBoardPanel = new JPanel();
		
		// Add the main panel and button panel to the frame
		frame.add(mainPanel, BorderLayout.CENTER);
		frame.add(scoreBoardPanel, BorderLayout.EAST);
		frame.add(buttonPanel, BorderLayout.SOUTH);

	}
	
	public void renderBoard() {
		JPanel boardPanel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				drawBoard(g);
				drawPlayers(g, control.getPlayerList());
				drawScorebaord(g, control.getPlayerList());
				// drawDiceBox(rollButton, g);
			}
		};
		mainPanel.add(boardPanel, BorderLayout.CENTER);
	}
	
	private void drawBoard(Graphics g) {
		g.setColor(Color.BLACK);
		
		int startX = 35; // Initial X position
		int startY = 35; // Initial Y position
		int cellSize = 50; // Size of each cell
		FontMetrics fm = g.getFontMetrics(); // Get font metrics to center the text
		
		for (int i = 1; i <= 100; i++) {
			// Calculate the position based on the current number
			int row = 9 - (i - 1) / 10; // Reverse the row order
			int col = (row % 2 == 0) ? ((100 - i) % 10) : (9 - ((100 - i) % 10)); // Column number
			
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
		
		// Draw border around the board
		g.drawRect(35, 35, 10 * 50, 10 * 50);
		
		// Draw chutes
		g.setColor(Color.RED);
		paintChutesAndLadders(GameBoard.chutes, g);
		
		// Draw ladders
		g.setColor(Color.GREEN);
		paintChutesAndLadders(GameBoard.ladders, g);
	}
	
	private void drawDiceBox(JButton button, Graphics g) {
		g.setColor(Color.BLACK);
		int centerX = 670;
		int centerY = 400;
		button.setBounds(centerX - 50, centerY + 75, 100, 50);
		button.setVisible(true);
		g.drawLine(centerX - 75, centerY - 75, centerX + 75, centerY - 75); //bottom line
		g.drawLine(centerX - 75, centerY + 75, centerX + 75, centerY + 75); //top line
		g.drawLine(centerX - 75, centerY - 75, centerX - 75, centerY + 75); //left line
		g.drawLine(centerX + 75, centerY - 75, centerX + 75, centerY + 75); //right line
		drawBoard(g);
		drawPlayers(g, control.getPlayerList());
	}
	
	private void paintChutesAndLadders(HashMap<Integer, Integer> chuteLadder, Graphics g) {
		for (HashMap.Entry<Integer, Integer> entry : chuteLadder.entrySet()) {
			Point start = tileCoordinates.get(entry.getKey());
			Point end = tileCoordinates.get(entry.getValue());
			
			g.drawLine((int) start.getX(), (int) start.getY(), (int) end.getX(), (int) end.getY());
		}
	}
	
	public void renderPlayers() {
		mainPanel.repaint(); // Repaint the board panel to update player rendering
	}
	
	private void drawPlayers(Graphics g, List<Player> playerList) {
		for (Player player : playerList) {
			int position = player.getCurrentPosition();
			Point playerPosition = tileCoordinates.get(position);
			if (playerPosition != null) {
				// Calculate the center of the tile where the player is located
				int x = (int) playerPosition.getX();
				int y = (int) playerPosition.getY();
				
				// Adjust player position based on the size of the tile
				int playerSize = 20; // Size of the player representation
				int playerX = x - playerSize / 2;
				int playerY = y - playerSize / 2;
				
				// Draw the player as a filled circle
				g.setColor(player.getColor());
				g.fillOval(playerX, playerY, playerSize, playerSize);
			}
		}
	}

	public void renderScoreboard() {
		mainPanel.repaint();
	}

	private void drawScorebaord(Graphics g, List<Player> playerList) {
		Font scoreBoardFont = new Font("Comic Sans MS", Font.BOLD, 20);
		g.setColor(Color.BLACK);
		int xcen = 550;
		int ycen = 35;
		int i = 0;
		g.setFont(scoreBoardFont);
		for (Player player : playerList) {
			int y = ycen + (i * 60);
			char nameChars[] = playerList.get(i).getPlayerName().toCharArray();
			char posChars[] = String.valueOf(playerList.get(i).getCurrentPosition()).toCharArray();
			g.drawRect(xcen, y, 200, 60);
			g.drawChars(nameChars, 0, nameChars.length, xcen + 5, y + 40);
			g.drawChars(posChars, 0, posChars.length, xcen + 150, y + 40);
		}
	}
	
	public void close() {
		System.out.println("WINNER");
		System.exit(0);
	}
}

class PlayerSelectionGUI extends JFrame {
	private final JComboBox<Integer> playerCountComboBox;

    public PlayerSelectionGUI() {
        setTitle("Chutes and Ladders");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame on the screen

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2, 1));

        JPanel playerCountPanel = new JPanel();
        playerCountPanel.add(new JLabel("Select number of players:"));
        playerCountComboBox = new JComboBox<>();
        for (int i = 1; i <= 4; i++) {
            playerCountComboBox.addItem(i);
        }
        playerCountPanel.add(playerCountComboBox);

        JPanel buttonPanel = new JPanel();
	    JButton startButton = new JButton("Start Game");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int playerCount = (int) playerCountComboBox.getSelectedItem();
                List<Player> playerList = new ArrayList<>();
                for (int i = 0; i < playerCount; i++) {
                    playerList.add(new Player("Player " + (i + 1)));
                }
                Control control = new Control(playerList);
                control.executeGame();
                dispose(); // Close the player selection GUI
            }
        });
        buttonPanel.add(startButton);

        mainPanel.add(playerCountPanel);
        mainPanel.add(buttonPanel);

        add(mainPanel);
    }
}