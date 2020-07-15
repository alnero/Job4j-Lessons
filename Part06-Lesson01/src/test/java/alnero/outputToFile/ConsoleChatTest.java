package alnero.outputToFile;

import org.junit.Test;
import java.util.Random;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ConsoleChatTest {
    /** File with only one sentence. */
    private File oneSentenceFile = new File("src/test/resources/one_sentense.txt");
    /** File with only one line separator character. */
    private File lineSeparatorFile = new File("src/test/resources/line_separator.txt");
    /** Empty file. */
    private File zeroLengthFile = new File("src/test/resources/empty_file.txt");
    /** File with random text. */
    private File textFile = new File("src/test/resources/lorem_ipsum.txt");
    /** File with chat log. */
    private File chatLog = new File("src/test/resources/chat.log");

    private String getChatLog() {
        StringBuilder result = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(chatLog))) {
            String line = in.readLine();
            while (line != null) {
                result.append(line).append(System.lineSeparator());
                line = in.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    @Test
    public void whenOnlyFinishCommandThenOnlyFinishCommandInOutputFile() {
        // redirect console input
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream("завершить".getBytes());
        InputStream systemIn = System.in;
        System.setIn(byteArrayInputStream);

        new ConsoleChat().chat(oneSentenceFile, chatLog);
        assertThat(getChatLog(), is("завершить" + System.lineSeparator()));

        // restore console input
        System.setIn(systemIn);
    }

    @Test
    public void whenStopTalkAndFinishCommandThenStopTalkAndFinishCommandsInOutputFile() {
        // redirect console input
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(("стоп" + System.lineSeparator() + "завершить").getBytes());
        InputStream systemIn = System.in;
        System.setIn(byteArrayInputStream);

        new ConsoleChat().chat(oneSentenceFile, chatLog);
        assertThat(getChatLog(), is("стоп" + System.lineSeparator() + "завершить" + System.lineSeparator()));

        // restore console input
        System.setIn(systemIn);
    }

    @Test
    public void whenOneUserInputAndFinishCommandThenOneUserInputOneReplyAndFinishCommandInOutputFile() {
        // redirect console input
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(("aaa" + System.lineSeparator() + "завершить").getBytes());
        InputStream systemIn = System.in;
        System.setIn(byteArrayInputStream);

        new ConsoleChat().chat(oneSentenceFile, chatLog);
        assertThat(getChatLog(), is(
                "aaa" + System.lineSeparator()
                + "Lorem ipsum dolor sit amet, consectetur adipiscing elit." + System.lineSeparator()
                + "завершить" + System.lineSeparator()
        ));

        // restore console input
        System.setIn(systemIn);
    }

    @Test
    public void whenFourUserInputAndFinishCommandThenFourUserInputFourReplyAndFinishCommandInOutputFile() {
        // redirect console input
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream((
                "aaa" + System.lineSeparator()
              + "bbb" + System.lineSeparator()
              + "ccc" + System.lineSeparator()
              + "ddd" + System.lineSeparator()
              + "завершить").getBytes());
        InputStream systemIn = System.in;
        System.setIn(byteArrayInputStream);

        new ConsoleChat().chat(oneSentenceFile, chatLog);
        assertThat(getChatLog(), is(
                "aaa" + System.lineSeparator()
              + "Lorem ipsum dolor sit amet, consectetur adipiscing elit." + System.lineSeparator()
              + "bbb" + System.lineSeparator()
              + "Lorem ipsum dolor sit amet, consectetur adipiscing elit." + System.lineSeparator()
              + "ccc" + System.lineSeparator()
              + "Lorem ipsum dolor sit amet, consectetur adipiscing elit." + System.lineSeparator()
              + "ddd" + System.lineSeparator()
              + "Lorem ipsum dolor sit amet, consectetur adipiscing elit." + System.lineSeparator()
              + "завершить" + System.lineSeparator()
        ));

        // restore console input
        System.setIn(systemIn);
    }

    @Test
    public void whenStopTalkContinutTalkInChatThenNoReplyBetweenStopTalkAndContinueTalkInOutputFile() {
        // redirect console input
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream((
                "aaa" + System.lineSeparator()
                        + "стоп" + System.lineSeparator()
                        + "bbb" + System.lineSeparator()
                        + "ccc" + System.lineSeparator()
                        + "продолжить" + System.lineSeparator()
                        + "ddd" + System.lineSeparator()
                        + "завершить").getBytes());
        InputStream systemIn = System.in;
        System.setIn(byteArrayInputStream);

        new ConsoleChat().chat(oneSentenceFile, chatLog);
        assertThat(getChatLog(), is(
                "aaa" + System.lineSeparator()
                        + "Lorem ipsum dolor sit amet, consectetur adipiscing elit." + System.lineSeparator()
                        + "стоп" + System.lineSeparator()
                        + "bbb" + System.lineSeparator()
                        + "ccc" + System.lineSeparator()
                        + "продолжить" + System.lineSeparator()
                        + "Lorem ipsum dolor sit amet, consectetur adipiscing elit." + System.lineSeparator()
                        + "ddd" + System.lineSeparator()
                        + "Lorem ipsum dolor sit amet, consectetur adipiscing elit." + System.lineSeparator()
                        + "завершить" + System.lineSeparator()
        ));

        // restore console input
        System.setIn(systemIn);
    }

    @Test
    public void whenThreeContinueTalkCommandThenNormalChatInOutputFile() {
        // redirect console input
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream((
                "продолжить" + System.lineSeparator()
                        + "продолжить" + System.lineSeparator()
                        + "продолжить" + System.lineSeparator()
                        + "завершить").getBytes());
        InputStream systemIn = System.in;
        System.setIn(byteArrayInputStream);

        new ConsoleChat().chat(oneSentenceFile, chatLog);
        assertThat(getChatLog(), is(
                "продолжить" + System.lineSeparator()
                        + "Lorem ipsum dolor sit amet, consectetur adipiscing elit." + System.lineSeparator()
                        + "продолжить" + System.lineSeparator()
                        + "Lorem ipsum dolor sit amet, consectetur adipiscing elit." + System.lineSeparator()
                        + "продолжить" + System.lineSeparator()
                        + "Lorem ipsum dolor sit amet, consectetur adipiscing elit." + System.lineSeparator()
                        + "завершить" + System.lineSeparator()
        ));

        // restore console input
        System.setIn(systemIn);
    }

    @Test
    public void whenThreeStopTalkCommandThenNoReplyInOutputFile() {
        // redirect console input
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream((
                "стоп" + System.lineSeparator()
                        + "стоп" + System.lineSeparator()
                        + "стоп" + System.lineSeparator()
                        + "завершить").getBytes());
        InputStream systemIn = System.in;
        System.setIn(byteArrayInputStream);

        new ConsoleChat().chat(oneSentenceFile, chatLog);
        assertThat(getChatLog(), is(
                "стоп" + System.lineSeparator()
                        + "стоп" + System.lineSeparator()
                        + "стоп" + System.lineSeparator()
                        + "завершить" + System.lineSeparator()
        ));

        // restore console input
        System.setIn(systemIn);
    }

    @Test
    public void whenOnlyNewLineInTextFileThenOnlyNewLineAsReplyInChatAndOutputFile() {
        // redirect console input
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream((
                "aaa" + System.lineSeparator()
                        + "стоп" + System.lineSeparator()
                        + "bbb" + System.lineSeparator()
                        + "ccc" + System.lineSeparator()
                        + "продолжить" + System.lineSeparator()
                        + "ddd" + System.lineSeparator()
                        + "завершить").getBytes());
        InputStream systemIn = System.in;
        System.setIn(byteArrayInputStream);

        new ConsoleChat().chat(lineSeparatorFile, chatLog);
        assertThat(getChatLog(), is(
                "aaa" + System.lineSeparator()
                        + System.lineSeparator()
                        + "стоп" + System.lineSeparator()
                        + "bbb" + System.lineSeparator()
                        + "ccc" + System.lineSeparator()
                        + "продолжить" + System.lineSeparator()
                        + System.lineSeparator()
                        + "ddd" + System.lineSeparator()
                        + System.lineSeparator()
                        + "завершить" + System.lineSeparator()
        ));

        // restore console input
        System.setIn(systemIn);
    }

    @Test
    public void whenZeroLengthTextFileThenReplyAboutZeroLengthTextFileInChatAndOutputFile() {
        // redirect console input
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream((
                "aaa" + System.lineSeparator()
                        + "bbb" + System.lineSeparator()
                        + "завершить").getBytes());
        InputStream systemIn = System.in;
        System.setIn(byteArrayInputStream);

        new ConsoleChat().chat(zeroLengthFile, chatLog);
        assertThat(getChatLog(), is(
                "aaa" + System.lineSeparator()
                        + "Size of text file is 0." + System.lineSeparator()
                        + "bbb" + System.lineSeparator()
                        + "Size of text file is 0." + System.lineSeparator()
                        + "завершить" + System.lineSeparator()
        ));

        // restore console input
        System.setIn(systemIn);
    }

    @Test
    public void whenChatWithThousandUserInputsThenChatWorksProperly() {
        // generate thousand user input string with random continue and stop talk commands
        StringBuilder thousandUserInput = new StringBuilder();
        String candidateChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        for (int i = 0; i < 1000; i++) {
            char randomChar = candidateChars.charAt(new Random().nextInt(candidateChars.length()));
            thousandUserInput.append(randomChar).append(System.lineSeparator());
            if (new Random().ints(0, 5).findFirst().getAsInt() == 1) {
                thousandUserInput.append("стоп").append(System.lineSeparator());
            } else {
                thousandUserInput.append("продолжить").append(System.lineSeparator());
            }
        }
        thousandUserInput.append("завершить");

        // redirect console input
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(thousandUserInput.toString().getBytes());
        InputStream systemIn = System.in;
        System.setIn(byteArrayInputStream);

        new ConsoleChat().chat(textFile, chatLog);
        assertThat(getChatLog(), containsString("Lorem"));
        assertThat(getChatLog(), containsString("стоп"));
        assertThat(getChatLog(), containsString("продолжить"));
        assertThat(getChatLog(), containsString("завершить"));

        // restore console input
        System.setIn(systemIn);
    }

}
