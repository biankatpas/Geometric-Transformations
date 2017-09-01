package br.univali.geometricstransformations;

import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author biankatpas
 */
public class Transformation
{

    public ArrayList<Point> rotation(float ang, ArrayList<Point> pontos)
    {
        double rad = Math.toRadians(ang);
        double cos = Math.cos(rad);
        double sin = Math.sin(rad);
        Point centroid = getCentroid(pontos);
        ArrayList<Point> aux = new ArrayList<>();

        for (Point ponto : pontos)
        {
            double dx = ponto.x - centroid.x;
            double dy = ponto.y - centroid.y;

            aux.add(new Point(
                    (int) (cos * dx - sin * dy + centroid.x),
                    (int) (sin * dx + cos * dy + centroid.y)));
        }
        return aux;
    }

    public ArrayList<Point> translation(Point newPoint, ArrayList<Point> points)
    {
        ArrayList<Point> newPoints = new ArrayList<>();

        for (int i = 0; i < points.size(); i++)
        {
            newPoints.add(new Operation().sum(points.get(i), newPoint));
        }

        return newPoints;
    }

    public ArrayList<Point> scale(Point newPoint, ArrayList<Point> points)
    {
        ArrayList<Point> newPoints = new ArrayList<>();
        ArrayList<Point> finalPoints = new ArrayList<>();
        ArrayList<Point> auxPoints = new ArrayList<>();
        Point centerPoint;

        centerPoint = getCentroid(points);
        auxPoints = coordMenosPontoCentral(points, centerPoint);

        for (int i = 0; i < auxPoints.size(); i++)
        {
            newPoints.add(new Point(auxPoints.get(i).x * newPoint.x, auxPoints.get(i).y * newPoint.y));
        }

        finalPoints = coordMaisPontoCentral(newPoints, centerPoint);

        return finalPoints;
    }

    private ArrayList<Point> coordMenosPontoCentral(ArrayList<Point> points, Point centroid)
    {
        ArrayList<Point> newPoints = new ArrayList<>();
        for (int i = 0; i < points.size(); i++)
        {
            newPoints.add(new Point((points.get(i).x - centroid.x), (points.get(i).y - centroid.y)));
        }
        return newPoints;
    }

    private ArrayList<Point> coordMaisPontoCentral(ArrayList<Point> points, Point centroid)
    {
        ArrayList<Point> newPoints = new ArrayList<>();
        for (int i = 0; i < points.size(); i++)
        {
            newPoints.add(new Point((points.get(i).x + centroid.x), (points.get(i).y + centroid.y)));
        }
        return newPoints;
    }

    private Point getCentroid(ArrayList<Point> points)
    {
        Point centerPoint;
        centerPoint = new Point((((maxx(points) - minx(points)) / 2) + minx(points)),
                (((maxy(points) - miny(points)) / 2) + miny(points)));

        return centerPoint;
    }

    private int maxx(ArrayList<Point> points)
    {
        int x = points.get(0).x;

        for (int i = 1; i < points.size(); i++)
        {
            if (points.get(i).x > x)
            {
                x = points.get(i).x;
            }
        }

        return x;
    }

    private int maxy(ArrayList<Point> points)
    {
        int y = points.get(0).y;
        for (int i = 1; i < points.size(); i++)
        {
            if (points.get(i).y > y)
            {
                y = points.get(i).y;
            }
        }
        return y;
    }

    private int minx(ArrayList<Point> points)
    {
        int x = points.get(0).x;
        for (int i = 1; i < points.size(); i++)
        {
            if (points.get(i).x < x)
            {
                x = points.get(i).x;
            }
        }
        return x;
    }

    private int miny(ArrayList<Point> points)
    {
        int y = points.get(0).y;
        for (int i = 1; i < points.size(); i++)
        {
            if (points.get(i).y < y)
            {
                y = points.get(i).y;
            }
        }
        return y;
    }

}
