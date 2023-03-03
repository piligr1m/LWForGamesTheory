import java.util.Arrays;
import java.util.Scanner;
import Algs.*;

public class Main {
    public static void main(String[] args) {
        int[][] matrix = new int[3][3];
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter limit");
        double limit = scanner.nextDouble();
        System.out.println("Enter matrix");
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3;j++)
            {
                matrix[i][j] = scanner.nextInt();
            }
        }
        System.out.println("Игрок 1");
        BR_alg brAlg = new BR_alg(matrix, limit);
         if(brAlg.setInit())
         {
             brAlg.iteration();
         }
         float[][] matrix2 = new float[3][3];
         for (int i = 0; i < 3; i++)
         {
             for (int j = 0; j < 3; j++)
             {
                 matrix2[i][j] = (float)matrix[i][j];
             }
         }
         AnalyticAlg analyticAlg = new AnalyticAlg();
         analyticAlg.countXYV(matrix2);
        int[][] matrix3 = new int[3][3];
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                matrix3[i][j] = -matrix[i][j];
            }
        }
        matrix3 = transpose(matrix3);
        System.out.println("Игрок 2");
        BR_alg brAlg1 = new BR_alg(matrix3, limit);
        if(brAlg1.setInitV2())
        {
            brAlg1.iterationV2();
        }
        float[][] matrix4 = new float[3][3];
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                matrix4[i][j] = (float)matrix3[i][j];
            }
        }
        analyticAlg.countXYV(matrix4);
    }
    public static int[][] transpose(int[][] matrix)
    {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i+1; j < matrix.length; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        return matrix;
    }
}