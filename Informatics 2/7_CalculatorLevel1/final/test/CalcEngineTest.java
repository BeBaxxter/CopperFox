package test;

import static org.junit.jupiter.api.Assertions.*;

import main.calculator.decimal.CalcEngine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CalcEngineTest {

    @BeforeEach
    void setUp() throws Exception {
    }

    @Test
    void testAdd() {
        CalcEngine c = new CalcEngine();
        c.numberPressed(2);
        c.plus();
        c.numberPressed(4);
        c.equals();
        assertEquals(6, c.displayValue);
    }

    @Test
    void testSubtract() {
        CalcEngine c = new CalcEngine();
        c.numberPressed(9);
        c.minus();
        c.numberPressed(3);
        c.equals();
        assertEquals(6, c.displayValue);
    }

    @Test
    void testMulti() {
        CalcEngine c = new CalcEngine();
        c.numberPressed(2);
        c.multiply();
        c.numberPressed(4);
        c.equals();
        assertEquals(8, c.displayValue);
    }

    @Test
    void testDivide() {
        CalcEngine c = new CalcEngine();
        c.numberPressed(8);
        c.divide();
        c.numberPressed(4);
        c.equals();
        assertEquals(2, c.displayValue);
    }
}
