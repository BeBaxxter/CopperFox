package main;

import main.hex.CalcEngineHex;
import main.hex.UserInterfaceHex;

public class Calculator {

    public static void main(String args []) {

        /**
         * Create a new Calculator and combine the engine with the interface.
         */
        CalcEngineHex calcEngine = new CalcEngineHex();
        UserInterfaceHex userInterface = new UserInterfaceHex(calcEngine);
        userInterface.setVisible(true);

    }
}
