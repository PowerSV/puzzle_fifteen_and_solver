package test.solverTest;

import java.util.Arrays;
import java.util.Deque;
import java.util.Queue;

import org.junit.Test;

import sample.Field;
import sample.solver.Solver;
import sample.solver.SolverState;

import static org.junit.Assert.*;

public class SolverTest {
    @Test
    public void solverTest() {
        for (int i = 0; i < 10; i++) {
            Field field = new Field();
            field.shuffle();
            SolverState startState = new SolverState(field.getField());
            Solver solver = new Solver(startState);

            Deque<SolverState> resultOfSolver = solver.getResult();
            assertFalse(resultOfSolver.isEmpty());
            assertArrayEquals(Field.controlArray, resultOfSolver.peek().getField());

            System.out.println(resultOfSolver.size());
            while (!resultOfSolver.isEmpty()) {
                SolverState currentState = resultOfSolver.pollLast();
                for (byte[] line: currentState.getField()) {
                    System.out.println(Arrays.toString(line));
                }
                System.out.println();
            }
        }
    }
}
