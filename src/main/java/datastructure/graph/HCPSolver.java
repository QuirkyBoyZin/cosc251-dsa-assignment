package datastructure.graph;


public class HCPSolver {

    public static <T> boolean isHamiltonianCycle(Graph<T> graph) {
        if (graph.getNumVertices() < 3) {
            return false;
        }

        for (T vertex : graph.adjList.keySet()) {
            if (graph.getDegree(vertex) < 2) {
                return false;
            }
        }

        if (!graph.isConnected()) {
            return false;
        }

        return false;
    }

    public static void main(String[] args) {

    }
}
