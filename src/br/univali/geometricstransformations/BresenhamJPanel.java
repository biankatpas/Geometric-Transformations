package br.univali.geometricstransformations;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author biankatpas
 */
class Bresenham extends JFrame
{

    private JFrame f;
    private BresenhamPanel panel;

    public Bresenham()
    {
        f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(false);
        panel = new BresenhamPanel();
        f.add(panel, BorderLayout.CENTER);
        f.setTitle("Bresenham");
        f.setResizable(false);
        f.pack();
    }

    public void add(ArrayList<Point> newPoints)
    {
        if (panel != null)
        {
            f.remove(panel);
        }
        panel = new BresenhamPanel(newPoints);
        f.add(panel, BorderLayout.CENTER);
        f.setVisible(true);

    }

}

class BresenhamPanel extends JPanel
{

    int centerX, centerY;
    int MouseX, MouseY;
    ArrayList<Point> newPoints;

    public BresenhamPanel()
    {
        newPoints = new ArrayList<>();

        int w = 600;
        int h = 500;

        centerX = w / 2;
        centerY = h / 2;

        setPreferredSize(new Dimension(w, h));
        setBackground(Color.white);
    }

    public BresenhamPanel(ArrayList<Point> newPoints)
    {
        this.newPoints = newPoints;
        
        int w = 600;
        int h = 500;

        centerX = w / 2;
        centerY = h / 2;

        setPreferredSize(new Dimension(w, h));
        setBackground(Color.white);
        
        printPoints();
    }

    private void printPoints()
    {
        System.out.println("pts");
        for (Point newPoint : newPoints)
        {
            System.out.println("["+newPoint.x+","+newPoint.y+"]");
        }
    }

    @Override
    public void paintComponent(Graphics g
    )
    {
        super.paintComponent(g);

        if (!newPoints.isEmpty())
        {
            int size = newPoints.size();
            {
                for (int i = 0; i < size - 1; i++)
                {
                    drawLine(g, newPoints.get(i).x, newPoints.get(i).y, newPoints.get(i + 1).x, newPoints.get(i + 1).y);
                }
                drawLine(g, newPoints.get(size - 1).x, newPoints.get(size - 1).y, newPoints.get(0).x, newPoints.get(0).y);
            }
        }
    }

    private void plot(Graphics g, int x, int y)
    {
        g.setColor(Color.BLACK);
        g.fillOval(centerX + (x * 1), centerY + (-y * 1), 2, 2);
    }

    public void drawLine(Graphics g, int x1, int y1, int x2, int y2)
    {
        // delta of exact value and rounded value of the dependant variable
        int d = 0;

        int dy = Math.abs(y2 - y1);
        int dx = Math.abs(x2 - x1);

        int dy2 = (dy << 1); // slope scaling factors to avoid floating
        int dx2 = (dx << 1); // point

        int ix = x1 < x2 ? 1 : -1; // increment direction
        int iy = y1 < y2 ? 1 : -1;

        if (dy <= dx)
        {
            for (;;)
            {
                plot(g, x1, y1);
                if (x1 == x2)
                {
                    break;
                }
                x1 += ix;
                d += dy2;
                if (d > dx)
                {
                    y1 += iy;
                    d -= dx2;
                }
            }
        } else
        {
            for (;;)
            {
                plot(g, x1, y1);
                if (y1 == y2)
                {
                    break;
                }
                y1 += iy;
                d += dx2;
                if (d > dy)
                {
                    x1 += ix;
                    d -= dy2;
                }
            }
        }
    }
}
