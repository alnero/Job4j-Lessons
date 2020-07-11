package alnero.outputToFile;

import alnero.readFile.SearchFiles;

import java.util.List;
import java.io.File;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipOutputStream;
import java.util.zip.ZipEntry;

/**
 * Create zip archives. Sources can be folders or single files.
 */
public class ZipArchiver {
    /**
     * Creat zip archive from list of files.
     * @param sources list of files to zip
     * @param target destination file
     */
    public void packFiles(List<File> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (File file : sources) {
                zip.putNextEntry(new ZipEntry(file.getPath()));
                try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(file))) {
                    zip.write(in.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create zip archive from single file.
     * @param source source file
     * @param target destination file
     */
    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Get list of files excluding files with certain extension.
     * @param root where to search
     * @param ext skip files with this extension
     * @return list of files
     * @throws IOException as defined by FileVisitor API used in SearchFiles class
     */
    public List<File> searchFilesExcludingExtension(File root, String ext) throws IOException {
        return new SearchFiles().searchFiles(root, file -> !file.getName().endsWith(ext));
    }

    /**
     * Create zip archive from folder. Supply correct arguments in proper format.
     * @param args -d=PATH_TO_FOLDER -e=FILE_EXTENSION -o=OUTPUT_FILE
     * @throws IOException as defined by FileVisitor API
     */
    public static void main(String[] args) throws IOException {
        ZipArgsParser argsParser = new ZipArgsParser(args);
        ZipArchiver archiver = new ZipArchiver();
        File sourceDirectory = new File(argsParser.directory());
        File target = new File(argsParser.output());
        List<File> sources = archiver.searchFilesExcludingExtension(sourceDirectory, argsParser.exclude());
        archiver.packFiles(sources, target);
    }
}
