package alnero.fileSearch;

import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.PathMatcher;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.FileSystems;
import java.nio.file.attribute.BasicFileAttributes;

import static java.nio.file.FileVisitResult.CONTINUE;

public class Finder {
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
}
