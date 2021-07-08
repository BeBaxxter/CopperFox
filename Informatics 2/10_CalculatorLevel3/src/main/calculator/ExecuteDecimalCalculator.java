package main.calculator;

import main.calculator.decimal.CalcEngine;
import main.calculator.decimal.UserInterface;

public class ExecuteDecimalCalculator {

    public static void main(String[] args) {

        CalcEngine calcEngine = new CalcEngine();
        UserInterface userInterface = new UserInterface(calcEngine);
        userInterface.setVisible(true);

    }
}
