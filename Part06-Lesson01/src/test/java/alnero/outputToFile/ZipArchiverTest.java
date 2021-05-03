package alnero.outputToFile;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.io.File;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Test zip archiver.
 */
public class ZipArchiverTest {
    /**
     * Temp folder for test files.
     */
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void whenAddFolderToArchiveThenProperArchiveCreated() throws IOException {
        File file = folder.newFile("archive.zip");
        String destination = file.getAbsolutePath();
        ZipArchiver.main(new String[]{"-d=src/test/resources", "-e=txt", "-o=" + destination});
        List<String> expected = Arrays.asList(
                "src/test/resources/Test_folder_1/Test_folder_2/test_file_2.test",
                "src/test/resources/Test_folder_1/test_file_1.test",
                "src/test/resources/chat.log",
                "src/test/resources/server.log",
                "src/test/resources/app.properties"
        );
        List<String> result = new ArrayList<>();
        try (ZipInputStream zip = new ZipInputStream(new BufferedInputStream(new FileInputStream(file)))) {
            ZipEntry entry = null;
            while ((entry = zip.getNextEntry()) != null) {
                result.add(entry.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertThat(result, Matchers.containsInAnyOrder(expected.toArray()));
    }
}
