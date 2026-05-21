import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class GreenAppleCard {
    BufferedImage greenApple;
    private String name;
    private String description;

    public GreenAppleCard(String name) {
        this.name = name;
        this.description = description;
        try {
            greenApple = ImageIO.read(new File("GreenApple.png"));
        } catch (IOException var2) {
            var2.printStackTrace();
        }

   }

    public BufferedImage getImage() {
        return greenApple;
    }
}
