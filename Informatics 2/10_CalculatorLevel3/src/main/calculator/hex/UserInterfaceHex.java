package main.calculator.hex;

import main.calculator.decimal.UserInterface;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.regex.Pattern;

public class UserInterfaceHex extends UserInterface {

    public static JPanel buttonPanel = new JPanel(new GridLayout(6, 5));

    /**
     * Generic buttons
     */
    JToggleButton toggleButton;
    JButton a,b,c,d,e,f;

    /**
     * Constructor
     */
    public UserInterfaceHex(CalcEngineHex engine){
        super(engine);
        calc.setMode(16);
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
        buttonPanel.add(new JLabel(" "));
        buttonPanel.add(new JLabel(" "));


        addButton(buttonPanel, "1");
        addButton(buttonPanel, "2");
        addButton(buttonPanel, "3");
        buttonPanel.add(new JLabel(" "));
        buttonPanel.add(new JLabel(" "));

        buttonPanel.add(new JLabel(" "));
        addButton(buttonPanel, "0");
        buttonPanel.add(new JLabel(" "));
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

        if(command.equals("0") ||
                command.equals("1") ||
                command.equals("2") ||
                command.equals("3") ||
                command.equals("4") ||
                command.equals("5") ||
                command.equals("6") ||
                command.equals("7") ||
                command.equals("8") ||
                command.equals("9")) {
            int number = Integer.parseInt(command);
            calc.numberPressed(number);
        }
        else if(Pattern.compile("[a-fA-F]").matcher(command).matches()){
            int number = Integer.parseInt(command, 16);
            calc.numberPressed(number);
        }

        else if(command.equals("+")) {
            calc.plus();
        }
        else if(command.equals("-")) {
            calc.minus();
        }
        else if(command.equals("/")) {
            calc.divide();
        }
        else if(command.equals("*")) {
            calc.multiply();
        }
        else if(command.equals("=")) {
            calc.equals();
        }
        else if(command.equals("AC")) {
            calc.clear();
        }
        else if(command.equals("?")) {
            showInfo();
        }
        else if (command.equals("DEC")) {
            toggleButton.setText("HEX");
            calc.setMode(16);
        }
        else if (command.equals("HEX")) {
            toggleButton.setText("DEC");
            calc.setMode(10);
        }

        if(toggleButton.getText().equals("HEX")) {
            redisplay();
            enableHexLayout();
        }
        else {
            super.redisplay();
            disableHexLayout();
        }
    }

    /**
     * Redisplay the output of calculator
     */
    public void redisplay() {
        String text = Integer.toHexString(calc.getDisplayValue());
        display.setText("" + text);
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
