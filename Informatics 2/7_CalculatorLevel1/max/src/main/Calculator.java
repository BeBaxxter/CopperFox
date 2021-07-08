package main;

public class Calculator {

    public static void main(String args []) {

        CalcEngineHex calcEngine = new CalcEngineHex();
        UserInterfaceHex userInterface = new UserInterfaceHex(calcEngine);

        userInterface.setVisible(true);

    }
}
