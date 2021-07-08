package test.calculator;

import main.calculator.string.CalcEngineString;
import main.calculator.string.UserInterfaceString;
import main.rpn.Postfix;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserInterfaceStringTest {

    @Test
    @DisplayName("Test if enable the hex-layout works correctly")
    void enableHexLayout() {
        CalcEngineString engine = new CalcEngineString();
        UserInterfaceString us = new UserInterfaceString(engine);
        assertEquals("HEX", us.toggleButton.getText());
    }

    @Test
    @DisplayName("Test if disable the hex-layout works correctly")
    void disableHexLayout() {
        CalcEngineString engine = new CalcEngineString();
        UserInterfaceString us = new UserInterfaceString(engine);
        us.toggleButton.setText("DEC");
        assertEquals("DEC", us.toggleButton.getText());
    }

    @Test
    @DisplayName("Check if the precedence of values works correctly [1]")
    void precedenceOfValues_run1() {
        Postfix pf = new Postfix();
        assertEquals(" 2 1 * 3 +", pf.infixToPostfix("2 * 1 + 3"));

    }

    @Test
    @DisplayName("Check if the precedence of values works correctly [2]")
    void precedenceOfValues_run2() {
        Postfix pf = new Postfix();
        assertEquals(" 2 3 1 * +", pf.infixToPostfix("2 + 3 * 1"));
    }

    @Test
    @DisplayName("Check if the precedence of values works correctly [3]")
    void precedenceOfValues_run3() {
        Postfix pf = new Postfix();
        assertEquals(" 2 3 * 4 * 1 + 1 +", pf.infixToPostfix("2 * 3 * 4 + 1 + 1"));
    }

    @Test
    @DisplayName("Check if the precedence of values works correctly [4]")
    void precedenceOfValues_run4() {
        Postfix pf = new Postfix();
        assertEquals(" 1 1 + 4 3 * 2 * +", pf.infixToPostfix("1 + 1 + 4 * 3 * 2"));
    }

    @Test
    @DisplayName("Check if the precedence of values works correctly [5]")
    void precedenceOfValues_run5() {
        Postfix pf = new Postfix();
        assertEquals(" 300 1000 2 * +", pf.infixToPostfix("300 + 1000 * 2"));
    }

    @Test
    @DisplayName("Check if the precedence of values works correctly [6]")
    void precedenceOfValues_run6() {
        Postfix pf = new Postfix();
        assertEquals(" 1000 2 * 300 +", pf.infixToPostfix("1000 * 2 + 300"));
    }

    @Test
    @DisplayName("Check if the precedence of values works correctly [7]")
    void precedenceOfValues_run7() {
        Postfix pf = new Postfix();
        assertEquals(" 20 2 4 / * 2 + 5 -", pf.infixToPostfix("20 * 2 / 4 + 2 - 5"));
    }

    @Test
    @DisplayName("Check if the precedence of values works correctly [8]")
    void precedenceOfValues_run8() {
        Postfix pf = new Postfix();
        assertEquals(" 2 5 - 4 10 20 * / +", pf.infixToPostfix("2 - 5 + 4 / 10 * 20"));
    }

    @Test
    @DisplayName("Check if the precedence of values works correctly [9]")
    void precedenceOfValues_run9() {
        Postfix pf = new Postfix();
        assertEquals(" 2 5 2 + 4 / *", pf.infixToPostfix("2 * ( 5 + 2 ) / 4"));
    }

    @Test
    @DisplayName("Check if the precedence of values works correctly [10]")
    void precedenceOfValues_run10() {
        Postfix pf = new Postfix();
        assertEquals(" 4 5 2 + 2 * /", pf.infixToPostfix("4 / ( 5 + 2 ) * 2"));
    }

    @Test
    @DisplayName("Check if the precedence of values works correctly [11]")
    void precedenceOfValues_run11() {
        Postfix pf = new Postfix();
        assertEquals(" 4 500 + 200 1000 (6 * / - 5 7 / + 8) -", pf.infixToPostfix("4 + 500 - 200 / 1000 * (6 + 5 / 7 - 8)"));
    }

    @Test
    @DisplayName("Check if the precedence of values works correctly [12]")
    void precedenceOfValues_run12() {
        Postfix pf = new Postfix();
        assertEquals(" (6 5 7 / + 8) 1000 200 / * - 500 - 4 +", pf.infixToPostfix("(6 + 5 / 7 - 8) * 1000 / 200 - 500 + 4"));
    }

    @Test
    @DisplayName("Check if the precedence of values works correctly [13]")
    void precedenceOfValues_run13() {
        Postfix pf = new Postfix();
        assertEquals(" (6 5 7 / + 8) -", pf.infixToPostfix("(6 + 5 / 7 - 8)"));
    }

    @Test
    @DisplayName("Check if the precedence of values works correctly [14]")
    void precedenceOfValues_run14() {
        Postfix pf = new Postfix();
        assertEquals(" (6 5 7 / + 8) - (6 + 5 7 / + 8) -", pf.infixToPostfix("(6 + 5 / 7 - 8) + (6 + 5 / 7 - 8)"));
    }

    @Test
    @DisplayName("Check if the precedence of values works correctly [15]")
    void precedenceOfValues_run15() {
        Postfix pf = new Postfix();
        assertEquals(" (6 5 7 / + 8) (6 / - 5 7 / + 8) (6 * - 5 7 / + 8) - (6 + 5 7 / + 8) - (6 - 5 7 / + 8) -", pf.infixToPostfix("(6 + 5 / 7 - 8) / (6 + 5 / 7 - 8) * (6 + 5 / 7 - 8) + (6 + 5 / 7 - 8) - (6 + 5 / 7 - 8)"));
    }

}