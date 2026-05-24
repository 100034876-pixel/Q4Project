import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Screen extends JPanel implements KeyListener, MouseListener {

    private static final int playerCount = 4;
    private static final int handSize = 7;
    private static final int greenCardsToWin = 8;

    private static final int passToPlayer = 0;
    private static final int chooseRedCard = 1;
    private static final int passToJudge = 2;
    private static final int judgeChoosing = 3;
    private static final int gameOver = 4;

    private final GreenAppleDeck greenDeck;
    private final RedAppleDeck redDeck;
    private final RedAppleCard[][] playerHands;
    private final int[] greenCardsWon;
    private final RedAppleCard[] submissions;
    private final int[] submissionPlayers;
    private final Random random;

    private GreenAppleCard centerGreenCard;
    private int phase;
    private int roundNumber;
    private int judgePlayer;
    private int selectingPlayer;
    private int submissionCount;

    private final int handCardWidth = 85;
    private final int handCardHeight = 118;
    private final int handStartX = 85;
    private final int handY = 455;
    private final int handSpacing = 90;
    private final int submissionWidth = 150;
    private final int submissionHeight = 168;
    private final int submissionStartX = 130;
    private final int submissionY = 362;
    private final int submissionSpacing = 185;
    private final Color gameBackgroundColor = new Color(255, 220, 220);

    public Screen() {
        greenDeck = new GreenAppleDeck();
        redDeck = new RedAppleDeck();
        playerHands = Card.dealRedHands(redDeck, playerCount, handSize);
        greenCardsWon = new int[playerCount];
        submissions = new RedAppleCard[playerCount - 1];
        submissionPlayers = new int[playerCount - 1];
        random = new Random();

        centerGreenCard = greenDeck.drawCard();
        roundNumber = 1;
        judgePlayer = 0;
        selectingPlayer = nextNonJudge(judgePlayer);
        phase = passToPlayer;

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
        g.setColor(gameBackgroundColor);
        g.fillRect(0, 0, getWidth(), getHeight());

        drawScoreboard(g);
        if (phase == passToPlayer) {
            drawPassScreen(g, "Pass to Player " + (selectingPlayer + 1),
                "Click when ready to choose a red apple.");
        } else if (phase == chooseRedCard) {
            drawCardSelection(g);
        } else if (phase == passToJudge) {
            drawPassScreen(g, "Pass to Player " + (judgePlayer + 1) + " (Judge)",
                "Click when the judge is ready to pick a winner.");
        } else if (phase == judgeChoosing) {
            drawJudgingTable(g);
        }
    }

    private void drawScoreboard(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.drawString("Round " + roundNumber + "   Judge: Player " + (judgePlayer + 1), 22, 30);

        g.setFont(new Font("Arial", Font.PLAIN, 16));
        String score = "Green Apples: ";
        for (int player = 0; player < playerCount; player++) {
            if (player > 0) {
                score += "     ";
            }
            score += "P" + (player + 1) + " " + greenCardsWon[player];
        }
        g.drawString(score, 22, 55);
        g.drawString("First to " + greenCardsToWin + " wins", 625, 30);
    }

    private void drawPassScreen(Graphics g, String title, String instruction) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 32));
        drawCentered(g, title, 280);
        g.setFont(new Font("Arial", Font.PLAIN, 18));
        drawCentered(g, instruction, 320);
    }

    private void drawCardSelection(Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Player " + (selectingPlayer + 1) + ": choose your best match", 22, 96);
        g.setFont(new Font("Arial", Font.PLAIN, 16));
        g.drawString("Only your selected card will be shown to the judge.", 22, 120);

        centerGreenCard.draw(g, 340, 145, 119, 170);
        Card.drawHorizontalHand(g, playerHands[selectingPlayer], handStartX, handY,
            handCardWidth, handCardHeight, true);
    }

    private void drawJudgingTable(Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Player " + (judgePlayer + 1) + ": pick the winning red apple", 22, 96);
        g.setFont(new Font("Arial", Font.PLAIN, 16));
        g.drawString("Submissions are anonymous until you select one.", 22, 120);

        centerGreenCard.draw(g, 340, 145, 119, 170);
        for (int index = 0; index < submissionCount; index++) {
            submissions[index].draw(g, submissionStartX + index * submissionSpacing,
                submissionY, submissionWidth, submissionHeight);
        }
    }

    private void drawCentered(Graphics g, String text, int y) {
        int x = (getWidth() - g.getFontMetrics().stringWidth(text)) / 2;
        g.drawString(text, x, y);
    }

    private int nextNonJudge(int fromPlayer) {
        int next = (fromPlayer + 1) % playerCount;
        if (next == judgePlayer) {
            next = (next + 1) % playerCount;
        }
        return next;
    }

    private int selectedHandCard(int x, int y) {
        if (y < handY || y > handY + handCardHeight) {
            return -1;
        }
        for (int index = 0; index < handSize; index++) {
            int cardX = handStartX + index * handSpacing;
            if (x >= cardX && x <= cardX + handCardWidth) {
                return index;
            }
        }
        return -1;
    }

    private int selectedSubmission(int x, int y) {
        if (y < submissionY || y > submissionY + submissionHeight) {
            return -1;
        }
        for (int index = 0; index < submissionCount; index++) {
            int cardX = submissionStartX + index * submissionSpacing;
            if (x >= cardX && x <= cardX + submissionWidth) {
                return index;
            }
        }
        return -1;
    }

    private void submitCard(int cardIndex) {
        RedAppleCard selectedCard = playerHands[selectingPlayer][cardIndex];
        if (selectedCard == null) {
            return;
        }

        submissions[submissionCount] = selectedCard;
        submissionPlayers[submissionCount] = selectingPlayer;
        playerHands[selectingPlayer][cardIndex] = null;
        submissionCount++;

        if (submissionCount == playerCount - 1) {
            shuffleSubmissions();
            phase = passToJudge;
        } else {
            selectingPlayer = nextNonJudge(selectingPlayer);
            phase = passToPlayer;
        }
    }

    private void shuffleSubmissions() {
        for (int index = submissionCount - 1; index > 0; index--) {
            int swapIndex = random.nextInt(index + 1);
            RedAppleCard card = submissions[index];
            submissions[index] = submissions[swapIndex];
            submissions[swapIndex] = card;

            int player = submissionPlayers[index];
            submissionPlayers[index] = submissionPlayers[swapIndex];
            submissionPlayers[swapIndex] = player;
        }
    }

    private void awardGreenCard(int submissionIndex) {
        int winner = submissionPlayers[submissionIndex];
        greenCardsWon[winner]++;

        for (int index = 0; index < submissionCount; index++) {
            redDeck.discardCard(submissions[index]);
            submissions[index] = null;
        }
        submissionCount = 0;

        if (greenCardsWon[winner] >= greenCardsToWin) {
            phase = gameOver;
            Window window = SwingUtilities.getWindowAncestor(this);
            if (window != null) {
                window.dispose();
            }
            new EndScreen("Player " + (winner + 1));
            return;
        }

        refillHands();
        judgePlayer = (judgePlayer + 1) % playerCount;
        selectingPlayer = nextNonJudge(judgePlayer);
        centerGreenCard = greenDeck.drawCard();
        roundNumber++;
        phase = passToPlayer;
    }

    private void refillHands() {
        for (int player = 0; player < playerCount; player++) {
            for (int card = 0; card < handSize; card++) {
                if (playerHands[player][card] == null) {
                    playerHands[player][card] = redDeck.drawCard();
                }
            }
        }
    }

    public void keyPressed(KeyEvent e) {
        repaint();
    }

    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}

    public void mousePressed(MouseEvent e) {
        if (phase == passToPlayer) {
            phase = chooseRedCard;
        } else if (phase == chooseRedCard) {
            int selectedCard = selectedHandCard(e.getX(), e.getY());
            if (selectedCard >= 0) {
                submitCard(selectedCard);
            }
        } else if (phase == passToJudge) {
            phase = judgeChoosing;
        } else if (phase == judgeChoosing) {
            int winner = selectedSubmission(e.getX(), e.getY());
            if (winner >= 0) {
                awardGreenCard(winner);
            }
        }
        repaint();
    }

    public void mouseReleased(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}
