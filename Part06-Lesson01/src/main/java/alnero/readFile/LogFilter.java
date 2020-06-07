package alnero.readFile;

import java.io.PrintWriter;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.ArrayList;

/**
 * Filter log file and save results.
 */
public class LogFilter {
    /**
     * Filter log file and get strings with return code 404.
     * @param file log file name
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

    /**
     * Save list of strings to a file.
     * @param log list of strings
     * @param file destination file name
     */
    public void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(file)))) {
            log.forEach(str -> out.write(str + System.lineSeparator()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
