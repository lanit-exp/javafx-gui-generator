package sample.utils;

import lombok.Cleanup;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class UtilsFile {

    public static void write(File file, String text) {
        try {
            @Cleanup BufferedWriter fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            fileWriter.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static List<File> getFilesTree(String path) {
        File folder = new File(path);
        File[] files = folder.listFiles();
        return Arrays.asList(files);
    }

    public static File getRandomFile(String path){
        List<File> UtilsFile = getFilesTree(path);
        return UtilsFile.get((int) Math.floor(Math.random() * UtilsFile.size()));
    }

}
