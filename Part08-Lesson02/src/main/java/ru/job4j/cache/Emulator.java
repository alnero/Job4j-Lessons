package ru.job4j.cache;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Emulator {
    /** Cache. */
    private static AbstractCache<String, String> cache;
    /** Name of directory with cached fiels. */
    private static String directoryName;

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        setUpFileCacheDirectory(scanner);

        boolean inProgress = true;
        while (inProgress) {
            System.out.println("\n[1] Cache File");
            System.out.println("[2] Get File From Cache");
            System.out.println("[3] Exit");
            System.out.println("Enter menu item:");
            String menuItem = scanner.nextLine();
            switch (menuItem) {
                case "1":
                    cacheFileContents(scanner);
                    break;
                case "2":
                    getFileFromCache(scanner);
                    break;
                case "3":
                    inProgress = false;
                    break;
                default:
                    System.out.println("Enter \"3\" to Exit.");
            }
        }
    }

    private static void getFileFromCache(Scanner scanner) {
        String fileName = "";
        while (fileName.isEmpty()) {
            System.out.println("Enter file name to get contents from cache:");
            fileName = scanner.nextLine();
        }

        URL resource = ClassLoader.getSystemResource(directoryName + "/" + fileName);
        if (resource != null) {
            System.out.println(cache.get(fileName));
        } else {
            System.out.println("File not found");
        }
    }

    private static void cacheFileContents(Scanner scanner) throws URISyntaxException, IOException {
        String fileName = "";
        while (fileName.isEmpty()) {
            System.out.println("Enter file name to cache its contents:");
            fileName = scanner.nextLine();
        }

        URL resource = ClassLoader.getSystemResource(directoryName + "/" + fileName);
        if (resource != null) {
            Path path = Paths.get(resource.toURI());
            String contents = Files.readAllLines(path).get(0);
            cache.put(fileName, contents);
            System.out.println("File contents cached - OK");
        } else {
            System.out.println("File not found");
        }
    }

    private static void setUpFileCacheDirectory(Scanner scanner) throws IOException, URISyntaxException {
        while (directoryName == null || directoryName.isEmpty()) {
            System.out.println("Enter directory name:");
            directoryName = scanner.nextLine();
        }
        createCache(directoryName);

        URL resource = ClassLoader.getSystemResource(directoryName);
        if (resource != null) {
            Files.walk(Paths.get(resource.toURI()))
                    .forEach(path -> System.out.println(path.getFileName()));
        } else {
            System.out.println("Directory not found");
            System.exit(1);
        }
    }

    private static void createCache(String directoryName) {
        cache = new DirFileCache(directoryName);
    }
}
