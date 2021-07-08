package test.rpn;

import main.rpn.Postfix;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PostfixTest {

    Postfix pf = new Postfix();

    @Test
    @DisplayName("Test if evaluate a postfix value with positive result works correctly")
    void testEvaluatePostFixWithPositiveResult() {
        assertEquals("98.75", pf.evaluatePostFix("1 2 + 3 4 / + 5 + 6 7 8 + * +"));
    }

    @Test
    @DisplayName("Test if evaluate a postfix value with negative result works correctly")
    void testEvaluatePostFixWithNegativeResult() {
        assertEquals("-1011.0", pf.evaluatePostFix("1 2 3 * + 4 5 ^ - 6 +"));
    }

    @Test
    @DisplayName("Convert a infix to postfix [1]")
    void testInfixToPostfixCase_1() {
        assertEquals( " 23 3 + 400 + 300 -", pf.infixToPostfix("23 + 3 + 400 - 300"));
    }

    @Test
    @DisplayName("Convert a infix to postfix [2]")
    void testInfixToPostfixCase_2() {
        assertEquals(" 1 2 3 * +", pf.infixToPostfix("1 + 2 * 3"));
    }

    @Test
    @DisplayName("Convert a infix to postfix [3]")
    void testInfixToPostfixCase_3() {
        assertEquals(" 1 2 + 3 4 ^ -", pf.infixToPostfix("1 + 2 - 3 ^ 4"));
    }

    @Test
    @DisplayName("Convert a infix to postfix [4]")
    void testInfixToPostfixCase_4() {
        assertEquals(" 1 2 ^ 3 4 * -", pf.infixToPostfix("1 ^ 2 - 3 * 4"));
    }

    @Test
    @DisplayName("Convert a infix to postfix [5]")
    void testInfixToPostfixCase_5() {
        assertEquals(" 1 2 3 * + 4 5 ^ - 6 +", pf.infixToPostfix("1 + 2 * 3 - 4 ^ 5 + 6"));
    }

    @Test
    @DisplayName("Convert a infix to postfix [6]")
    void testInfixToPostfixCase_6() {
        assertEquals(" 1 2 + 3 * 4 5 6 - ^ +", pf.infixToPostfix("( 1 + 2 ) * 3 + ( 4 ^ ( 5 - 6 ) )"));
    }

    @Test
    @DisplayName("Convert a infix to postfix [7]")
    void testInfixToPostfixCase_7() {
        assertEquals(" 1 2 + 3 4 / + 5 + 6 7 8 + * +", pf.infixToPostfix("1 + 2 + 3 / 4 + 5 + 6 * ( 7 + 8 )"));
    }

    @Test
    @DisplayName("Convert a infix to postfix [8]")
    void testInfixToPostfixCase_8() {
        assertEquals(" 9 1 - 2 - 3 2 * - 1 -", pf.infixToPostfix("9 - 1 - 2 - 3 * 2 - 1"));
    }

}