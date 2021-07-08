package main.calculator;

import main.calculator.sets.CalcEngineSets;
import main.calculator.sets.UserInterfaceSets;

public class ExecuteSetCalculator {

    public static void main(String[] args) {

        CalcEngineSets calcEngine = new CalcEngineSets();
        UserInterfaceSets userInterface = new UserInterfaceSets(calcEngine);
        userInterface.setVisible(true);

    }
}
