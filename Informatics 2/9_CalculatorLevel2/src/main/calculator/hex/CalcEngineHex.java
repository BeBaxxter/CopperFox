package main.calculator.hex;

import main.calculator.decimal.CalcEngine;

public class CalcEngineHex extends CalcEngine {


    public CalcEngineHex(){
        super();
    }

    /**
     * Calculate the dec / hex output
     * @param number The number pressed on the calculator.
     */
    public void numberPressed(int number) {
        if(buildingDisplayValue) {
            // Incorporate this digit.
            displayValue = displayValue * mode + number;
        }
        else {
            // Start building a new number.
            displayValue = number;
            buildingDisplayValue = true;
        }
    }
}
