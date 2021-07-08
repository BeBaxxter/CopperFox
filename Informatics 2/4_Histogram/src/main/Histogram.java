package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Histogram {

    private HashMap<Character, Integer> histogramMap = new HashMap<Character, Integer>();
    private FileHandler fileHandler = new FileHandler();
    private String readFromFile = "/Users/baxxter/Doghouse/03 Uni/2 Semester/Informatik 2/LabInfo2/workspace/Lab_4/katja.src.src/main/resources/dummy.txt";
    private String readToFile = "/Users/baxxter/Doghouse/03 Uni/2 Semester/Informatik 2/LabInfo2/workspace/Lab_4/katja.src.src/main/resources/frequency.txt";

    /**
     * creates a new histogram
     * @param args
     */
    public static void main (String args[]) throws IOException {

        Histogram histogram = new Histogram();
        histogram.generateHistogram();

    }

    /**
     * Generates a new histogram
     * @throws IOException
     */
    private void generateHistogram() throws IOException {
        /**
         * Read data from dummy.txt and write it to our HashMap histogramMap
         */
        parseDataFromFile(fileHandler.getFileFromPath(readFromFile));

        /**
         * draw the histogram and write it to file
         */
        fileHandler.writeDataToFile(readToFile, drawHistogram(histogramMap));
    }

    /**
     * Parse data from given file to histogramMap
     * @param inputFile
     * @throws FileNotFoundException
     */
    private void parseDataFromFile(File inputFile) throws FileNotFoundException {

        Scanner inputScanner = new Scanner(inputFile);
        char key;

        while (inputScanner.hasNext()) {

            /**
             * Assign the input to key and convert it to uppercase to avoid a A behavior
             */
            key = inputScanner.next().charAt(0);
            key = Character.toUpperCase(key);

            /**
             * If key is not inside the map, i will it with value 1
             * If the key already inside the Map i add +1 to the existing value
             */
            if(!histogramMap.containsKey(key)) {
                histogramMap.put(key, 1);
            } else {
                histogramMap.put(key, histogramMap.get(key) + 1);
            }
        }
        /**
         * Close scanner after use
         */
        inputScanner.close();
    }

    /**
     * Draw a Histogram with the given Map
     * @param histogramMap a Map with String, Integer pattern
     * @return
     */
    private String drawHistogram(HashMap<Character, Integer> histogramMap) {

        String outputString = "";

        /**
         * I prepare here already everything that the histogram can easy be written to a file.
         * That means i put everything together in a simple String and divide the lines with a
         * linebreak. The result of this method can easily written into a file.
         */
        for (Character i : histogramMap.keySet()) {

            /**
             * Output for exercise 3
             */
            //outputString = outputString + i  + ":   " + histogramMap.get(i) + "\n";


            /**
             * Pattern: character:  + dots in * + \n
             * Like: A: *********** \n
             */
            outputString = outputString + i  + ":   " + calculateDots(histogramMap.get(i), 25) + "\n";
        }
        return outputString;
    }

    /**
     * Calculate a String of stars (*) based on the input length.
     * Size of output is divided by 5 so that the String is not to big.
     *
     * @param length of the String
     * @param divideFactor if you want to shorten the length of *
     * @return String of *
     */
    private String calculateDots(int length, int divideFactor) {

        String starCount = "";

        /**
         * If the length is only 1 we dont divided anything
         */
        if(length == 1) { return "*"; }

        /**
         * We divided the length by divideFactor so that a lenght of 10000 is better to read.
         */
        for(int i = 0; i <= length / divideFactor; i++) {
            starCount = starCount + "*";
        }
        return starCount;
    }

}
