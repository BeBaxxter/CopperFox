package main;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * This Class implements a simple Postix
 * @author lucas
 * @author max (for exercises 4 and 5)
 * @version 0.1
 */

public class Postfix {

    public static void main (String args[]) {

        Postfix pf = new Postfix();
        // System.out.println(pf.evaluatePostFix("1 2 + 3 4 / + 5 + 6 7 8 + * +"));
        //System.out.println(pf.infixToPostfix("( 1 + 2 ) * 3 + ( 4 ^ ( 5 - 6 ) )"));

        //Start program on the console
        // pf.calculationConsole();
        }

    /**
     * Evaluate the value of given postfix expression
     * Patter: Only numbers and operators separated with space
     * @param pfx
     * @return
     */
    public String evaluatePostFix (String pfx) {

        StackAsList<String> stack = new StackAsList<>();
        String[] t = pfx.split(Pattern.quote(" "));

        for (int i = 0; i < t.length; i++) {

            int rhs;
            int lhs;

            if (t[i].equals("+") || t[i].equals("-") || t[i].equals("*") || t[i].equals("/") || t[i].equals("^")) {

                rhs = Integer.parseInt(stack.pop());
                lhs = Integer.parseInt(stack.pop());

                if(t[i].equals("+")) {
                    stack.push(((lhs + rhs)+ ""));
                }

                if(t[i].equals("-")) {
                    stack.push(((lhs - rhs)+ ""));
                }

                if(t[i].equals("*")) {
                    stack.push(((lhs * rhs)+ ""));
                }

                if(t[i].equals("/")) {
                    stack.push(((lhs / rhs)+ ""));
                }

                if(t[i].equals("^")) {
                    int intResult = (int) Math.pow(lhs, rhs);
                    stack.push(((intResult)+ ""));
                }

            } else {
                stack.push(t[i]);
            }

        }
        return stack.pop();
    }

    /**
     * Converts given infix expression to postfix expression
     * Patter: Only numbers and operators separated with space
     * @param tokens
     * @return
     */
    public String infixToPostfix (String tokens) {

        StackAsList<String> stack = new StackAsList<>();
        String[] t = tokens.split(Pattern.quote(" "));
        String result = "";

        for (int i = 0; i < t.length; i++) {

            if(t[i].equals("(")) {
                stack.push(t[i]);
            }
            else if(t[i].equals(")")) {
                String latest = stack.pop();
                while(!latest.equals("(")){
                    result = result + " " + latest;
                    latest = stack.pop();
                }
            }else if (t[i].equals("+") || t[i].equals("-") || t[i].equals("*") || t[i].equals("/") || t[i].equals("^")) {

              String top = stack.pop();
                while (calculatePrecedence(t[i], top)) {
                    result = result + " " + top;
                    top = stack.pop();
                }
                stack.push(top);
                stack.push(t[i]);

            } else {
                result = result + " " + t[i];
            }
        }

        String last = stack.pop();

        while (last != null) {
            result = result + " " + last;
            last = stack.pop();
        }

        return result;
    }

    /**
     * Calculate the precedence between the given operators
     * @param newV
     * @param oldV
     * @return true, you can put it inside the stack
     * @return false, you have to pop the last element from stack
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
            else if((newV.equals("+") || newV.equals("-")) && (!newV.equals("+") || !newV.equals("-"))) {
                return true;
            }

        }

        return false;
    }

    /**
     * Infix calculation with user-input
     */
    private void calculationConsole(){
        System.out.println("Paste an Infix-Calculation here:");
        Scanner scanner = new Scanner(System.in);
        try {
            while (true) {
                String line = scanner.nextLine();
                try {
                    String[] str = line.split("");
                    String s = "";
                    String p = "";
                    for (int i = 0; i < str.length; i++){
                        if(Pattern.compile("[1-9]").matcher(str[i]).matches() && Pattern.compile("[1-9]").matcher(p).matches()){
                            s = s + str[i];
                        }else {
                            s = s + " " + str[i];
                        }
                        p = str[i];
                    }
                    String pf = infixToPostfix(s);
                    System.out.println("Result: " + evaluatePostFix(pf));
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        scanner.close();
    }
}
