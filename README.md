# Java Alarm Clock

A simple console-based alarm clock implemented in Java. The program counts down to a specified alarm time, displays the current time updating every second, and plays a WAV audio alarm when the time is reached. The alarm sound can be stopped by pressing *Enter*.

---

## Features

- Real-time clock display with second-level precision in the console
- Configurable alarm time
- Plays a user-provided WAV audio file as an alarm
- Allows the user to stop the alarm sound by pressing *Enter*
- Thread-based implementation for responsive timing

---

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher installed
- Basic understanding of running Java programs from the command line
- A WAV audio file to use as your alarm sound

### Setup & Running

1. **Clone or download this repository:**

git clone https://github.com/your-username/java-alarm-clock.git
cd java-alarm-clock

2. Add your WAV audio file to the project directory.

Rename it or update the file path in the code accordingly (default in example: alarm.wav).

3. Compile the Java source files:

 javac Clock.java ClockTest.java

4. Run the test program:

java ClockTest

####Usage

->The example ClockTest class sets an alarm 10 seconds from the current time for demonstration.

->Modify the alarmTime variable in ClockTest to set your desired alarm time.

->When the alarm triggers, the WAV file will play, and the console will prompt you to press Enter to stop the alarm sound.

#####Code Overview 

* Clock.java: Implements Runnable that continuously updates the time until the alarm time is reached, then plays the alarm sound.

* ClockTest.java: Sample main class to demonstrate how to create and start the alarm clock thread.

######Notes

+ Ensure your WAV file path is correct relative to the running directory.

+ The program uses console output with carriage return (\r) to update the time display on a single line.

+ Closing the Scanner used for user input is managed outside of the Clock class to avoid closing System.in prematurely.

+ Tested on Java 8+ and Windows/Linux terminals; behavior might vary in some IDE consoles.

💡License:

This project is open source under the MIT License.

💡Contact:

Developed by Dulan Dhanush
Feel free to open issues or submit pull requests.

💡Example Main Method (ClockTest.java)->

import java.time.LocalTime;
import java.util.Scanner;

public class ClockTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Set alarm 10 seconds from now for testing
        LocalTime alarmTime = LocalTime.now().plusSeconds(10);
        String filePath = "alarm.wav";

        Clock clock = new Clock(alarmTime, filePath, scanner);
        Thread clockThread = new Thread(clock);
        clockThread.start();

        try {
            clockThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        scanner.close();
    }
}


