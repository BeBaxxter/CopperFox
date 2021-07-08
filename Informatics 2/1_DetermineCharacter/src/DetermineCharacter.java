/**
 * This class describe a implementation for a fictive eu flag after brexit.
 * @author lcarmohn, rwettstädt
 * @version 0.1
 */

public class DetermineCharacter {

    /**
     * Main method call that execute determineCharacter in a loop to generate the flag.
     * @param args
     */
    public static void main (String args[]){

        String outputLine;
        for (int row = 1; row <= 40; row++){
            outputLine = "";
            for (int column = 1; column <= 40; column++){
                outputLine = outputLine + determineCharacter (column, row);
            }
            System.out.println (outputLine);
        }
    }

    /**
     * Class to determine the character for every row and col of the flag.
     * @param col
     * @param row
     * @return
     */
    public static char determineCharacter(int col, int row) {

        if (row <= 2 || row >= 39 || col <= 2 || col >= 39) {
            return '?';
        }

        if (row + col == 41) {
            return '+';
        }

        if (col == 17 && row < 17 && row > 2) {
            return '|';
        }

        if (row == 17 && col < 18 && col > 2) {
            return '-';
        }

        if ((col + row) % 3 == 0 && row < 17 && col < 17) {
            return '/';
        }

        for (int i = 7; i < 32; i = i + 3) {
            if ((col + row) == i && row < 17 && col < 17){
                return '=';
            }
        }

        for (int i = 42; i < 77; i++) {
            if ((col + row) == i && row < 40 && col < 40 && row % 3 == 0){
                return ')';
            }

        }

        for (int i = 5; i <= 35; i = i + 5) {
            if (row > 17 && row < 36 && col == i && (col + row) <= 40) {
                return '(';
            }
        }

        for (int i = 20; i <= 38; i = i + 5) {
            if (row > 2 && row < 21 && col == i && (col + row) <= 40) {
                return '(';
            }
        }

        /**
         * return a space character for fields that dont have any special character
         */
        return ' ';
    }
}
