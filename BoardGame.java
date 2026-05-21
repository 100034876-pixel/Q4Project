import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;






public class BoardGame{


	// Instance variables
	
	public BoardGame(){
		
	}


	 // Play sound. You can find free .wav files at wavsource.com.
    public void playSound() {
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File("").getAbsoluteFile()));
            clip.start();
        } catch (Exception exc) {
            exc.printStackTrace(System.out);
        }


    }


	
	
	
}
