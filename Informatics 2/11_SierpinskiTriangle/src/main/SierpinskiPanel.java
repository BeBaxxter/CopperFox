package main;

import java.awt.*;

public class SierpinskiPanel extends Panel {

    private static int depthMax = 9;
    private static Color[] colors = {new Color(0xf8ffca),new Color(0xb6d084), new Color(0xffcb32), new Color(0x2f7604), new Color(0x9dcc5f), new Color(0x0d5f8a)};

    /**
     * The method to recursively draw the triangles.
     * @param g
     * @param x The x-coordinates
     * @param y The y-coordinates
     * @param depth The current depth-level
     */
    private void triangleRecursive(Graphics g, int[] x, int[] y, int depth){
        drawTriangle(g, x, y, depth);
        depth = depth + 1;
        if(depth < depthMax) {
            int xM1 = (x[0] + x[1]) / 2;
            int yM1 = (y[0] + y[1]) / 2;
            int xM2 = (x[1] + x[2]) / 2;
            int yM2 = (y[1] + y[2]) / 2;
            int xM3 = (x[0] + x[2]) / 2;
            int yM3 = (y[0] + y[2]) / 2;
            triangleRecursive(g, new int[]{xM1, x[1], xM2}, new int[]{yM1, y[1], yM2}, depth);
            triangleRecursive(g, new int[]{x[0], xM1, xM3}, new int[]{y[0], yM1, yM3}, depth);
            triangleRecursive(g, new int[]{xM3, xM2, x[2]}, new int[]{yM3, yM2, y[2]}, depth);
        }
    }

    /**
     * The method to draw a triangle.
     * @param g
     * @param x The x-coordinates
     * @param y The y-coordinates
     * @param depth The current depth-level
     */
    private void drawTriangle(Graphics g, int[] x, int[] y, int depth){
        Polygon p = new Polygon(x, y, 3);
        g.setColor(Color.BLACK);
        g.drawPolygon(p);
        g.setColor(colors[depth%colors.length]);
        g.fillPolygon(p);
    }

    /**
     *The paint method.
     * @param g
     */
    public void paint(Graphics g){
        int screenX = SierpinskiTriangle.frame.getWidth();
        int screenY = SierpinskiTriangle.frame.getHeight();
        screenY = screenY - screenY/50;

        int s = (int) (screenY/Math.sin(Math.toRadians(60.0)));

        triangleRecursive(g, new int[] {(screenX/2 - s/2), (screenX/2), (screenX/2 + s/2)}, new int[] {screenY, 0, screenY}, 1);
    }
}