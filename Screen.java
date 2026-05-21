import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;


//If you implement buttons, import those classes, too.




// If you use mouse listening, add that interface, too.
public class Screen extends JPanel implements KeyListener{


	// instance variables
	
	GreenAppleDeck greenDeck;
	RedAppleDeck redDeck;


	public Screen(){

		greenDeck = new GreenAppleDeck();
		redDeck = new RedAppleDeck();

        setFocusable(true); 
		setLayout(null);
		// add Key listener
		addKeyListener(this);
	}


	@Override
	public Dimension getPreferredSize() {
		//Sets the size of the panel
        	return new Dimension(800,600);
	}
	
	@Override
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
		g.drawImage(redDeck.drawCard().getImage(), 100, 100, null);
		g.drawImage(greenDeck.drawCard().getImage(), 200, 100, null);
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


}




