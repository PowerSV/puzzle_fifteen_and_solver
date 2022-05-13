package sample;

import java.util.Arrays;
import java.util.Random;

public class Field {

    public static final int SIZE = 4;

    private int[][] field;
    private int zeroX;
    private int zeroY;
    private int score;

    public Field() {
        field = new int[][]{
                {1, 5, 9, 13},
                {2, 6, 10, 14},
                {3, 7, 11, 15},
                {4, 8, 12, 0}};
        zeroX = 3;
        zeroY = 3;
        score = 0;
    }

    public int[][] getField() {
        return field;
    }

    public void setField(int[][] field) {
        this.field = field;
    }

    public int getScore() {
        return score;
    }

    public int getValue(int x, int y) {
        return field[x][y];
    }

    public boolean moveUp(){
        if (zeroY < SIZE - 1) {
            field[zeroX][zeroY] = field[zeroX][zeroY + 1];
            field[zeroX][zeroY + 1] = 0;
            zeroY++;
            score++;
            return true;
        }
        return false;
    }

    public boolean moveDown(){
        if (zeroY > 0) {
            field[zeroX][zeroY] = field[zeroX][zeroY - 1];
            field[zeroX][zeroY - 1] = 0;
            zeroY--;
            score++;
            return true;
        }
        return false;
    }

    public boolean moveLeft(){
        if (zeroX < SIZE - 1) {
            field[zeroX][zeroY] = field[zeroX + 1][zeroY];
            field[zeroX + 1][zeroY] = 0;
            zeroX++;
            score++;
            return true;
        }
        return false;
    }

    public boolean moveRight(){
        if (zeroX > 0) {
            field[zeroX][zeroY] = field[zeroX - 1][zeroY];
            field[zeroX - 1][zeroY] = 0;
            zeroX--;
            score++;
            return true;
        }
        return false;
    }

    public void shuffle(){
        Random rand = new Random();
        int temp;
        for (int i = 0; i < 1000; i++) {
            temp = rand.nextInt(4);
            switch (temp) {
                case 0:
                    moveDown();
                    break;
                case 1:
                    moveLeft();
                    break;
                case 2:
                    moveRight();
                    break;
                case 3:
                    moveUp();
                    break;
            }
        }
        score = 0;
    }

//    public int[][] arrayCopy(int[][] srcArray) {
//        int[][] resultArray = new int[srcArray.length][];
//        for (int i = 0; i < srcArray.length; i++) {
//            resultArray[i] = Arrays.copyOf(srcArray[i], srcArray[i].length);
//        }
//        return resultArray;
//    }
}
