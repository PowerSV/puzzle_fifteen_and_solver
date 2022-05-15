package sample.solver;

import java.util.List;

public class Solver {

    private List<SolverState> result;
    private SolverState startState;

    void solve(int[][] array){
        //todo
    }

    void heuristic(){
        //todo
    }

    private static class Node {
        private final Node parent;
        private final SolverState state;

        public Node(Node parent, SolverState state) {
            this.parent = parent;
            this.state = state;
        }

        public SolverState getState() {
            return state;
        }
    }
}
