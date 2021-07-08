package test.calculator;

import main.calculator.string.CalcEngineString;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalcEngineStringTest {

    CalcEngineString engine = new CalcEngineString();

    @Test
    @DisplayName("Test if given string is a operator")
    void isOperator() {
        assertTrue(engine.isOperator("+"));
    }

    @Test
    @DisplayName("Test if given string is not a operator")
    void isNotOperator() {
        assertFalse(engine.isOperator("2"));
    }

    @Test
    @DisplayName("Test if converting from hex to decimal works correctly")
    void hexToDecString() {
        assertEquals("5425", engine.hexToDecString("1531"));
    }

    @Test
    @DisplayName("Test if converting from decimal to hex works correctly")
    void decToHexString() {
        assertEquals("0x1.531ep16", engine.decToHexString("86814"));
    }

    @Test
    @DisplayName("Test if setting spaces for operators works correctly")
    void addSpacebars() {
        assertEquals("L -  - oR + w!( e )m / M.", engine.addSpacebars("L--oR+w!(e)m/M."));
    }



}