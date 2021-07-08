package main;

import javax.swing.*;
import java.awt.*;

public class SierpinskiTriangle {
    static JFrame frame;

    public static void main(String[] args){
        frameSetup();
        drawTriangles();
    }

    /**
     *The method to setup the frame.
     */
    private static void frameSetup(){
        JFrame.setDefaultLookAndFeelDecorated(false);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        frame = new JFrame("Sierpinski Triangle");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(Color.black);
        frame.setSize(screenSize.width, screenSize.height);
    }

    /**
     *The method to draw the triangles with the SierpinskiPanel.
     */
    private static void drawTriangles(){
        frame.add(new SierpinskiPanel());
        frame.setVisible(true);
    }
}