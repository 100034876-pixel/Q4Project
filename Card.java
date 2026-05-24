import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Card {
    private String name;
    private String description;
    private BufferedImage image;
    private static int horizontalCardSpacing = 25;
    private static int verticalCardSpacing = 20;
    private static int revealedHorizontalCardSpacing = 90;
    private static int revealedVerticalCardSpacing = 80;

    public Card(String name, String description, String imageFileName) {
        this.name = name;
        this.description = description;
        try {
            image = ImageIO.read(new File(imageFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void draw(Graphics g, int x, int y, int width, int height) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, width, height);
        g.setColor(getOutlineColor());
        g.drawRect(x, y, width, height);
        g.drawRect(x + 3, y + 3, width - 6, height - 6);

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 11));
        drawText(g, name, x + 8, y + 24, width - 16, 14);
        g.setFont(new Font("Arial", Font.PLAIN, 9));
        drawText(g, description, x + 8, y + 54, width - 16, 12);
    }

    public void drawBack(Graphics g, int x, int y, int width, int height) {
        if (image != null) {
            g.drawImage(image, x, y, width, height, null);
        }
    }

    protected Color getOutlineColor() {
        return Color.RED;
    }

    public static RedAppleCard[][] dealRedHands(RedAppleDeck deck, int playerCount, int cardsPerPlayer) {
        RedAppleCard[][] hands = new RedAppleCard[playerCount][cardsPerPlayer];

        for (int player = 0; player < hands.length; player++) {
            for (int card = 0; card < hands[player].length; card++) {
                hands[player][card] = deck.drawCard();
            }
        }

        return hands;
    }

    public static void drawHorizontalHand(Graphics g, RedAppleCard[] hand, int startX, int y, int width, int height) {
        drawHorizontalHand(g, hand, startX, y, width, height, true);
    }

    public static void drawHorizontalHand(Graphics g, RedAppleCard[] hand, int startX, int y, int width, int height, boolean revealed) {
        for (int card = 0; card < hand.length; card++) {
            int spacing = horizontalCardSpacing;
            if (revealed) {
                spacing = revealedHorizontalCardSpacing;
            }
            drawCard(g, hand[card], startX + card * spacing, y, width, height, revealed);
        }
    }

    public static void drawVerticalHand(Graphics g, RedAppleCard[] hand, int x, int startY, int width, int height) {
        drawVerticalHand(g, hand, x, startY, width, height, true);
    }

    public static void drawVerticalHand(Graphics g, RedAppleCard[] hand, int x, int startY, int width, int height, boolean revealed) {
        for (int card = 0; card < hand.length; card++) {
            int spacing = verticalCardSpacing;
            if (revealed) {
                spacing = revealedVerticalCardSpacing;
            }
            drawCard(g, hand[card], x, startY + card * spacing, width, height, revealed);
        }
    }

    private static void drawCard(Graphics g, Card card, int x, int y, int width, int height, boolean revealed) {
        if (card != null) {
            if (revealed) {
                card.draw(g, x, y, width, height);
            } else {
                card.drawBack(g, x, y, width, height);
            }
        }
    }

    private static void drawText(Graphics g, String text, int x, int y, int maxWidth, int lineHeight) {
        String[] words = text.split(" ");
        String line = "";

        for (int i = 0; i < words.length; i++) {
            String nextLine = line;
            if (nextLine.length() > 0) {
                nextLine += " ";
            }
            nextLine += words[i];

            if (g.getFontMetrics().stringWidth(nextLine) > maxWidth && line.length() > 0) {
                g.drawString(line, x, y);
                y += lineHeight;
                line = words[i];
            } else {
                line = nextLine;
            }
        }

        if (line.length() > 0) {
            g.drawString(line, x, y);
        }
    }
}
