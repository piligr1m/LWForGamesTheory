package Algs;

import model.Data;

import java.util.*;

public class BR_alg {
    private final List<Data> myData = new ArrayList<>();
    private final int[][] mat = new int[3][3];
    private final double limit;
    private final double limit2;
    public BR_alg(int[][] matrix, double limit)
    {
        for (int i = 0; i < 3; i++)
        {
            System.arraycopy(matrix[i], 0, mat[i], 0, 3);
        }
        this.limit = limit;
        this.limit2 = -limit;
    }
    public boolean setInit()
    {
        int[] winA = new int[mat.length];
        int[] loseB = new int[mat.length];
        for (int i = 0; i < mat[0].length; i++)
        {
            winA[i] = mat[i][0];
            loseB[i] = mat[0][i];
        }
        myData.add(new Data(1, countHighPrice(winA), countLowPrice(loseB), 0, 0, winA, loseB,
                (countHighPrice(winA) - countLowPrice(loseB))));
        System.out.println("Brown-Robinson algorithm has started");
        System.out.println("____________________________________");
        System.out.println(myData);
        System.out.println("____________________________________");
        return countHighPrice(winA) - countLowPrice(loseB) > limit;
    }
    public boolean setInitV2()
    {
        int[] winA = new int[mat.length];
        int[] loseB = new int[mat.length];
        for (int i = 0; i < mat[0].length; i++)
        {
            winA[i] = mat[i][0];
            loseB[i] = mat[0][i];
        }
        myData.add(new Data(1, countHighPriceV2(winA), countLowPriceV2(loseB), 0, 0, winA, loseB,
                (countHighPriceV2(winA) - countLowPriceV2(loseB))));
        System.out.println("Brown-Robinson algorithm has started");
        System.out.println("____________________________________");
        System.out.println(myData);
        System.out.println("____________________________________");
        return countHighPriceV2(winA) - countLowPriceV2(loseB) < limit2;
    }
    public void iteration() {
        while (myData.get(myData.size()-1).getEpsilon() >= limit) {
            int step = myData.size() + 1;
            int strA = chooseStrategyA();
            int strB = chooseStrategyB();
            int[] winA = winA(strB);
            int[] loseB = loseB(strA);
            float highPrice = countHighPrice(winA);
            float lowPrice = countLowPrice(loseB);
            float epsilon = countEpsilon(myData, highPrice, lowPrice);
            myData.add(new Data(step, highPrice, lowPrice, strA, strB, winA, loseB, epsilon));
            System.out.println(myData.get(myData.size()-1).toString());
            System.out.println("____________________________________");
        }
        Map<Integer, Integer> mapA = new HashMap<>();
        mapA.put(1, 0);
        mapA.put(2, 0);
        mapA.put(3, 0);
        Map<Integer, Integer> mapB = new HashMap<>();
        mapB.put(1, 0);
        mapB.put(2, 0);
        mapB.put(3, 0);
        for (Data temp : myData)
        {
                mapA.put(temp.getChoiceA()+1, (mapA.get(temp.getChoiceA()+1)+1));
                mapB.put(temp.getChoiceB()+1, (mapB.get(temp.getChoiceB()+1)+1));
        }
        float[] approxStrA = {(float)mapA.get(1)/myData.get(myData.size()-1).getStep(),
                (float)mapA.get(2)/myData.get(myData.size()-1).getStep(),
                (float)mapA.get(3)/myData.get(myData.size()-1).getStep()};
        float[] approxStrB = {(float)mapB.get(1)/myData.get(myData.size()-1).getStep(),
                (float)mapB.get(2)/myData.get(myData.size()-1).getStep(),
                (float)mapB.get(3)/myData.get(myData.size()-1).getStep()};
        float error = myData.get(myData.size()-1).getEpsilon();
        System.out.println("Приближенные смешанные стратегии игрока А: " + Arrays.toString(approxStrA) + " " +
                "Приближенные смешанные стратегии игрока B: " + Arrays.toString(approxStrB) + " " +
                "Погрешнсоть: " + error);
    }
    public void iterationV2()
    {
        while (myData.get(myData.size()-1).getEpsilon() <= limit2) {
            int step = myData.size() + 1;
            int strA = chooseStrategyAV2();
            int strB = chooseStrategyBV2();
            int[] winA = winA(strB);
            int[] loseB = loseB(strA);
            float highPrice = countHighPriceV2(winA);
            float lowPrice = countLowPriceV2(loseB);
            float epsilon = countEpsilonV2(myData, highPrice, lowPrice);
            myData.add(new Data(step, highPrice, lowPrice, strA, strB, winA, loseB, epsilon));
            System.out.println(myData.get(myData.size()-1).toString());
            System.out.println("____________________________________");
        }
        Map<Integer, Integer> mapA = new HashMap<>();
        mapA.put(1, 0);
        mapA.put(2, 0);
        mapA.put(3, 0);
        Map<Integer, Integer> mapB = new HashMap<>();
        mapB.put(1, 0);
        mapB.put(2, 0);
        mapB.put(3, 0);
        for (Data temp : myData)
        {
            mapA.put(temp.getChoiceA()+1, (mapA.get(temp.getChoiceA()+1)+1));
            mapB.put(temp.getChoiceB()+1, (mapB.get(temp.getChoiceB()+1)+1));
        }
        float[] approxStrA = {(float)mapA.get(1)/myData.get(myData.size()-1).getStep(),
                (float)mapA.get(2)/myData.get(myData.size()-1).getStep(),
                (float)mapA.get(3)/myData.get(myData.size()-1).getStep()};
        float[] approxStrB = {(float)mapB.get(1)/myData.get(myData.size()-1).getStep(),
                (float)mapB.get(2)/myData.get(myData.size()-1).getStep(),
                (float)mapB.get(3)/myData.get(myData.size()-1).getStep()};
        float error = myData.get(myData.size()-1).getEpsilon();
        System.out.println("Приближенные смешанные стратегии игрока А: " + Arrays.toString(approxStrA) + " " +
                "Приближенные смешанные стратегии игрока B: " + Arrays.toString(approxStrB) + " " +
                "Погрешнсоть: " + error);
    }
    public int chooseStrategyA()
    {
        Data data = myData.get(myData.size()-1);
        int[] winA = data.getWinA();
        int max = -1;
        int index = -1;
        for (int i = 0; i < winA.length; i++)
        {
            if (max < winA[i])
            {
                max = winA[i];
                index = i;
            }
        }
        return index;
    }
    public int chooseStrategyAV2()
    {
        Data data = myData.get(myData.size()-1);
        int[] winA = data.getWinA();
        int max = -1;
        int index = -1;
        for (int i = 0; i < winA.length; i++)
        {
            if (Math.abs(max) < Math.abs(winA[i]))
            {
                max = winA[i];
                index = i;
            }
        }
        return index;
    }
    public int chooseStrategyB()
    {
        Data data = myData.get(myData.size()-1);
        int[] loseB = data.getLoseB();
        int min = 1000000;
        int index = -1;
        for (int i = 0; i < loseB.length; i++)
        {
            if (min > loseB[i])
            {
                min = loseB[i];
                index = i;
            }
        }
        return index;
    }
    public int chooseStrategyBV2()
    {
        Data data = myData.get(myData.size()-1);
        int[] loseB = data.getLoseB();
        int min = 1000000;
        int index = -1;
        for (int i = 0; i < loseB.length; i++)
        {
            if (Math.abs(min) > Math.abs(loseB[i]))
            {
                min = loseB[i];
                index = i;
            }
        }
        return index;
    }
    public int[] winA(int strB)
    {
        Data data = myData.get(myData.size()-1);
        int[] winA = data.getWinA();
        for (int i = 0; i < mat.length; i++)
        {
            winA[i] = winA[i] + mat[i][strB];
        }
        return winA;
    }
    public int[] loseB(int strA)
    {
        Data data = myData.get(myData.size()-1);
        int[] loseB = data.getLoseB();
        for (int i = 0; i < mat.length; i++)
        {
            loseB[i] = loseB[i] + mat[strA][i];
        }
        return loseB;
    }
    public float countHighPrice(int[] arr)
    {
        if ((myData.size()-1) >= 0)
        {
        Data data = myData.get(myData.size()-1);
        return (float) (Arrays.stream(arr).max().getAsInt())/(data.getStep() + 1);
        }
        else
        {
            return Arrays.stream(arr).max().getAsInt();
        }
    }
    public float countHighPriceV2(int[] arr)
    {
        if (myData.size()-1 >= 0)
        {
            Data data = myData.get(myData.size()-1);
            return (float) (Arrays.stream(arr).min().getAsInt())/(data.getStep() + 1);
        }
        else
        {
            return Arrays.stream(arr).min().getAsInt();
        }
    }

    public float countLowPrice(int[] arr)
    {
        if (myData.size()-1 >= 0)
        {
        Data data = myData.get(myData.size()-1);
        return (float) (Arrays.stream(arr).min().getAsInt())/(data.getStep() + 1);
        }
        else
        {
            return Arrays.stream(arr).min().getAsInt();
        }
    }
    public float countLowPriceV2(int[] arr)
    {
        if ((myData.size()-1) >= 0)
        {
            Data data = myData.get(myData.size()-1);
            return (float) (Arrays.stream(arr).max().getAsInt())/(data.getStep() + 1);
        }
        else
        {
            return Arrays.stream(arr).max().getAsInt();
        }
    }
    public float countEpsilon(List<Data> data, float highPrice, float lowPrice)
    {
        float minHighPrice = 100000;
        float maxLowPrice = -1;
        for (Data temp : data)
        {
            if (temp.getHighPrice() < minHighPrice)
            {
                minHighPrice = temp.getHighPrice();
            }
            if (maxLowPrice < temp.getLowPrice())
            {
                maxLowPrice = temp.getLowPrice();
            }
        }
        if (minHighPrice > highPrice)
        {
            minHighPrice = highPrice;
        }
        if (maxLowPrice < lowPrice)
        {
            maxLowPrice = lowPrice;
        }
       return minHighPrice-maxLowPrice;
    }
    public float countEpsilonV2(List<Data> data, float highPrice, float lowPrice)
    {
        float minHighPrice = 100000;
        float maxLowPrice = -1;
        for (Data temp : data)
        {
            if (Math.abs(temp.getHighPrice()) < Math.abs(minHighPrice))
            {
                minHighPrice = temp.getHighPrice();
            }
            if (Math.abs(maxLowPrice) < Math.abs(temp.getLowPrice()))
            {
                maxLowPrice = temp.getLowPrice();
            }
        }
        if (Math.abs(minHighPrice) > Math.abs(highPrice))
        {
            minHighPrice = highPrice;
        }
        if (Math.abs(maxLowPrice) < Math.abs(lowPrice))
        {
            maxLowPrice = lowPrice;
        }
        return minHighPrice-maxLowPrice;
    }
}
