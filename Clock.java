import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.util.Scanner;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Clock implements Runnable {

    private final LocalTime alarmTime;
    private final String filePath;
    private final Scanner scanner;

    public Clock(LocalTime alarmTime, String filePath, Scanner scanner) {
        this.alarmTime = alarmTime;
        this.filePath = filePath;
        this.scanner = scanner;
    }

    @Override
    public void run() {
        while (LocalTime.now().isBefore(alarmTime)) {
            try {
                Thread.sleep(1000);

                LocalTime now = LocalTime.now();

                System.out.printf("\r%02d:%02d:%02d", now.getHour(), now.getMinute(), now.getSecond());
                System.out.flush();

            } catch (InterruptedException e) {
                System.out.println("Thread was interrupted");
                Thread.currentThread().interrupt(); 
                return;
            }
        }
        System.out.println("\n*Alarm Noises*");
        try {
            playSound(filePath);
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
            System.out.println("Error playing sound: " + e.getMessage());
        }
    }

    private void playSound(String filePath) throws IOException, UnsupportedAudioFileException, LineUnavailableException {

        File audioFile = new File(filePath);

        try (AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile)) {
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();

            System.out.println("Press *Enter* to stop alarm");
            scanner.nextLine();
            clip.stop();
            clip.close();

        }
    }
}
