package sample.solver;

import java.util.Arrays;
import java.util.HashSet;
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
        this.field = field;

        heuristic = 0;
        for (byte i = 0; i < field.length; i++) {
            for (byte j = 0; j < field.length; j++) {

                if (field[i][j] == 0) {
                    zeroX = i;
                    zeroY = j;
                    continue;
                }

                heuristic += calculateHeuristic(field[i][j], i, j);
            }
        }

    }

    private int calculateHeuristic(byte value, byte x, byte y) {
        if (Field.controlArray[x][y] == value) {
            return 0;
        }
        int trueX = (value - 1) % 4;
        int trueY = (value - 1) / 4;
        int result = Math.abs(x - trueX) + Math.abs(y - trueY);

        if (value == 15 && y != 3 || value == 12 && x != 3) {
            result += 2;
        }
        return result;
    }

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
                Arrays.deepEquals(field, that.field);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(zeroX, zeroY, heuristic);
        result = 31 * result + Arrays.deepHashCode(field);
        return result;
    }
}
