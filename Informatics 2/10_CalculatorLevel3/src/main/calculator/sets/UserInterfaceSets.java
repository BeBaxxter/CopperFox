package main.calculator.sets;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

/**
 * A graphical user interface for the calculator. No calculation is being
 * done here. This class is responsible just for putting up the display on 
 * screen. It then refers to the "CalcEngine" to do all the real work.
 *
 * @author  lcarmohn, xauber
 * @version 0.1
 */
public class UserInterfaceSets implements ActionListener {

    protected JTextField firstLineDisplay;
    protected JTextField secondLineDisplay;
    protected JTextField resultDisplay;

    protected CalcEngineSets calcSet;
    protected boolean showingAuthor;
    protected JFrame frame;
    protected JLabel status;

    /**
     * Create a user interface.
     * @param engine The calculator engine.
     */
    public UserInterfaceSets(CalcEngineSets engine) {
        calcSet = engine;
        showingAuthor = true;
        makeFrame();
        frame.setVisible(true);
    }

    /**
     * Set the visibility of the interface.
     * @param visible true if the interface is to be made visible, false otherwise.
     */
    public void setVisible(boolean visible)
    {
        frame.setVisible(visible);
    }

    /**
     * Make the frame for the user interface.
     */
    protected void makeFrame() {

        /**
         * Declaration
         */
        frame = new JFrame(calcSet.getTitle());
        firstLineDisplay = new JTextField();
        secondLineDisplay = new JTextField();
        resultDisplay = new JTextField();
        status = new JLabel(calcSet.getAuthor());

        JPanel contentPane = (JPanel)frame.getContentPane();
        JPanel headline = new JPanel(new GridLayout(2, 1));
        JPanel footer = new JPanel(new GridLayout(2, 1));

        /**
         * Set layout
         */
        contentPane.setLayout(new BorderLayout(2, 1));
        contentPane.setBorder(new EmptyBorder( 10, 10, 10, 10));

        /**
         * headline
         */
        headline.add(firstLineDisplay, BorderLayout.NORTH);
        headline.add(secondLineDisplay, BorderLayout.SOUTH);

        /**
         * Buttons
         */
        JPanel buttonPanel = new JPanel(new GridLayout(2, 4));
            addButton(buttonPanel, "Union");
            addButton(buttonPanel, "Intersection");
            addButton(buttonPanel, "Subtraction");
            addButton(buttonPanel, "Clear");
            addButton(buttonPanel, "Push Set A");
            addButton(buttonPanel, "Push Set B");
            addButton(buttonPanel, "Length Set A");
            addButton(buttonPanel, "Length Set B");


        /**
         * footer
         */
        footer.add(resultDisplay, BorderLayout.NORTH);
        footer.add(status, BorderLayout.SOUTH);



        /**
         * build full interface
         */
        contentPane.add(headline, BorderLayout.NORTH);
        contentPane.add(buttonPanel, BorderLayout.CENTER);
        contentPane.add(footer, BorderLayout.SOUTH);
        frame.pack();

    }

    /**
     * Add a button to the button panel.
     * @param panel The panel to receive the button.
     * @param buttonText The text for the button.
     */
    protected void addButton(Container panel, String buttonText) {
        JButton button = new JButton(buttonText);
        button.addActionListener(this);
        panel.add(button);
    }

    /**
     * An interface action has been performed.
     * Find out what it was and handle it.
     * @param event The event that has occurred.
     */
    public void actionPerformed(ActionEvent event) {
        String command = event.getActionCommand();

        /**
         * Give values to engine
         */
        calcSet.setSetA(calcSet.parseStringToSet(firstLineDisplay.getText()));
        calcSet.setSetB(calcSet.parseStringToSet(secondLineDisplay.getText()));
        calcSet.setSetResult(calcSet.parseStringToSet(resultDisplay.getText()));

        switch (command) {
            case "Union" -> calcSet.union();
            case "Intersection" -> calcSet.intersection();
            case "Subtraction" -> calcSet.subtraction();
            case "Clear" -> calcSet.clear();
            case "Push Set A" -> calcSet.pushSetAtoB();
            case "Push Set B" -> calcSet.pushSetBtoA();
            case "Length Set A" -> calcSet.lengthA();
            case "Length Set B" -> calcSet.lengthB();

        }
        // else unknown command.

        redisplay();
    }

    /**
     * Update the interface display to show the current value of the 
     * calculator.
     */
    public void redisplay() {
        /**
         * get the value
         */
        firstLineDisplay.setText(removeBrackets(calcSet.getSetA()));
        secondLineDisplay.setText(removeBrackets(calcSet.getSetB()));
        resultDisplay.setText(removeBrackets(calcSet.getSetResult()));

    }

    /**
     * Remove unused brackets from given input
     * @param setWithBrackets as input
     * @return string without brackets
     */
    private String removeBrackets(Set<String> setWithBrackets) {

        String result = String.valueOf(setWithBrackets);
        result = result.replaceAll("[\\[\\]]","");

        return result;
    }

}
