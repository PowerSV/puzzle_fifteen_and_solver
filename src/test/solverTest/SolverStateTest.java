package test.solverTest;

import org.junit.Test;

import sample.solver.SolverState;

import static org.junit.Assert.*;

public class SolverStateTest {

    @Test
    public void heuristicStateTest() {
        SolverState state = new SolverState(new byte[][]{
                {1, 5, 9, 13},
                {2, 6, 10, 14},
                {3, 7, 11, 15},
                {4, 8, 12, 0}
        });
        assertEquals(0, state.getHeuristic());

        state = new SolverState(new byte[][]{
                {1, 5, 9, 13},
                {2, 6, 10, 14},
                {3, 7, 11, 15},
                {4, 8, 0, 12}
        });
        assertEquals(1, state.getHeuristic());

        state = new SolverState(new byte[][]{
                {1, 5, 9, 13},
                {2, 6, 12, 8},
                {3, 11, 0, 10},
                {4, 7, 15, 14}
        });
        assertEquals(18, state.getHeuristic());

        state = new SolverState(new byte[][]{
                {6, 5, 9, 13},
                {1, 8, 2, 0},
                {11, 10, 15, 14},
                {7, 3, 4, 12}
        });
        assertEquals(20, state.getHeuristic());

        state = new SolverState(new byte[][]{
                {1, 7, 13, 14},
                {2, 3, 6, 9},
                {11, 5, 12, 15},
                {4, 0, 10, 8}
        });
        assertEquals(18, state.getHeuristic());

    }
}
