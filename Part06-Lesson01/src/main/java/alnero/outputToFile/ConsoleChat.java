package alnero.outputToFile;

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
    /**
     * Get random string constrained between two line separators from text file.
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
            // add part of line from seek position to next System.lineSeparator()
            sb.append(file.readLine());
            // add part of line from previous System.lineSeparator() to seek position
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
     * Main chat method.
     * @param textFile source file for random replies
     * @param outputFile all chat is saved to this file
     */
    public void chat(File textFile, File outputFile) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        Optional<String> isSilenceOn = Optional.ofNullable(null);
        try (PrintWriter logger = new PrintWriter(new BufferedOutputStream(new FileOutputStream(outputFile)))) {
            while (!"завершить".equals(line)) {
                logger.println(line);
                switch (line) {
                    case "стоп":
                        isSilenceOn = Optional.of(line);
                        break;
                    case "продолжить":
                        isSilenceOn = Optional.ofNullable(null);
                    default:
                        if (isSilenceOn.isEmpty()) {
                            String str = getRandomString(textFile);
                            System.out.println(str);
                            logger.println(str);
                        }
                        break;
                }
                line = sc.nextLine();
            }
            logger.println(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
