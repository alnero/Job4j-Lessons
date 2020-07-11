package alnero.readFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static java.nio.file.FileVisitResult.CONTINUE;

/**
 * Search files by name, part of name and mask.
 */
public class SearchFiles {
    /**
     * Get list of files.
     * @param root where to search
     * @param predicate search clause
     * @return list of files
     * @throws IOException as defined by FileVisitor API
     */
    public List<File> searchFiles(File root, Predicate<File> predicate) throws IOException {
        List<File> result = new ArrayList<>();
        FileVisitor<Path> fileVisitor = new FileVisitor<>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                return CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
                File file = path.toFile();
                if (predicate.test(file)) {
                    result.add(file);
                }
                return CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path path, IOException exc) throws IOException {
                return CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                return CONTINUE;
            }
        };
        Files.walkFileTree(root.toPath(), fileVisitor);
        return result;
    }
}
