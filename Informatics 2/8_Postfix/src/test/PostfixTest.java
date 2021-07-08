package test;

import main.Postfix;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PostfixTest {

    Postfix pf = new Postfix();

    @Test
    @DisplayName("Test if evaluate a postfix value with positive result works correctly")
    void testEvaluatePostFixWithPositiveResult() {
        Assert.assertEquals("98", pf.evaluatePostFix("1 2 + 3 4 / + 5 + 6 7 8 + * +"));
    }

    @Test
    @DisplayName("Test if evaluate a postfix value with negative result works correctly")
    void testEvaluatePostFixWithNegativeResult() {
        Assert.assertEquals("-1011", pf.evaluatePostFix("1 2 3 * + 4 5 ^ - 6 +"));
    }

    @Test
    @DisplayName("Convert a infix to postfix [1]")
    void testInfixToPostfixCase_1() {
        Assert.assertEquals("", " 1 2 * 3 +", pf.infixToPostfix("1 * 2 + 3"));
    }

    @Test
    @DisplayName("Convert a infix to postfix [2]")
    void testInfixToPostfixCase_2() {
        Assert.assertEquals("", " 1 2 3 * +", pf.infixToPostfix("1 + 2 * 3"));
    }

    @Test
    @DisplayName("Convert a infix to postfix [3]")
    void testInfixToPostfixCase_3() {
        Assert.assertEquals("", " 1 2 + 3 4 ^ -", pf.infixToPostfix("1 + 2 - 3 ^ 4"));
    }

    @Test
    @DisplayName("Convert a infix to postfix [4]")
    void testInfixToPostfixCase_4() {
        Assert.assertEquals("", " 1 2 ^ 3 4 * -", pf.infixToPostfix("1 ^ 2 - 3 * 4"));
    }

    @Test
    @DisplayName("Convert a infix to postfix [5]")
    void testInfixToPostfixCase_5() {
        Assert.assertEquals("", " 1 2 3 * + 4 5 ^ - 6 +", pf.infixToPostfix("1 + 2 * 3 - 4 ^ 5 + 6"));
    }

    @Test
    @DisplayName("Convert a infix to postfix [6]")
    void testInfixToPostfixCase_6() {
        Assert.assertEquals("", " 1 2 + 3 * 4 5 6 - ^ +", pf.infixToPostfix("( 1 + 2 ) * 3 + ( 4 ^ ( 5 - 6 ) )"));
    }

    @Test
    @DisplayName("Convert a infix to postfix [7]")
    void testInfixToPostfixCase_7() {
        Assert.assertEquals("", " 1 2 + 3 4 / + 5 + 6 7 8 + * +", pf.infixToPostfix("1 + 2 + 3 / 4 + 5 + 6 * ( 7 + 8 )"));
    }

    @Test
    @DisplayName("Convert a infix to postfix [8]")
    void testInfixToPostfixCase_8() {
        Assert.assertEquals("", " 9 1 - 2 - 3 2 * - 1 -", pf.infixToPostfix("9 - 1 - 2 - 3 * 2 - 1"));
    }

}