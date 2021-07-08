package main.rpn;

import java.util.regex.Pattern;

/**
 * This Class implements a simple Postix
 * @author lucas
 * @author max (for exercises 4 and 5)
 * @version 0.2
 */

public class Postfix {

    /**
     * Evaluate the value of given postfix expression
     * Patter: Only numbers and operators separated with space
     * @param pfx postfix value
     * @return evaluated postfix value
     */
    public String evaluatePostFix (String pfx) {

        StackAsList<String> stack = new StackAsList<>();
        String[] t = pfx.split(Pattern.quote(" "));

        for (String s : t) {

            float rhs;
            float lhs;

            if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/") || s.equals("^")) {

                rhs = Float.parseFloat(stack.pop());
                lhs = Float.parseFloat(stack.pop());

                if (s.equals("+")) {
                    stack.push(((lhs + rhs) + ""));
                }

                if (s.equals("-")) {
                    stack.push(((lhs - rhs) + ""));
                }

                if (s.equals("*")) {
                    stack.push(((lhs * rhs) + ""));
                }

                if (s.equals("/")) {
                    stack.push(((lhs / rhs) + ""));
                }

                if (s.equals("^")) {
                    float result = (float) Math.pow(lhs, rhs);
                    stack.push(((result) + ""));
                }

            } else {
                stack.push(s);
            }

        }
        return stack.pop();
    }

    /**
     * Converts given infix expression to postfix expression
     * Patter: Only numbers and operators separated with space
     * @param infixValue infix value as a string
     * @return postfix value as a string
     */
    public String infixToPostfix (String infixValue) {

        StackAsList<String> stack = new StackAsList<>();
        String[] t = infixValue.split(Pattern.quote(" "));
        StringBuilder result = new StringBuilder();

        for (String s : t) {

            switch (s) {
                case "(" -> stack.push(s);
                case ")" -> {
                    String latest = stack.pop();
                    while (!latest.equals("(")) {
                        result.append(" ").append(latest);
                        latest = stack.pop();
                    }
                }
                case "+", "-", "*", "/", "^" -> {
                    String top = stack.pop();
                    while (calculatePrecedence(s, top)) {
                        result.append(" ").append(top);
                        top = stack.pop();
                    }
                    stack.push(top);
                    stack.push(s);
                }
                default -> result.append(" ").append(s);
            }
        }

        String last = stack.pop();

        while (last != null) {
            result.append(" ").append(last);
            last = stack.pop();
        }

        return result.toString();
    }

    /**
     * Calculate the precedence between the given operators
     * @param newV new value
     * @param oldV old value
     * @return
     * true, you can put it inside the stack
     * false, you have to pop the last element from stack
     */
    public boolean calculatePrecedence(String newV, String oldV) {

        if(newV != null && oldV != null) {

            //Wenn letzter Operator "(", diesen dann ignorieren
            if(oldV.equals("(")) {return false;}

            //Gleichheitsfall
            if(newV.equals(oldV)) {
                return true;
            }
            //positiver fall wenn man drauf packen darf
            else return newV.equals("+") || newV.equals("-");

        }

        return false;
    }

}
