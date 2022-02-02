package ru.job4j.cache;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DirFileCache extends AbstractCache<String, String> {
    /** Directory to cache. */
    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        String fileContents = null;
        try {
            Path searchResult = Files.walk(Paths.get(ClassLoader.getSystemResource(cachingDir).toURI()))
                    .filter(file -> key.equals(file.getFileName().toString()))
                    .findFirst()
                    .orElse(null);
            try {
                Path path = Paths.get(searchResult.toString());
                fileContents = Files.readAllLines(path).get(0);
                super.put(key, fileContents);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return fileContents;
    }
}
