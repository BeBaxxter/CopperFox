package main.calculator.string;

import main.calculator.decimal.CalcEngine;

import java.util.Scanner;

public class CalcEngineString extends CalcEngine {


    public CalcEngineString(){
        super();
    }

    /**
     * Calculate the dec / hex output
     * @param number The number pressed on the calculator.
     */
    public void numberPressed(int number) {
        if(buildingDisplayValue) {
            // Incorporate this digit.
            displayValue = displayValue*mode + number;
        }
        else {
            // Start building a new number.
            displayValue = number;
            buildingDisplayValue = true;
        }
    }

    /**
     * Checks if given string is a operator
     * @param value operator as string
     * @return true if operator, false if not
     */
    public boolean isOperator(String value) {
        return value.equals("+") || value.equals("-") || value.equals("/") || (value.equals("*")) || (value.equals("(")) || (value.equals(")"));
    }

    /**
     * Converts hex-string to decimal-string
     * @param input hex number as string
     * @return decimal String of input
     */
    public String hexToDecString(String input) {
        StringBuilder output = new StringBuilder();
        Scanner in = new Scanner(input).useDelimiter(" ");
        while(in.hasNext()) {
            String hexStr = in.next();
            if (isOperator(hexStr)) {
                output.append(hexStr);
            } else if(hexStr.contains(".")) {
                String decimalNumber = String.valueOf(Float.parseFloat(hexStr));
                output.append(decimalNumber);
            } else output.append(Integer.parseInt(hexStr, 16));
        }
        in.close();
        output = new StringBuilder(addSpacebars(output.toString()));
        return output.toString();
    }

    /**
     * Converts decimal-string to hex-string
     * @param input decimal number as string
     * @return hex String of input
     */
    public String decToHexString(String input) {
        StringBuilder output = new StringBuilder();
        Scanner in = new Scanner(input).useDelimiter(" ");

        while(in.hasNext()) {
            String decStr = in.next();
            if (isOperator(decStr)) {
                output.append(decStr);
            } else {
                float hexNumber=Float.parseFloat(decStr);
                output.append(Float.toHexString(hexNumber));
            }
        }
        in.close();
        output = new StringBuilder(addSpacebars(output.toString()));
        return output.toString();
    }

    /**
     * Adds Spacebars before and after Operators in a String
     * (e.g.: 34+2*4+(4*4) -> 34 + 2 * 4 + ( 4 * 4 )
     * @param input as string
     * @return String with added spacebars
     */
    public String addSpacebars(String input) {
        Scanner in = new Scanner(input).useDelimiter("");
        StringBuilder output = new StringBuilder();

        while(in.hasNext()) {
            String str = in.next();
            if(!str.equals(" "))
                if (str.equals("+") || str.equals("-") || str.equals("/") || str.equals("*") || str.equals("^"))
                    output.append(" ").append(str).append(" ");
                else if (str.equals("(")) output.append("( ");
                else if (str.equals(")")) output.append(" )");
                else output.append(str);
        }
        in.close();
        return output.toString();
    }

}
