package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileHandler {

    BufferedWriter bw = null;

    /**
     * read a specific file from given path
     * @param path
     * @return
     */
    public File getFileFromPath(String path) {

        Path filePath = Paths.get(path);
        File inputFile = new File(String.valueOf(filePath));

        return inputFile;
    }

    /**
     * Writes given String to FilePath
     * @param FilePath
     * @param str
     * @throws IOException
     */
    public void writeDataToFile(String FilePath, String str) throws IOException {

        File file = new File(FilePath);
        FileWriter fw = new FileWriter(file);

        bw = new BufferedWriter(fw);
        bw.write(str);

        System.out.println("File written Successfully");
        bw.close();
    }

}
