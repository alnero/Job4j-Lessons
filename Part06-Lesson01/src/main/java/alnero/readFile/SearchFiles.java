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

import static java.nio.file.FileVisitResult.CONTINUE;

/**
 * Search files by name, part of name and mask.
 */
public class SearchFiles {
    /**
     * Get list of files.
     * @param root where to search
     * @param searchString file name, part of file name, file name with ? or *
     * @return list of files
     * @throws IOException as defined by FileVisitor API
     */
    public List<File> searchFiles(File root, String searchString) throws IOException {
        String searchRegExp = searchString.replace("?", ".?").replace("*", ".*?");
        List<File> result = new ArrayList<>();
        FileVisitor<Path> fileVisitor = new FileVisitor<>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                return CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
                String fileName = path.getFileName().toString();
                if (fileName.equals(searchString)
                    || fileName.contains(searchString)
                    || fileName.matches(searchRegExp)) {
                    result.add(path.toFile());
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
