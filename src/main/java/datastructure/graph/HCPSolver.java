package datastructure.graph;

import java.util.*;

public class HCPSolver {
    public static boolean isConnectedDFS(UndirectedGraph graph) {
        if (graph.numVertices <= 1) {
            return true;
        }

        int start = -1;
        for (int i = 0; i < graph.adjMatrix.length; i++) {
            if (graph.adjMatrix[i][i] == 0) {
                start = i;
                break;
            }
        }

        boolean[] visited = new boolean[graph.adjMatrix.length];
        Stack<Integer> stack = new Stack<>();

        visited[start] = true;
        stack.push(start);
        int visitedCount = 1;

        while (!stack.isEmpty()) {
            int current = stack.pop();
            for (int i = 0; i < graph.adjMatrix.length; i++) {
                if (graph.adjMatrix[current][i] == 1 && !visited[i]) {
                    visited[i] = true;
                    stack.push(i);
                    visitedCount++;
                }
            }
        }

        return visitedCount == graph.numVertices;
    }

    public static boolean hasHamiltonianCycle(UndirectedGraph graph) {
        if (graph.getNumVertices() < 3) return false;

        for (int vertex : graph.getVertices()) {
            if (graph.getDegree(vertex) < 2) {
                return false;
            }
        }

        if (!isConnectedDFS(graph)) {
            return false;
        }

        int start = -1;
        for (int i = 0; i < graph.adjMatrix.length; i++) {
            if (graph.adjMatrix[i][i] == 0) {
                start = i;
                break;
            }
        }

        List<Integer> path = new ArrayList<>();
        boolean[] visited = new boolean[graph.adjMatrix.length];

        visited[start] = true;
        path.add(start);

        if (solveHCP(graph, start, start, visited, 1, path)) {
            System.out.println("Hamiltonian Cycle found: " + path);
            return true;
        }

        System.out.println("No Hamiltonian Cycle found.");
        return false;
    }

    private static boolean solveHCP(UndirectedGraph graph, int current, int start, boolean[] visited, int numOfVisited, List<Integer> path) {
        if (numOfVisited == graph.numVertices) {
            if (graph.adjMatrix[current][start] == 1) {
                path.add(start);
                return true;
            }

            return false;
        }


        if (!hasValidDegreeLeft(graph, current, start, visited)) {
            return false;
        }

        if (!isUnvisitedConnected(graph, visited, numOfVisited)) {
            return false;
        }


        for (int neighbor = 0; neighbor < graph.adjMatrix.length; neighbor++) {
            if (graph.adjMatrix[current][neighbor] == 1 && !visited[neighbor]) {
                visited[neighbor] = true;
                path.add(neighbor);

                if (solveHCP(graph, neighbor, start, visited, numOfVisited + 1, path)) {
                    return true;
                }

                visited[neighbor] = false;
                path.removeLast();

            }
        }


        return false;
    }

    private static boolean hasValidDegreeLeft(UndirectedGraph graph, int current, int start, boolean[] visited) {
        for (int i = 0; i < graph.adjMatrix.length; i++) {
            // active and unvisited vertex
            if (graph.adjMatrix[i][i] == 0 && !visited[i]) {
                int degreeNum = 0;

                for (int v = 0; v < graph.adjMatrix.length; v++) {
                    // The edge actually exists then the valid Pool = unvisited U {current, start}
                    if (graph.adjMatrix[i][v] == 1 && (!visited[v] || v == current || v == start)) {
                        degreeNum++;
                    }
                }

                if (degreeNum < 2) {
                    return false;
                }
            }

        }
        return true;

    }

    private static boolean isUnvisitedConnected(UndirectedGraph graph, boolean[] visited, int numOfVisited) {

        int seed = -1;
        for (int i = 0; i < graph.adjMatrix.length; i++) {
            if (graph.adjMatrix[i][i] == 0 && !visited[i]) {
                seed = i;
                break;
            }
        }

        boolean[] seen = new boolean[graph.adjMatrix.length];
        Queue<Integer> queue = new ArrayDeque<>();

        seen[seed] = true;
        queue.offer(seed);
        int reachedCount = 1;

        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int v = 0; v < graph.adjMatrix.length; v++) {
                if (graph.adjMatrix[u][v] == 1 && !visited[v] && !seen[v]) {
                    seen[v] = true;
                    queue.offer(v);
                    reachedCount++;
                }
            }
        }

        return reachedCount == graph.numVertices - numOfVisited;

    }
}
