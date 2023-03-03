package model;

import java.util.Arrays;
import java.util.List;

public class Data {
    private int step;
    private int choiceA;
    private int choiceB;
    private int[] winA;
    private int[] loseB;
    private float highPrice;
    private float lowPrice;
    private float epsilon;

    public Data(int step, float highPrice, float lowPrice, int choiceA, int choiceB, int[] winA, int[] loseB,
                float epsilon) {
        this.step = step;
        this.choiceA = choiceA;
        this.choiceB = choiceB;
        this.winA = winA;
        this.loseB = loseB;
        this.highPrice = highPrice;
        this.lowPrice = lowPrice;
        this.epsilon = epsilon;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public int getChoiceA() {
        return choiceA;
    }

    public void setChoiceA(int choiceA) {
        this.choiceA = choiceA;
    }

    public int getChoiceB() {
        return choiceB;
    }

    public void setChoiceB(int choiceB) {
        this.choiceB = choiceB;
    }

    public int[] getWinA() {
        return winA;
    }

    public void setWinA(int[] winA) {
        this.winA = winA;
    }

    public int[] getLoseB() {
        return loseB;
    }

    public void setLoseB(int[] loseB) {
        this.loseB = loseB;
    }

    public float getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(int highPrice) {
        this.highPrice = highPrice;
    }

    public float getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(int lowPrice) {
        this.lowPrice = lowPrice;
    }

    public float getEpsilon() {
        return epsilon;
    }
    public void setEpsilon(int epsilon) {
        this.epsilon = epsilon;
    }
    @Override
    public String toString()
    {
        return "Step " + getStep() + " | " + "StrA " + (getChoiceA()+1) + " | " + "StrB " + (getChoiceB()+1) + " | " +
                "WinA " + Arrays.toString(getWinA()) + " | " + "LoseB " + Arrays.toString(getLoseB()) + " | " +
                "HighPrice " + getHighPrice() + " | " + "LowPrice " + getLowPrice() + " | " + "Epsilon " +
                getEpsilon() + " | ";

    }
}
