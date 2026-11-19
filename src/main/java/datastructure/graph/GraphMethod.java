package datastructure.graph;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class GraphMethod {
    public static <T> boolean isConnected(Graph<T> graph) {
        if (graph.getNumVertices() == 0) {
            return true;
        }

        Set<T> visited = new HashSet<>();
        Stack<T> stack = new Stack<>();

        T start = graph.getVertices().getFirst();

        visited.add(start);
        stack.add(start);

        while (!stack.isEmpty()) {
            T current = stack.pop();
            List<T> neighbors = graph.getNeighbors(current);

            for (T vertex : neighbors) {
                if (visited.contains(vertex)) continue;

                stack.add(vertex);
                visited.add(vertex);
            }
        }

        return visited.size() == graph.getNumVertices();
    }


    public static void main(String[] args) {
        DirectedGraph<Integer> graph = new DirectedGraph<>();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
//        graph.addVertex(5);
//        graph.addVertex(6);


        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);

        graph.printGraph();

        System.out.println(isConnected(graph));
    }
}
