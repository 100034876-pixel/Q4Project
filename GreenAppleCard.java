import java.awt.Color;

public class GreenAppleCard extends Card {
    public GreenAppleCard(String name) {
        super(name, "Green apple card", "GreenApple.png");
    }

    protected Color getOutlineColor() {
        return Color.GREEN;
    }
}
