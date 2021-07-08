package main.calculator;

import main.calculator.string.CalcEngineString;
import main.calculator.string.UserInterfaceString;

public class ExecuteStringCalculator {

    public static void main(String[] args) {

        CalcEngineString calcEngine = new CalcEngineString();
        UserInterfaceString userInterface = new UserInterfaceString(calcEngine);
        userInterface.setVisible(true);

    }
}
