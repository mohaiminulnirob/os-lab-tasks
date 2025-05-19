package Process_control;

import java.io.IOException;

public class TerminateRunningProcess {
    public static void main(String[] args) {
        try {
//            Process notepad = new ProcessBuilder("notepad.exe").start();
//            long pid = notepad.pid();
//            new ProcessBuilder("taskkill", "/F", "/PID", String.valueOf(pid)).start();
            Process process = new ProcessBuilder("taskkill", "/F", "/IM", "notepad.exe").start();
            System.out.println("Sent kill command to Notepad.");
            int exitCode = process.waitFor();
            System.out.println("taskkill exited with code: " + exitCode);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
