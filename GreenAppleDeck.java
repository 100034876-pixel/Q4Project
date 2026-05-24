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
        deck.add(new GreenAppleCard("Delicious"));
        deck.add(new GreenAppleCard("Mysterious"));
        deck.add(new GreenAppleCard("Messy"));
        deck.add(new GreenAppleCard("Famous"));
        deck.add(new GreenAppleCard("Dangerous"));
        deck.add(new GreenAppleCard("Peaceful"));
        deck.add(new GreenAppleCard("Expensive"));
        deck.add(new GreenAppleCard("Lucky"));
        deck.add(new GreenAppleCard("Loud"));
        deck.add(new GreenAppleCard("Tiny"));
        deck.add(new GreenAppleCard("Unforgettable"));
        deck.add(new GreenAppleCard("Useful"));
        deck.add(new GreenAppleCard("Wild"));
        deck.add(new GreenAppleCard("Boring"));
        deck.add(new GreenAppleCard("Clever"));
        deck.add(new GreenAppleCard("Colorful"));
        deck.add(new GreenAppleCard("Creepy"));
        deck.add(new GreenAppleCard("Fast"));
        deck.add(new GreenAppleCard("Friendly"));
        deck.add(new GreenAppleCard("Gross"));
        deck.add(new GreenAppleCard("Impossible"));
        deck.add(new GreenAppleCard("Magical"));
        deck.add(new GreenAppleCard("Noisy"));
        deck.add(new GreenAppleCard("Strange"));
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
