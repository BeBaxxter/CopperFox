package main;

public class CalcEngineHex extends CalcEngine{

    public CalcEngineHex(){
        super();
    }


    public void numberPressed(int number) {
        if(buildingDisplayValue) {
            // Incorporate this digit.
            displayValue = displayValue*16 + number;
        }
        else {
            // Start building a new number.
            displayValue = number;
            buildingDisplayValue = true;
        }
    }


}
