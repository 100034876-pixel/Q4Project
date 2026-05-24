import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;


//If you implement buttons, import those classes, too.




// If you use mouse listening, add that interface, too.
public class Screen extends JPanel implements KeyListener, MouseListener{


	// instance variables
	
	GreenAppleDeck greenDeck;
	RedAppleDeck redDeck;
	RedAppleCard[][] playerHands;
	GreenAppleCard centerGreenCard;
	boolean cardsRevealed;
	int currentPlayer;

	private final int cardWidth = 120;
	private final int cardHeight = 168;
	private final int revealedCardWidth = 85;
	private final int revealedCardHeight = 118;
	private final int greenCardWidth = 119;
	private final int greenCardHeight = 170;
	private final Color gameBackgroundColor = new Color(255, 220, 220);


	public Screen(){

		greenDeck = new GreenAppleDeck();
		redDeck = new RedAppleDeck();
		playerHands = Card.dealRedHands(redDeck, 4, 7);
		centerGreenCard = greenDeck.drawCard();
		cardsRevealed = false;
		currentPlayer = 0;

        setFocusable(true); 
		setLayout(null);
		// add Key listener
		addKeyListener(this);
		addMouseListener(this);
	}


	@Override
	public Dimension getPreferredSize() {
		//Sets the size of the panel
        	return new Dimension(800,600);
	}
	
	@Override
	public void paintComponent(Graphics g) {
        super.paintComponent(g);

		if (!cardsRevealed) {
			g.setFont(new Font("Arial", Font.BOLD, 32));
			g.drawString("View Player " + (currentPlayer + 1) + " Cards", 250, 300);
			return;
		}

		g.setColor(gameBackgroundColor);
		g.fillRect(0, 0, getWidth(), getHeight());

		int hiddenSlot = 0;
		for (int player = 0; player < playerHands.length; player++) {
			if (player == currentPlayer) {
				drawPlayerHand(g, player, -1);
			} else {
				drawPlayerHand(g, player, hiddenSlot);
				hiddenSlot++;
			}
		}

		if (centerGreenCard != null) {
			centerGreenCard.draw(g, 340, 215, greenCardWidth, greenCardHeight);
		}
	} 

	private void drawPlayerHand(Graphics g, int player, int hiddenSlot) {
		boolean revealed = currentPlayer == player;
		int width = cardWidth;
		int height = cardHeight;

		if (revealed) {
			Card.drawHorizontalHand(g, playerHands[player], 85, 472, revealedCardWidth, revealedCardHeight, true);
		} else if (hiddenSlot == 0) {
			Card.drawHorizontalHand(g, playerHands[player], 265, 10, width, height, false);
		} else if (hiddenSlot == 1) {
			Card.drawVerticalHand(g, playerHands[player], 60, 156, width, height, false);
		} else {
			Card.drawVerticalHand(g, playerHands[player], 620, 156, width, height, false);
		}
	}


	// animate a scene
	public void animate() {
		while(true){
            //pause for .01 second
            try {
                Thread.sleep(10);    // 10 milliseconds
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            

            repaint();
        }
	}


	// interpret key clicks
	public void keyPressed(KeyEvent e){
		
		repaint();
		
	}


	// You must have method signatures for all methods that are
	// part of an interface.
	public void keyReleased(KeyEvent e){}
	public void keyTyped(KeyEvent e){}

	public void mousePressed(MouseEvent e){
		if (cardsRevealed) {
			currentPlayer++;
			if (currentPlayer >= playerHands.length) {
				currentPlayer = 0;
			}
			cardsRevealed = false;
		} else {
			cardsRevealed = true;
		}
		repaint();
	}

	public void mouseReleased(MouseEvent e){}
	public void mouseClicked(MouseEvent e){}
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}


}




