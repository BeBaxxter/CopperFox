package main;

import java.util.Locale;

public class Prelab {

    public static void main (String args[]) {

        Prelab prelab = new Prelab();


        System.out.println("Lowercase: " + prelab.charToLowercase('A'));
        System.out.println("Uppercase: " + prelab.charToUppercase('a'));

    }

    public char charToLowercase(char input) {
        return (char) (input + 32);
    }

    public char charToUppercase(char input) {
        return (char) (input - 32);
    }

}
