package ru.job4j.cache;

import org.junit.Test;
import org.mockito.Mockito;
import org.powermock.reflect.Whitebox;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class DirFileCacheTest {
    /** Test directory name. */
    public static final String TEST_DIRECTORY = "TestDirectory";
    /** Test file name. */
    public static final String TEST_FILE = "test.txt";
    /** Test file contents. */
    public static final String TEST_FILE_CONTENTS = "qwertyuiopasdfghjklzxcvbnm";


    @Test
    public void whenGetFileFromCacheThenProperContentsReturned() {
        AbstractCache<String, String> cache = new DirFileCache(TEST_DIRECTORY);
        assertThat(cache.get(TEST_FILE), is(TEST_FILE_CONTENTS));
    }

    @Test
    public void whenThreeTimesGetFileFromCacheThenLoadExecutedOnlyOnce() {
        AbstractCache<String, String> cache = new DirFileCache(TEST_DIRECTORY);
        AbstractCache<String, String> spy = Mockito.spy(cache);
        spy.get(TEST_FILE);
        spy.get(TEST_FILE);
        spy.get(TEST_FILE);

        Mockito.verify(spy, Mockito.times(1)).load(TEST_FILE);
        Mockito.verify(spy, Mockito.times(1)).load(Mockito.anyString());
    }

    @Test
    public void whenFileIsRemovedFromCacheThenLoadExecutedEveryTime() throws Exception {
        AbstractCache<String, String> cache = new DirFileCache(TEST_DIRECTORY);
        AbstractCache<String, String> spyCache = Mockito.spy(cache);
        Map<String, SoftReference<String>> spyMap = Mockito.spy(
                (HashMap<String, SoftReference<String>>) Whitebox.getInternalState(spyCache, "cache"));
        Whitebox.setInternalState(spyCache, "cache", spyMap);
        Mockito.doReturn(new SoftReference<String>(null))
                .when(spyMap)
                .getOrDefault(Mockito.anyString(), Mockito.any(SoftReference.class));

        spyCache.get(TEST_FILE);
        spyCache.get(TEST_FILE);
        spyCache.get(TEST_FILE);

        Mockito.verify(spyCache, Mockito.times(3)).load(TEST_FILE);
        Mockito.verify(spyCache, Mockito.times(3)).load(Mockito.anyString());
    }
}
