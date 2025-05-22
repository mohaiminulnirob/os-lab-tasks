package File_management;

import java.io.File;

public class SetFileReadOnly {
    public static void main(String[] args) {
        File file = new File("example.txt");

        if (file.exists()) {
            boolean success = file.setReadOnly(); // or file.setWritable(false)
            System.out.println("Set to read-only: " + success);
        } else {
            System.out.println("File does not exist.");
        }
    }
}
