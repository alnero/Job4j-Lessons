package alnero.readFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.FileVisitor;
import java.nio.file.FileVisitResult;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.attribute.BasicFileAttributes;

import static java.nio.file.FileVisitResult.CONTINUE;

/**
 * Search for files with appropriate extension using FileVisitor API.
 */
public class SearchFilesByExt {
    public List<Path> search(Path root, String ext) throws IOException {
        List<Path> result = new ArrayList<>();
        FileVisitor<Path> fileVisitor = new FileVisitor<>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                return CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (file.toString().endsWith(ext)) {
                    result.add(file);
                }
                return CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                return CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                return CONTINUE;
            }
        };
        Files.walkFileTree(root, fileVisitor);
        return result;
    }

    /**
     * Search files from command line, two arguments required: path to search folder, file extension.
     * @param args path to search folder and file extension
     * @throws IOException exceptions from FileVisitor API
     */
    public static void main(String[] args) throws IOException {
        if (args.length <= 1) {
            throw new IllegalArgumentException("Arguments required, usage: java -cp Part06-Lesson01-1.0.0.jar alnero.readFile.SearchFilesByExt PATH_TO_SEARCH_FOLDER FILE_EXTENSION");
        }
        SearchFilesByExt searchFiles = new SearchFilesByExt();
        Path path = Paths.get(args[0]);
        List<Path> pathsToFiles = searchFiles.search(path, args[1]);
        pathsToFiles.forEach(System.out::println);
    }
}
