package File_management;

import java.io.File;

public class FileAttributes {
    public static void main(String[] args) {
        File file = new File("example.txt");

        if (file.exists()) {
            System.out.println("File name: " + file.getName());
            System.out.println("Absolute path: " + file.getAbsolutePath());
            System.out.println("Is directory: " + file.isDirectory());
            System.out.println("Is file: " + file.isFile());
            System.out.println("Readable: " + file.canRead());
            System.out.println("Writable: " + file.canWrite());
            System.out.println("Executable: " + file.canExecute());
            System.out.println("File size: " + file.length() + " bytes");
            System.out.println("Last modified: " + file.lastModified());
        } else {
            System.out.println("File does not exist.");
        }
    }
}
