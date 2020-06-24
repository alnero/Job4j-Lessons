package alnero.readFile;

import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.FileVisitor;
import java.nio.file.FileVisitResult;
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
}
