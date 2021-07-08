package main.prelab;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;


public class AreaOfEquilateralTriangle extends Frame {

    /*
        Source: https://www.daniweb.com/programming/software-development/threads/411585/draw-an-isoceles-or-equilateral-triangle
     */

    public static void main(String args[]) {
        Frame frame = new AreaOfEquilateralTriangle();
        frame.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent we)
            {
                System.exit(0);
            }
        });
        frame.setSize(1000, 1000);
        frame.setVisible(true);
    }

    Line2D line1 = new Line2D.Double(50,150,150,150);
    Line2D line2 = new Line2D.Double(50,50,150,150);
    Line2D line3 = new Line2D.Double(50,50,50,150);


    public void paint(Graphics g) {
        Graphics2D graph = (Graphics2D)g;
        graph.draw(line1);
        graph.draw(line2);
        graph.draw(line3);


    }


}