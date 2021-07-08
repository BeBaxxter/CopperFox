package test.calculator;

import main.calculator.decimal.CalcEngine;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalcEngineTest {

    @Test
    @DisplayName("Test if adding two values works correctly")
    void testAdd() {
        CalcEngine c = new CalcEngine();
        c.numberPressed(2);
        c.plus();
        c.numberPressed(4);
        c.equals();
        assertEquals(6, c.displayValue);
    }

    @Test
    @DisplayName("Test if subtracting two values works correctly")
    void testSubtract() {
        CalcEngine c = new CalcEngine();
        c.numberPressed(9);
        c.minus();
        c.numberPressed(3);
        c.equals();
        assertEquals(6, c.displayValue);
    }

    @Test
    @DisplayName("Test if multiply two values works correctly")
    void testMulti() {
        CalcEngine c = new CalcEngine();
        c.numberPressed(2);
        c.multiply();
        c.numberPressed(4);
        c.equals();
        assertEquals(8, c.displayValue);
    }

    @Test
    @DisplayName("Test if divide two values works correctly")
    void testDivide() {
        CalcEngine c = new CalcEngine();
        c.numberPressed(8);
        c.divide();
        c.numberPressed(4);
        c.equals();
        assertEquals(2, c.displayValue);
    }
}
