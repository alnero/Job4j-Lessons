package alnero.readFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * List files from directory.
 */
public class ListDir {
    /**
     * Get list of file names and sizes from directory.
     * @param path path to directory
     * @return list of file names and sizes
     */
    public List<String> listFiles(String path) {
        List<String> result = new ArrayList<>();
        File directory = new File(path);
        if (!directory.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", directory.getAbsoluteFile()));
        }
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", directory.getAbsoluteFile()));
        }
        for (File file : directory.listFiles()) {
            result.add(String.format("%s %s", file.getName(), file.length()));
        }
        return result;
    }
}
