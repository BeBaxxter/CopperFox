package main.calculator.string;

import main.calculator.decimal.UserInterface;
import main.rpn.Postfix;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Scanner;

public class UserInterfaceString extends UserInterface {

    public static JPanel buttonPanel = new JPanel(new GridLayout(6, 5));
    Postfix pf = new Postfix();
    CalcEngineString engine = new CalcEngineString();

    /**
     * Generic values
     */
    public JToggleButton toggleButton;
    JButton a,b,c,d,e,f;
    String displayValue = "";

    /**
     * Constructor
     */
    public UserInterfaceString(CalcEngineString engine){
        super(engine);
        calc.setMode(10);
    }

    /**
     * Create and draw the calculator layout
     */
    protected void makeFrame() {
        frame = new JFrame(calc.getTitle());

        toggleButton = new JToggleButton("HEX");
        JPanel contentPane = (JPanel)frame.getContentPane();
        contentPane.setLayout(new BorderLayout(8, 8));
        contentPane.setBorder(new EmptyBorder( 10, 10, 10, 10));

        display = new JTextField();
        contentPane.add(display, BorderLayout.NORTH);

        a = addButton(buttonPanel, "a");
        b = addButton(buttonPanel, "b");
        c = addButton(buttonPanel, "c");
        addButton(buttonPanel, "?");
        addButton(buttonPanel, "AC");

        d = addButton(buttonPanel, "d");
        e = addButton(buttonPanel, "e");
        f = addButton(buttonPanel, "f");
        addButton(buttonPanel, "+");
        addButton(buttonPanel, "-");

        addButton(buttonPanel, "7");
        addButton(buttonPanel, "8");
        addButton(buttonPanel, "9");
        addButton(buttonPanel, "*");
        addButton(buttonPanel, "/");


        addButton(buttonPanel, "4");
        addButton(buttonPanel, "5");
        addButton(buttonPanel, "6");
        addButton(buttonPanel, "(");
        addButton(buttonPanel, ")");


        addButton(buttonPanel, "1");
        addButton(buttonPanel, "2");
        addButton(buttonPanel, "3");
        buttonPanel.add(new JLabel(" "));
        buttonPanel.add(new JLabel(" "));

        buttonPanel.add(new JLabel(" "));
        addButton(buttonPanel, "0");
        addButton(buttonPanel, ".");
        buttonPanel.add(toggleButton);
        addButton(buttonPanel, "=");


        toggleButton.addActionListener(this);

        contentPane.add(buttonPanel, BorderLayout.CENTER);

        status = new JLabel(calc.getAuthor());
        contentPane.add(status, BorderLayout.SOUTH);

        frame.pack();
    }

    /**
     * An interface action has been performed.
     * Find out what it was and handle it.
     * @param event The event that has occurred.
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        String command = event.getActionCommand();

        switch (command) {
            case "=":
                if (toggleButton.getText().equals("HEX")) {
                    //Hex to Dec
                    displayValue = engine.hexToDecString(displayValue);

                    //infix to postfix
                    String postfix = pf.infixToPostfix(displayValue);

                    //evaluate
                    displayValue = pf.evaluatePostFix(postfix);
                    //Back from dec to Hex
                    displayValue = engine.decToHexString(displayValue).toUpperCase();
                } else if (toggleButton.getText().equals("DEC")) {
                    //infix to postfix
                    String postfix = pf.infixToPostfix(displayValue);
                    //evaluate
                    displayValue = pf.evaluatePostFix(postfix);
                }
                break;
            case "HEX":
                toggleButton.setText("DEC");
                displayValue = engine.hexToDecString(displayValue);
                break;
            case "DEC":
                toggleButton.setText("HEX");
                displayValue = engine.decToHexString(displayValue).toUpperCase();

                break;
            case "AC":
                displayValue = "";
                break;
            case "?":
                showInfo();
                break;
            default:
                displayValue = displayValue + command;
                break;
        }

        if(toggleButton.getText().equals("HEX")) {
        	displayValue = displayValue.toUpperCase();
        	enableHexLayout();
        }
        else {
        	disableHexLayout();
        }
        displayValue = engine.addSpacebars(displayValue);
    	redisplay();
    }

    /**
     * Redisplay the output of calculator
     */
    public void redisplay() {
        display.setText("" + displayValue);
    }

    /**
     * Enable the Hex buttons
     */
    public void enableHexLayout() {
        a.setEnabled(true);
        b.setEnabled(true);
        c.setEnabled(true);
        d.setEnabled(true);
        e.setEnabled(true);
        f.setEnabled(true);
    }

    /**
     * Disable the Hex buttons
     */
    public void disableHexLayout() {
        a.setEnabled(false);
        b.setEnabled(false);
        c.setEnabled(false);
        d.setEnabled(false);
        e.setEnabled(false);
        f.setEnabled(false);
    }

}
