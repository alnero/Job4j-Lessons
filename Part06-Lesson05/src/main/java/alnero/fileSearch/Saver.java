package alnero.fileSearch;

import java.util.List;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;

public class Saver {
    /**
     * Save list of strings to file.
     * @param log  list of strings
     * @param file destination file
     */
    public void saveStringListToFile(List<String> log, File file) throws IOException {
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(file)))) {
            for (String s : log) {
                out.println(s);
            }
        }
    }
}
