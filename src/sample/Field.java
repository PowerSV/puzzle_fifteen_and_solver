package sample;

import java.util.Arrays;
import java.util.Random;

public class Field {

    public static final byte SIZE = 4;
    public static final byte[][] controlArray = new byte[][]{
            {1, 5, 9, 13},
            {2, 6, 10, 14},
            {3, 7, 11, 15},
            {4, 8, 12, 0}
    };

    private byte[][] field;
    private byte zeroX;
    private byte zeroY;
    private int score;

    public Field() {
        initializeField();
        zeroX = 3;
        zeroY = 3;
        score = 0;
    }

//    public Field() {
//        field = new byte[][]{
//                {1, 7, 13, 14},
//                {2, 3, 6, 9},
//                {11, 5, 12, 15},
//                {4, 0, 10, 8}
//        };
//        zeroX = 3;
//        zeroY = 1;
//        score = 0;
//    }

    private void initializeField() {
        field = new byte[SIZE][SIZE];
        byte temp = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                field[j][i] = ++temp;
            }
        }
        field[SIZE - 1][SIZE - 1] = 0;
    }

    public byte[][] getField() {
        return field;
    }

    public void setField(byte[][] field) {
        this.field = deepCopy(field);

        for (byte i = 0; i < SIZE; i++) {
            for (byte j = 0; j < SIZE; j++) {
                if (field[i][j] == 0) {
                    zeroX = i;
                    zeroY = j;
                    break;
                }
            }
        }

    }

    public int getScore() {
        return score;
    }

    public byte getValue(int x, int y) {
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
        for (int i = 0; i < 100; i++) {
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

    public static byte[][] deepCopy(byte[][] srcArray) {
        if (srcArray == null) {
            return null;
        }
        byte[][] resultArray = new byte[srcArray.length][];
        for (int i = 0; i < srcArray.length; i++) {
            resultArray[i] = Arrays.copyOf(srcArray[i], srcArray[i].length);
        }
        return resultArray;
    }
}
