package sample.solver;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solver {

    private final Queue<SolverState> result = new ArrayDeque<>();;

    public Queue<SolverState> getResult() {
        return result;
    }

    public Solver(SolverState startState) {
        Queue<Node> openStates = new PriorityQueue<>(Comparator.comparing(this::getWeight));
        List<Integer> visitedStates = new ArrayList<>();
        Node startNode = new Node(null, startState);

        openStates.add(startNode);
        while (!openStates.isEmpty()) {
            Node currentNode = openStates.poll();

            if (currentNode.state.isGoaaaaaaal()) {
                fillResult(new Node(currentNode, currentNode.state));
                return;
            }
            visitedStates.add(currentNode.state.hashCode());
            for (SolverState neighbor : currentNode.state.getNeighbors()) {
                if (neighbor != null && !visitedStates.contains(neighbor.hashCode())) {
                    Node temp = new Node(currentNode, neighbor);
                    openStates.add(temp);
                }
            }
        }
    }

    private int getWeight(Node node) {
        return node.state.getHeuristic() + calculatePath(node);
    }

    private int calculatePath(Node nodeState) {
        Node currentNode = nodeState.parent;
        int pathCounter = 0;
        while (currentNode != null) {
            pathCounter++;
            currentNode = currentNode.parent;
        }
        return pathCounter;
    }

    private void fillResult(Node node) {
        Node currentNode = node.parent;
        while (currentNode != null) {
            result.add(currentNode.state);
            currentNode = currentNode.parent;
        }
    }

    private static class Node {
        private final Node parent;
        private final SolverState state;

        public Node(Node parent, SolverState state) {
            this.parent = parent;
            this.state = state;
        }

        public byte[][] getStateField() {
            return state.getField();
        }
    }
}
