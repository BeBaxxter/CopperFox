package main;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.regex.Pattern;

public class UserInterfaceHex extends UserInterface{

    public UserInterfaceHex(CalcEngine engine){
        super(engine);
    }

    protected void makeFrame()
    {
        frame = new JFrame(calc.getTitle());

        JPanel contentPane = (JPanel)frame.getContentPane();
        contentPane.setLayout(new BorderLayout(8, 8));
        contentPane.setBorder(new EmptyBorder( 10, 10, 10, 10));

        display = new JTextField();
        contentPane.add(display, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(6, 5));

        addButton(buttonPanel, "A");
        addButton(buttonPanel, "B");
        addButton(buttonPanel, "C");
        buttonPanel.add(new JLabel(" "));
        buttonPanel.add(new JLabel(" "));

        addButton(buttonPanel, "D");
        addButton(buttonPanel, "E");
        addButton(buttonPanel, "F");
        buttonPanel.add(new JLabel(" "));
        buttonPanel.add(new JLabel(" "));

        addButton(buttonPanel, "7");
        addButton(buttonPanel, "8");
        addButton(buttonPanel, "9");
        addButton(buttonPanel, "?");
        addButton(buttonPanel, "C");

        addButton(buttonPanel, "4");
        addButton(buttonPanel, "5");
        addButton(buttonPanel, "6");

        addButton(buttonPanel, "+");
        addButton(buttonPanel, "-");

        addButton(buttonPanel, "1");
        addButton(buttonPanel, "2");
        addButton(buttonPanel, "3");
        addButton(buttonPanel, "*");
        addButton(buttonPanel, "/");

        buttonPanel.add(new JLabel(" "));
        addButton(buttonPanel, "0");
        buttonPanel.add(new JLabel(" "));
        buttonPanel.add(new JLabel(" "));
        addButton(buttonPanel, "=");


        contentPane.add(buttonPanel, BorderLayout.CENTER);

        status = new JLabel(calc.getAuthor());
        contentPane.add(status, BorderLayout.SOUTH);

        frame.pack();
    }

    /**
     * An interface action has been performed.
     * Find out what it was and handle it.
     * @param event The event that has occured.
     */
    public void actionPerformed(ActionEvent event)
    {
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
        else if(command.equals("C")) {
            calc.clear();
        }
        else if(command.equals("?")) {
            showInfo();
        }
        // else unknown command.

        redisplay();
    }

    protected void redisplay()
    {
        String text = Integer.toHexString(calc.getDisplayValue());
        display.setText("" + text);
    }
}
