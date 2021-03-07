package alnero.fileSearch;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.io.File;
import java.io.PrintWriter;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.PathMatcher;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.FileVisitResult;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import static java.nio.file.FileVisitResult.CONTINUE;

public class Finder {
    /** Search directory key. */
    private static final String SEARCH_DIRECTORY_KEY = "-d";
    /** Search type key. */
    private static final String SEARCH_TYPE_KEY = "-t";
    /** Search phrase key. */
    private static final String SEARCH_PHRASE_KEY = "-n";
    /** Output file name key. */
    private static final String OUTPUT_FILE_NAME_KEY = "-o";
    /** Search type names. */
    private static final String[] SEARCH_TYPE_NAMES = new String[]{"name", "mask", "regex"};

    /**
     * Search files by name, mask or regex in specified directory and sub-directories.
     * Mandatory arguments are supplied via CLI. Result is available as separate file.
     * @param args -d=search directory, -t=search type (name, mask, regex), -n=search pattern, -o=result file name
     */
    public static void main(String[] args) {
        try {
            Finder finder = new Finder();
            Map<String, String> searchArguments = finder.getSearchArgs(args);
            List<File> result = finder.searchFiles(
                    searchArguments.get(SEARCH_DIRECTORY_KEY),
                    searchArguments.get(SEARCH_TYPE_KEY),
                    searchArguments.get(SEARCH_PHRASE_KEY));
            finder.saveStringListToFile(
                    result.stream().map(File::toString).collect(Collectors.toList()),
                    new File(searchArguments.get(OUTPUT_FILE_NAME_KEY)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Help is printed and exception is thrown if there are mistakes during parsing of supplies arguments.
     */
    public static void printHelpThrowIllegalArgumentException() {
        System.out.println(
                "Usage: java -jar fileSearch.jar [OPTION]...\n"
              + "Search files by name, mask or regex in specified directory and sub-directories.\n"
              + "Search results are available as separate file.\n\n"
              + "Example: java -jar fileSearch.jar -d=c:/ -t=mask -n=*.txt -o=result.txt\n\n"
              + "All arguments are mandatory:\n"
              + "-d\t\tsearch directory\n"
              + "-t\t\tsearch type: name, mask, regex\n"
              + "-n\t\tfile name, mask or regular expression\n"
              + "-o\t\tresult file name\n");
        throw new IllegalArgumentException();
    }

    /**
     * Parsing of required arguments.
     * @param args CLI supplied arguments: -d, -t, -n, -o
     * @return map of key=values derived from supplied arguments
     */
    public Map<String, String> getSearchArgs(String[] args) {
        if (args.length != 4) {
            printHelpThrowIllegalArgumentException();
        }
        Map<String, String> searchArguments = new HashMap<>();
        for (String argument : args) {
            // args to have only one separator symbol "="
            if (!argument.contains("=") || argument.indexOf("=", argument.indexOf("=") + 1) > -1) {
                printHelpThrowIllegalArgumentException();
            }
            String[] keyValue = argument.split("=");
            String key = keyValue[0];
            // only predefined keys allowed
            if (Arrays.stream(new String[]{SEARCH_DIRECTORY_KEY, SEARCH_TYPE_KEY, SEARCH_PHRASE_KEY, OUTPUT_FILE_NAME_KEY}).noneMatch(key::matches)) {
                printHelpThrowIllegalArgumentException();
            }
            String value = keyValue[1];
            // only predefined search types allowed
            if (SEARCH_TYPE_KEY.equals(key) && Arrays.stream(SEARCH_TYPE_NAMES).noneMatch(value::matches)) {
                printHelpThrowIllegalArgumentException();
            }
            searchArguments.put(key, value);
        }
        return searchArguments;
    }

    /**
     * Get list of files.
     * Search pattern is defined by required search type:
     *      - type "name" or "mask" -> "glob" search pattern
     *      - type "regex" -> "regex" search pattern
     * @param dir start directory, search is recursive
     * @param searchType type of search
     * @param searchPhrase search clause
     * @return list of files
     * @throws IOException as defined by FileVisitor API
     */
    public List<File> searchFiles(String dir, String searchType, String searchPhrase) throws IOException {
        List<File> result = new ArrayList<>();
        // choose pattern depending on required search type
        String pattern = "glob:" + searchPhrase;
        if ("regex".equals(searchType)) {
            pattern = "regex:" + searchPhrase;
        }
        PathMatcher matcher = FileSystems.getDefault().getPathMatcher(pattern);
        SimpleFileVisitor<Path> fileVisitor = new SimpleFileVisitor<Path>() {
            void find(Path file) {
                Path name = file.getFileName();
                if (name != null && matcher.matches(name)) {
                    result.add(file.toFile());
                }
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                find(file);
                return CONTINUE;
            }

            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
                find(dir);
                return CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException e) {
                e.printStackTrace();
                return CONTINUE;
            }
        };
        Files.walkFileTree(Paths.get(dir), fileVisitor);
        return result;
    }

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
