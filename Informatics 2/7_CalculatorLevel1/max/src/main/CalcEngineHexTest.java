package main;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CalcEngineHexTest {

    @BeforeEach
    void setUp() throws Exception {
    }


    @Test
    void testAdd() {
        CalcEngineHex c = new CalcEngineHex();
        c.numberPressed(12);
        c.plus();
        c.numberPressed(4);
        c.equals();
        assertEquals(16, c.displayValue);
    }

    @Test
    void testSubtract() {
        CalcEngineHex c = new CalcEngineHex();
        c.numberPressed(15);
        c.numberPressed(10);
        c.minus();
        c.numberPressed(10);
        c.equals();
        assertEquals(240, c.displayValue);
    }

    @Test
    void testMulti() {
        CalcEngineHex c = new CalcEngineHex();
        c.numberPressed(10);
        c.multiply();
        c.numberPressed(4);
        c.equals();
        assertEquals(40, c.displayValue);
    }

    @Test
    void testDivide() {
        CalcEngineHex c = new CalcEngineHex();
        c.numberPressed(10);
        c.divide();
        c.numberPressed(2);
        c.equals();
        assertEquals(5, c.displayValue);
    }
}

