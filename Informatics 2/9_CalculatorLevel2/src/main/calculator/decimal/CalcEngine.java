package main.calculator.decimal;

/**
 * The main part of the calculator doing the calculations.
 * 
 * @author  David J. Barnes and Michael Kolling 
 * @version 2008.03.30
 */
public class CalcEngine
{
    // The calculator's state is maintained in three fields:
    //     buildingDisplayValue, haveLeftOperand, and lastOperator.
    
    // Are we already building a value in the display, or will the
    // next digit be the first of a new one?
    protected boolean buildingDisplayValue;
    // Has a left operand already been entered (or calculated)?
    protected boolean haveLeftOperand;
    // The most recent operator that was entered.
    protected char lastOperator;

    // The current value (to be) shown in the display.
    public int displayValue;
    // The value of an existing left operand.
    protected int leftOperand;

    protected int mode;

    /**
     * Create a main.calculator.decimal.CalcEngine.
     */
    public CalcEngine()
    {
        clear();
    }

    /**
     * @return The value that should currently be displayed
     * on the calculator display.
     */
    public int getDisplayValue()
    {
        return displayValue;
    }

    /**
     * A number button was pressed.
     * Either start a new operand, or incorporate this number as
     * the least significant digit of an existing one.
     * @param number The number pressed on the calculator.
     */
    public void numberPressed(int number) {
        if(buildingDisplayValue) {
            // Incorporate this digit.
            displayValue = displayValue * 10 + number;
        }
        else {
            // Start building a new number.
            displayValue = number;
            buildingDisplayValue = true;
        }
    }

    /**
     * The 'plus' button was pressed. 
     */
    public void plus()
    {
        applyOperator('+');
    }

    /**
     * The 'minus' button was pressed.
     */
    public void minus()
    {
        applyOperator('-');
    }

    /**
     * The 'divide' button was pressed.
     */
    public void divide() {
        applyOperator('/');
    }

    /**
     * The 'multiply' button was pressed.
     */
    public void multiply() {
        applyOperator('*');
    }

    /**
     * The '=' button was pressed.
     */
    public void equals() {
        // This should completes the building of a second operand,
        // so ensure that we really have a left operand, an operator
        // and a right operand.
        if(haveLeftOperand &&
                lastOperator != '?' &&
                buildingDisplayValue) {
            calculateResult();
            lastOperator = '?';
            buildingDisplayValue = false;
        }
        else {
            keySequenceError();
        }
    }

    /**
     * The 'C' (clear) button was pressed.
     * Reset everything to a starting state.
     */
    public void clear() {
        lastOperator = '?';
        haveLeftOperand = false;
        buildingDisplayValue = false;
        displayValue = 0;
    }

    /**
     * @return The title of this calculation engine.
     */
    public String getTitle()
    {
        return "Java main.Calculator";
    }

    /**
     * @return The author of this engine.
     */
    public String getAuthor()
    {
        return "David J. Barnes and Michael Kolling";
    }

    /**
     * @return The version number of this engine.
     */
    public String getVersion()
    {
       return "Version 1.0";
    }

    /**
     * Combine leftOperand, lastOperator, and the
     * current display value.
     * The result becomes both the leftOperand and
     * the new display value.
     */
    protected void calculateResult() {
        switch (lastOperator) {
            case '+' -> {
                displayValue = leftOperand + displayValue;
                haveLeftOperand = true;
                leftOperand = displayValue;
            }
            case '-' -> {
                displayValue = leftOperand - displayValue;
                haveLeftOperand = true;
                leftOperand = displayValue;
            }
            case '*' -> {
                displayValue = leftOperand * displayValue;
                haveLeftOperand = true;
                leftOperand = displayValue;
            }
            case '/' -> {
                displayValue = leftOperand / displayValue;
                haveLeftOperand = true;
                leftOperand = displayValue;
            }
            default -> keySequenceError();
        }
    }
    
    /**
     * Apply an operator.
     * @param operator The operator to apply.
     */
    protected void applyOperator(char operator) {
        // If we are not in the process of building a new operand
        // then it is an error, unless we have just calculated a
        // result using '='.
        if(!buildingDisplayValue &&
                    !(haveLeftOperand && lastOperator == '?')) {
            keySequenceError();
            return;
        }

        if(lastOperator != '?') {
            // First apply the previous operator.
            calculateResult();
        }
        else {
            // The displayValue now becomes the left operand of this
            // new operator.
            haveLeftOperand = true;
            leftOperand = displayValue;
        }
        lastOperator = operator;
        buildingDisplayValue = false;
    }

    /**
     * Report an error in the sequence of keys that was pressed.
     */
    protected void keySequenceError() {
        System.out.println("A key sequence error has occurred.");
        // Reset everything.
        clear();
    }

    /**
     * Sets the mode you are operating in
     * @param i the mode/divider you want to use (for e.g. 2/10/16)
     */
    public void setMode(int i){
        mode = i;
    }
}
