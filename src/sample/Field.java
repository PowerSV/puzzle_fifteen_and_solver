package sample;

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
        field = deepCopy(controlArray);
        zeroX = 3;
        zeroY = 3;
        score = 0;
    }

    public byte[][] getField() {
        return field;
    }

    public void setField(byte[][] field) {
        this.field = new byte[field.length][];

        for (byte i = 0; i < SIZE; i++) {
            this.field[i] = new byte[field[i].length];
            for (byte j = 0; j < SIZE; j++) {
                this.field[i][j] = field[i][j];

                if (field[i][j] == 0) {
                    zeroX = i;
                    zeroY = j;
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
            resultArray[i] = srcArray[i].clone();
        }
        return resultArray;
    }
}
