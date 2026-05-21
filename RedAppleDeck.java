import java.util.ArrayList;
import java.util.Collections;

public class RedAppleDeck {
    private ArrayList<RedAppleCard> deck;

    public RedAppleDeck() {
        deck = new ArrayList<>();
        loadCards();
        shuffleDeck();
    }

    private void loadCards() {
        deck.add(new RedAppleCard("Shark", "A dangerous ocean predator"));
        deck.add(new RedAppleCard("Pizza", "A popular cheesy food"));
        deck.add(new RedAppleCard("Dragon", "A mythical flying creature"));
        deck.add(new RedAppleCard("School Bus", "Transportation for students"));
        deck.add(new RedAppleCard("Robot", "A programmable machine"));
        deck.add(new RedAppleCard("Volcano", "A mountain that erupts lava"));
        deck.add(new RedAppleCard("TikTok", "A short-form video platform"));
        deck.add(new RedAppleCard("Basketball", "A sport played with a hoop"));
    }

    public void shuffleDeck() {
        Collections.shuffle(deck);
    }

    public RedAppleCard drawCard() {
        if (deck.isEmpty()) {
            return null;
        }

        return deck.remove(0);
    }

    public int cardsRemaining() {
        return deck.size();
    }
}