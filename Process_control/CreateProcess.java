package Process_control;

import java.io.IOException;

public class CreateProcess {
    public static void main(String[] args) {
        ProcessBuilder builder = new ProcessBuilder("notepad.exe","example.txt");
        try {
            Process process = builder.start();
            System.out.println("Notepad started. PID: " + process.pid());
        } catch (IOException e) {
            System.out.println("Error starting process: " + e.getMessage());
        }
    }

}
