import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

public class Screen extends JPanel implements KeyListener, MouseListener {

    private final BoardGame boardGame;

    public Screen() {
        boardGame = new BoardGame();

        setFocusable(true);
        setLayout(null);
        addKeyListener(this);
        addMouseListener(this);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 600);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        boardGame.draw(g, getWidth(), getHeight());
    }

    public void keyPressed(KeyEvent e) {
        repaint();
    }

    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}

    public void mousePressed(MouseEvent e) {
        boardGame.processClick(e.getX(), e.getY(), this);
        repaint();
    }

    public void mouseReleased(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}
