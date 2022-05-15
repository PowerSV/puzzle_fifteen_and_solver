package sample.solver;

import java.util.HashSet;
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
    }

    private int calculateManhattanPath(byte value, byte x, byte y) {
        if (Field.controlArray[x][y] == value) {
            return 0;
        }

        int trueX = (value - 1) % 4;
        int trueY = (value - 1) / 4;

        return Math.abs(x - trueX) + Math.abs(y - trueY);
    }

    public Iterable<SolverState> getNeighbors() {
        Set<SolverState> result = new HashSet<>();
        result.add(neighborFields(deepCopy(field), (byte) (zeroX + 1), zeroY));
        result.add(neighborFields(deepCopy(field), (byte) (zeroX - 1), zeroY));
        result.add(neighborFields(deepCopy(field), zeroX, (byte) (zeroY + 1)));
        result.add(neighborFields(deepCopy(field), zeroX, (byte) (zeroY - 1)));
        return result;
    }

    private SolverState neighborFields(byte[][] field, byte x, byte y) {
        if (x < Field.SIZE && x > -1 && y < Field.SIZE && y > -1) {
            field[zeroY][zeroX] = field[x][y];
            field[x][y] = 0;
            return new SolverState(field);
        }
        return null;
    }

    public boolean isGoaaaaaaal() {
        return heuristic == 0;
    }
}
