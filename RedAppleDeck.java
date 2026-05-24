import java.util.ArrayList;
import java.util.Collections;

public class RedAppleDeck {
    private ArrayList<RedAppleCard> deck;
    private ArrayList<RedAppleCard> discardPile;

    public RedAppleDeck() {
        deck = new ArrayList<>();
        discardPile = new ArrayList<>();
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
        deck.add(new RedAppleCard("Spaceship", "A vehicle for outer space"));
        deck.add(new RedAppleCard("Ice Cream", "A cold sweet dessert"));
        deck.add(new RedAppleCard("Roller Coaster", "A fast amusement park ride"));
        deck.add(new RedAppleCard("Superhero", "A person with amazing powers"));
        deck.add(new RedAppleCard("Library", "A quiet place full of books"));
        deck.add(new RedAppleCard("Thunderstorm", "A storm with lightning and thunder"));
        deck.add(new RedAppleCard("Video Game", "An electronic game"));
        deck.add(new RedAppleCard("Gold Medal", "A prize for first place"));
        deck.add(new RedAppleCard("Haunted House", "A spooky old house"));
        deck.add(new RedAppleCard("Magic Wand", "A tool used to cast spells"));
        deck.add(new RedAppleCard("Skateboard", "A board with wheels"));
        deck.add(new RedAppleCard("Movie Star", "A famous actor"));
        deck.add(new RedAppleCard("Tornado", "A spinning column of wind"));
        deck.add(new RedAppleCard("Treasure Chest", "A box full of valuables"));
        deck.add(new RedAppleCard("Snow Day", "A day off because of snow"));
        deck.add(new RedAppleCard("Guitar", "A musical instrument with strings"));
        deck.add(new RedAppleCard("Alien", "A creature from another planet"));
        deck.add(new RedAppleCard("Cupcake", "A small frosted cake"));
        deck.add(new RedAppleCard("Castle", "A large building for royalty"));
        deck.add(new RedAppleCard("Race Car", "A very fast car"));
        deck.add(new RedAppleCard("Pirate Ship", "A ship used by pirates"));
        deck.add(new RedAppleCard("Fireworks", "Bright explosions in the sky"));
    }

    public void shuffleDeck() {
        Collections.shuffle(deck);
    }

    public RedAppleCard drawCard() {
        if (deck.isEmpty()) {
            deck.addAll(discardPile);
            discardPile.clear();
            shuffleDeck();
        }

        if (deck.isEmpty()) {
            return null;
        }

        return deck.remove(0);
    }

    public void discardCard(RedAppleCard card) {
        if (card != null) {
            discardPile.add(card);
        }
    }

    public int cardsRemaining() {
        return deck.size();
    }
}
