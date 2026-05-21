import java.util.ArrayList;
import java.util.Collections;
public class GreenAppleDeck {
    
    private ArrayList<GreenAppleCard> deck;

    public GreenAppleDeck() {
        deck = new ArrayList<GreenAppleCard>();
        loadCards();
        shuffleDeck();
    }

    private void loadCards() {
        deck.add(new GreenAppleCard("Funny"));
        deck.add(new GreenAppleCard("Scary"));
        deck.add(new GreenAppleCard("Brilliant"));
        deck.add(new GreenAppleCard("Ancient"));
        deck.add(new GreenAppleCard("Awkward"));
        deck.add(new GreenAppleCard("Heroic"));
        deck.add(new GreenAppleCard("Ridiculous"));
        deck.add(new GreenAppleCard("Powerful"));
    }

    public void shuffleDeck() {
        Collections.shuffle(deck);
    }

    public GreenAppleCard drawCard() {
        if (deck.isEmpty()) {
            return null;
        }

        return deck.remove(0);
    }

    public int cardsRemaining() {
        return deck.size();
    }
}