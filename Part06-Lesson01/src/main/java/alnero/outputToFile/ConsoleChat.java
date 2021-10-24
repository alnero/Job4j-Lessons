package alnero.outputToFile;

import java.nio.file.Files;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.Optional;
import java.util.Scanner;
import java.io.File;
import java.io.RandomAccessFile;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.IOException;

/**
 * Chat in console. Replies are random strings from supplied text file. Commands to stop/continue replies and finish chat are supported.
 */
public class ConsoleChat {
    /** Console constant to finish chat. */
    private static final String FINISH_CHAT = "завершить";
    /** Console constant to stop reply from chat program. */
    private static final String STOP = "стоп";
    /** Console constant to continue reply from chat program. */
    private static final String CONTINUE = "продолжить";

    /**
     * Get random string constrained between two line separators from text file.
     * Method takes random position in file and gets string which has this position and is enclosed between two line separators.
     * @param textFile source file with text
     * @return random string, message about empty file or null in case of exception
     */
    public String getRandomString(File textFile) {
        String result = null;
        try {
            StringBuilder sb = new StringBuilder();
            RandomAccessFile file = new RandomAccessFile(textFile, "r");
            long length = file.length();
            if (length == 0) {
                throw new EOFException("Size of text file is 0.");
            }
            long position = new Random().longs(0, length).findFirst().getAsLong();
            file.seek(position);
            sb.append(file.readLine());
            for (long i = position - 1; i >= 0; i--) {
                file.seek(i);
                String letter = Character.toString(file.read());
                if (System.lineSeparator().equals(letter)) {
                    break;
                } else {
                    sb.insert(0, letter);
                }
            }
            result = sb.toString();
        } catch (EOFException e) {
            result = e.getMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Get list of strings from text file.
     * If file is empty the list will have only one string: "Size of text file is 0."
     * @param textFile source file with text
     * @return list of strings
     */
    public List<String> getStrings(File textFile) {
        List<String> lines = null;
        try {
            if (textFile.length() == 0) {
                lines = new ArrayList<>();
                lines.add("Size of text file is 0.");
            } else {
                lines = Files.readAllLines(textFile.toPath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    /**
     * Save list of strings to file.
     * @param log list of strings
     * @param file destination file
     */
    public void saveStringLogToFile(List<String> log, File file) {
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(file)))) {
            for (String s : log) {
                out.println(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Main chat method.
     * @param textFile   source file for random replies
     * @param outputFile all chat is saved to this file
     */
    public void chat(File textFile, File outputFile) {
        List<String> chatLog = new ArrayList<>();
        List<String> stringCorpus = getStrings(textFile);
        int numOfStrings = stringCorpus.size();
        Optional<String> isSilenceOn = Optional.ofNullable(null);
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        while (!FINISH_CHAT.equals(line)) {
            chatLog.add(line);
            switch (line) {
                case STOP:
                    isSilenceOn = Optional.of(line);
                    break;
                case CONTINUE:
                    isSilenceOn = Optional.ofNullable(null);
                default:
                    if (isSilenceOn.isEmpty()) {
                        int random = new Random().ints(0, numOfStrings).findFirst().getAsInt();
                        String str = stringCorpus.get(random);
                        System.out.println(str);
                        chatLog.add(str);
                    }
                    break;
            }
            line = sc.nextLine();
        }
        chatLog.add(line);
        this.saveStringLogToFile(chatLog, outputFile);
    }
}
