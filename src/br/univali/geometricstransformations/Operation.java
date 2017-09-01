package br.univali.geometricstransformations;

import java.awt.Point;

/**
 *
 * @author biankatpas
 */
public class Operation
{

    public Point sum(Point p1, Point p2)
    {
        return new Point(p1.x + p2.x, p1.y + p2.y);
    }

    public float[][] sub(int row, int col, float matrix1[][], float matrix2[][])
    {
        float matrixRes[][] = new float[row][col];

        for (int i = 0; i < row; i++)
        {
            for (int j = 0; j < col; j++)
            {
                matrixRes[i][j] = matrix1[i][j] - matrix2[i][j];
            }
        }

        return matrixRes;
    }

    public float scale(Point p1, Point p2)
    {
        return (p1.x * p2.x) + (p1.y * p2.y);
    }

    public float[][] mult(float matrix1[][], float matrix2[][])
    {
        float matrixRes[][];

        if (matrix1[0].length != matrix2.length)
        {
            System.err.println("mult sz error");
        } 
        else
        {
            matrixRes = new float[matrix1.length][matrix2[0].length];
            for (int i = 0; i < matrix1.length; i++)
            {
                for (int j = 0; j < matrix2[0].length; j++)
                {
                    for (int k = 0; k < matrix1[0].length; k++)
                    {
                        matrixRes[i][j] += matrix1[i][k] * matrix2[k][j];
                    }
                }
            }
            return matrixRes;
        }
        return null;
    }
}
