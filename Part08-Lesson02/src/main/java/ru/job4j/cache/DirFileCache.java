package ru.job4j.cache;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

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
                fileContents = Files.readString(Path.of(ClassLoader.getSystemResource(cachingDir).getPath(), key));
            } catch (IOException e) {
                e.printStackTrace();
            }
        return fileContents;
    }
}
