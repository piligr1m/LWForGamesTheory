package Algs;

import java.util.Arrays;

public class AnalyticAlg {
    public float[][] inversion(float[][] A, int N)
    {
        double temp;
        float [][] E = new float [N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
            {
                E[i][j] = 0f;

                if (i == j)
                    E[i][j] = 1f;
            }
        for (int k = 0; k < N; k++)
        {
            temp = A[k][k];

            for (int j = 0; j < N; j++)
            {
                A[k][j] /= temp;
                E[k][j] /= temp;
            }

            for (int i = k + 1; i < N; i++)
            {
                temp = A[i][k];

                for (int j = 0; j < N; j++)
                {
                    A[i][j] -= A[k][j] * temp;
                    E[i][j] -= E[k][j] * temp;
                }
            }
        }

        for (int k = N - 1; k > 0; k--)
        {
            for (int i = k - 1; i >= 0; i--)
            {
                temp = A[i][k];

                for (int j = 0; j < N; j++)
                {
                    A[i][j] -= A[k][j] * temp;
                    E[i][j] -= E[k][j] * temp;
                }
            }
        }
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                A[i][j] = E[i][j];
        return A;
    }
    public float[] multiplicationVectorOnMatrix(float[] u, float[][] invC)
    {
        float[] res = new float[u.length];
        for (int i = 0; i < u.length; i++)
        {
            for (int j = 0; j < u.length; j++)
            {
                res[i] += u[j]*invC[j][i];
            }
        }
        return res;
    }
    public float multiplicationVectorOnVector(float[] vec1, float[] vec2)
    {
        float res = 0;
        for (int i = 0; i < vec1.length; i++)
        {
            res += vec1[i]*vec2[i];
        }
        return res;
    }
    public float[] multiplicationMatrixOnVector(float[] u, float[][] invC)
    {
        float[] res = new float[u.length];
        for (int i = 0; i < u.length; i++)
        {
            for (int j = 0; j < u.length; j++)
            {
                res[i] += invC[i][j]*u[j];
            }
        }
        return res;
    }
    public  void countXYV(float[][] matrix)
    {
        float[] u = {1.0F, 1.0F, 1.0F};
        float[][] invC = inversion(matrix, 3);
        float[] res1 = multiplicationVectorOnMatrix(u, invC);
        float res2 = multiplicationVectorOnVector(res1, u);
        float[] res3 = multiplicationMatrixOnVector(u, invC);
        float[] x = new float[3];
        float[] y = new float[3];
        float v = 1/res2;
        for (int i = 0; i < 3; i++)
        {
            x[i] = res1[i]/res2;
            y[i] = res3[i]/res2;
        }
        System.out.println("Аналитическое решение: " + "x*= " + Arrays.toString(x) + " " +
                "y*= " + Arrays.toString(y) + " " + "v= " + v);
    }
}
