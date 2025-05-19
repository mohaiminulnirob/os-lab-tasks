package Process_control;

import java.io.IOException;

public class CreateAndTerminateProcess {
    public static void main(String[] args) {
        try {
            Process notepad = new ProcessBuilder("notepad.exe").start();
            System.out.println("Notepad started. PID: " + notepad.pid());

            Thread.sleep(5000);

            new ProcessBuilder("taskkill", "/F", "/IM", "notepad.exe").start();
            System.out.println("Notepad force-killed using taskkill.");

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}
