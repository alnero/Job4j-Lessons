package alnero.readFile;

import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Filter log file.
 */
public class LogFilter {
    /**
     * Filter log file and get strings with return code 404.
     * @param file log file
     * @return list of strings with return code 404
     */
    public List<String> filter(String file) {
        List<String> result = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            in.lines().filter(str -> {
                int end = str.lastIndexOf(" ");
                return "404".equals(str.substring(end - 3, end));
            }).forEach(result::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
