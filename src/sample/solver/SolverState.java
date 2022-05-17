package sample.solver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import sample.Field;

import static sample.Field.deepCopy;

public class SolverState {

    private final byte[][] field;
    private byte zeroX;
    private byte zeroY;
    private int heuristic;

    public SolverState(byte[][] field) {
        this.field = deepCopy(field);

        heuristic = 0;
        for (byte i = 0; i < field.length; i++) {
            for (byte j = 0; j < field.length; j++) {

                if (field[i][j] == 0) {
                    zeroX = i;
                    zeroY = j;
                    continue;
                }

                heuristic += calculateManhattanPath(field[i][j], i, j);
            }
        }
        //        heuristic += linearConflicts(field);
    }

    private int calculateManhattanPath(byte value, byte x, byte y) {
        if (Field.controlArray[x][y] == value) {
            return 0;
        }

        int trueX = (value - 1) % 4;
        int trueY = (value - 1) / 4;

        return Math.abs(x - trueX) + Math.abs(y - trueY);
    }

//    private int linearConflicts(byte[][] field) {
//        int result = 0;
//
//        List<Byte> inOwnColumn = new ArrayList<>();
//        for (int i = 0; i < Field.SIZE; i++) {
//            // находим конфликты в столбцах
//            for (byte value : field[i]) {
//                int trueX = (value - 1) % 4;
//                if (trueX == i) {
//                    inOwnColumn.add(value);
//                }
//            }
//            result += findConflicts(inOwnColumn);
//
//            // находим конфликты в строках
//            byte[] temp = new byte[Field.SIZE];
//            for (int j = 0; j < Field.SIZE; j++) {
//                temp[j] = field[j][i];
//            }
//
//            for (byte value : temp) {
//                int trueY = (value - 1) / 4;
//                if (trueY == i) {
//                    inOwnColumn.add(value);
//                }
//            }
//            result += findConflicts(inOwnColumn);
//        }
//
//        return result;
//    }

//    private int findConflicts(List<Byte> inOwnColumnOrRow) {
//        int counter = 0;
//        if (inOwnColumnOrRow.size() > 1) {
//            for (int j = 1; j < inOwnColumnOrRow.size(); j++) {
//                if (inOwnColumnOrRow.get(j) < inOwnColumnOrRow.get(j - 1)) {
//                    counter++;
//                }
//            }
//        }
//        inOwnColumnOrRow.clear();
//        return counter * 2;
//    }

    public byte[][] getField() {
        return field;
    }

    public int getHeuristic() {
        return heuristic;
    }

    public Iterable<SolverState> getNeighbors() {
        Set<SolverState> result = new HashSet<>();
        if (zeroY < Field.SIZE - 1) {
            result.add(neighborFields(deepCopy(field), zeroX, zeroY, zeroX, (byte) (zeroY + 1)));
        }
        if (zeroY > 0) {
            result.add(neighborFields(deepCopy(field), zeroX, zeroY, zeroX, (byte) (zeroY - 1)));
        }
        if (zeroX < Field.SIZE - 1) {
            result.add(neighborFields(deepCopy(field), zeroX, zeroY, (byte) (zeroX + 1), zeroY));
        }
        if (zeroX > 0) {
            result.add(neighborFields(deepCopy(field), zeroX, zeroY, (byte) (zeroX - 1), zeroY));
        }
        return result;
    }

    private SolverState neighborFields(byte[][] field, byte x1, byte y1, byte x2, byte y2) {
        byte temp = field[x2][y2];
        field[x2][y2] = field[x1][y1];
        field[x1][y1] = temp;
        return new SolverState(field);
    }

    public boolean isFinalState() {
        return heuristic == 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SolverState that = (SolverState) o;
        return zeroX == that.zeroX &&
                zeroY == that.zeroY &&
                heuristic == that.heuristic &&
                Arrays.equals(field, that.field);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(zeroX, zeroY, heuristic);
        result = 31 * result + Arrays.hashCode(field);
        return result;
    }
}
