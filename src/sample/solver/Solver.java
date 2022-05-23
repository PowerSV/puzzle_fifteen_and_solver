package sample.solver;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class Solver {

    private final Deque<SolverState> result = new ArrayDeque<>();

    public Deque<SolverState> getResult() {
        return result;
    }

    public Solver(SolverState startState) {
        Queue<Node> openStates = new PriorityQueue<>(Comparator.comparing(Node::getWeight));
        Set<SolverState> visitedStates = new HashSet<>();

        Node startNode = new Node(null, startState);
        openStates.add(startNode);

        while (!openStates.isEmpty()) {
            Node currentNode = openStates.poll();
            if (currentNode.state.isFinalState()) {
                fillResult(new Node(currentNode, currentNode.state));
                return;
            }
            visitedStates.add(currentNode.state);
            for (SolverState neighbor : currentNode.state.getNeighbors()) {
                if (!visitedStates.contains(neighbor)) {
                    openStates.add(new Node(currentNode, neighbor));
                }
            }
        }
    }

    private void fillResult(Node node) {
        Node currentNode = node.parent;
        while (currentNode.parent != null) {
            result.add(currentNode.state);
            currentNode = currentNode.parent;
        }
    }

    private static class Node {
        private final Node parent;
        private final SolverState state;
        private final int g; // количесвто шагов от старта
        private final int weight;

        public int getG() {
            return g;
        }

        public int getWeight() {
            return weight;
        }

        public Node(Node parent, SolverState state) {
            this.parent = parent;
            this.state = state;
            this.g = parent == null ? 0 : parent.getG() + 1;
            this.weight = g + state.getHeuristic();
        }
    }
}
