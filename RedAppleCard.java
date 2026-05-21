import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class RedAppleCard {
    BufferedImage redApple;
    private String name;
    private String description;

    public RedAppleCard(String name, String description) {
        this.name = name;
        this.description = description;
        try {
            redApple = ImageIO.read(new File("RedApple.png"));
        } catch (IOException var2) {
            var2.printStackTrace();
        }

   }

    public BufferedImage getImage() {
        return redApple;
    }
}