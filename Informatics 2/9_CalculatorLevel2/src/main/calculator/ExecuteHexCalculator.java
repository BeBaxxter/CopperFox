package main.calculator;

import main.calculator.hex.CalcEngineHex;
import main.calculator.hex.UserInterfaceHex;

public class ExecuteHexCalculator {

    public static void main(String[] args) {

        CalcEngineHex calcEngine = new CalcEngineHex();
        UserInterfaceHex userInterface = new UserInterfaceHex(calcEngine);
        userInterface.setVisible(true);

    }
}
