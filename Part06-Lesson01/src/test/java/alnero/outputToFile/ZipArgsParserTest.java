package alnero.outputToFile;

import java.nio.file.Paths;
import java.nio.file.InvalidPathException;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ZipArgsParserTest {
    @Test
    public void whenSupplyCorrectArgumentsThenTheyAreValid() {
        ZipArgsParser parser = new ZipArgsParser(new String[]{"-d=i:/test/resources", "-e=exe", "-o=archive.zip"});
        assertThat(parser.valid(), is(true));
        assertThat(parser.directory(), is("i:/test/resources"));
        assertThat(parser.exclude(), is("exe"));
        assertThat(parser.output(), is("archive.zip"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenSupplyNotEnoughArgumentsThenThrowException() {
        new ZipArgsParser(new String[]{"-e=exe", "-o=archive.zip"});
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenSupplyMoreThanThreeArgumentsThenThrowException() {
        new ZipArgsParser(new String[]{"-d=i:/test/resources", "-e=exe", "-o=archive.zip", "-x=test"});
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenSupplyEmptyArrayOfArgumentsThenThrowException() {
        new ZipArgsParser(new String[]{});
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenWrongArgumentKeysThenThrowException() {
        new ZipArgsParser(new String[]{"-x=i:/test/resources", "-y=exe", "-z=archive.zip"});
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenEmptyArgumentValueThenThrowException() {
        new ZipArgsParser(new String[]{"-d=", "-e=exe", "-o=archive.zip"});
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenKeyValueArgumentInWrongFormatThenThrowException() {
        new ZipArgsParser(new String[]{"-d=i:/test/resources", "-e:exe", "-o=archive.zip"});
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenInvalidPathArgumentThenThrowException() {
        try {
            Paths.get("\u0000");
        } catch (InvalidPathException e) {
            new ZipArgsParser(new String[]{"-d=\u0000", "-e:exe", "-o=archive.zip"});
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenOutputFileArgumentNotEndInZipThenThrowException() {
            new ZipArgsParser(new String[]{"-d=i:/test/resources", "-e=exe", "-o=archive.rar"});
    }
}
