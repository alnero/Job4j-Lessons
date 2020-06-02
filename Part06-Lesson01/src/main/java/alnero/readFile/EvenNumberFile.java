package alnero.readFile;

import java.io.File;
import java.io.FileInputStream;
/**
 * Class to check even/not-even numbers in a file.
 */
public class EvenNumberFile {
    /**
     * Read a file of numbers and output result to console.
     * @param file numbers file
     */
    public void read(File file) {
        StringBuilder sb = new StringBuilder();
        try (FileInputStream in = new FileInputStream(file)) {
            int read;
            while ((read = in.read()) != -1) {
                sb.append((char) read);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String[] lines = sb.toString().split(System.lineSeparator());
        for (String line : lines) {
            int num = Integer.parseInt(line);
            if (num % 2 == 0) {
                System.out.println(num + " is even");
            } else {
                System.out.println(num + " is NOT even");
            }
        }
    }
}
