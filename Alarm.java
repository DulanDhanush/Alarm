
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Alarm{
public static void main(String[] args) {

    Scanner scanner = new Scanner (System.in);

    DateTimeFormatter formatter =  DateTimeFormatter.ofPattern("HH:mm:ss");
    LocalTime alarmTime = null;
    String filePath ="Repeat - Mark Karan, Scott Guberman, Angeline Saris, Jeremy Hoenig.wav";

    while (alarmTime == null) { 
        
    
    try{

    System.out.print("Enter an alarm time(HH:MM:SS):");
    String inputTime = scanner.nextLine();

    alarmTime = LocalTime.parse(inputTime, formatter);
    System.out.println("Alarm set for " + alarmTime);

    }catch(DateTimeParseException e){

        System.out.println("Invalid Format!");
    }
}
Clock alarmClock = new Clock(alarmTime,filePath,scanner);
Thread alarmThread = new Thread(alarmClock);
alarmThread.start();
    

    
}
}