package datastructure.graph;

import java.util.*;

public class UndirectedGraph {
    private static final int DEFAULT_CAPACITY = 10;

    private int numVertices;
    private int numEdges;
    private int[][] adjMatrix;

    public UndirectedGraph() {
        this(DEFAULT_CAPACITY);
    }

    public UndirectedGraph(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Initial capacity cannot be negative.");
        }
        this.adjMatrix = new int[initialCapacity][initialCapacity];
        for (int i = 0; i < initialCapacity; i++) {
            this.adjMatrix[i][i] = -1;
        }
        this.numVertices = 0;
        this.numEdges = 0;
    }

    private void resize(int newCapacity) {
        int[][] newMatrix = new int[newCapacity][newCapacity];
        int oldCapacity = adjMatrix.length;

        for (int i = 0; i < oldCapacity; i++) {
            System.arraycopy(adjMatrix[i], 0, newMatrix[i], 0, oldCapacity);
        }
        for (int i = oldCapacity; i < newCapacity; i++) {
            newMatrix[i][i] = -1;
        }

        this.adjMatrix = newMatrix;
    }

    public boolean isEmpty() {
        return numVertices == 0;
    }

    public boolean hasVertex(int u) {
        return u >= 0 && u < adjMatrix.length && adjMatrix[u][u] == 0;
    }

    public boolean hasEdge(int u, int v) {
        return hasVertex(u) && hasVertex(v) && adjMatrix[u][v] == 1;
    }

    public void addVertex(int u) {
        if (u < 0) {
            throw new IllegalArgumentException("Vertex cannot be negative.");
        }
        if (hasVertex(u)) {
            throw new IllegalArgumentException("Vertex " + u + " already exists in the graph.");
        }
        if (u >= adjMatrix.length) {
            resize(Math.max(u + 1, adjMatrix.length * 2));
        }

        adjMatrix[u][u] = 0;
        numVertices++;
    }

    public void addEdge(int u, int v) {
        if (!hasVertex(u) || !hasVertex(v)) {
            throw new IllegalArgumentException("Both vertices must exist before adding an edge.");
        }
        if (u == v) {
            throw new IllegalArgumentException("Self-loops are not allowed in a simple undirected graph.");
        }
        if (hasEdge(u, v)) {
            throw new IllegalArgumentException("Edge between " + u + " and " + v + " already exists.");
        }

        // Undirected edge goes both ways
        adjMatrix[u][v] = 1;
        adjMatrix[v][u] = 1;
        numEdges++;
    }

    public boolean removeEdge(int u, int v) {
        if (!hasEdge(u, v)) return false;

        adjMatrix[u][v] = 0;
        adjMatrix[v][u] = 0;
        numEdges--;
        return true;
    }

    public boolean removeVertex(int u) {
        if (!hasVertex(u)) return false;

        for (int i = 0; i < adjMatrix.length; i++) {
            if (adjMatrix[u][i] == 1) {
                adjMatrix[u][i] = 0;
                adjMatrix[i][u] = 0;
                numEdges--;
            }
        }

        adjMatrix[u][u] = -1;
        numVertices--;
        return true;
    }

    public int getNumVertices() {
        return numVertices;
    }

    public int getNumEdges() {
        return numEdges;
    }

    public int getDegree(int u) {
        if (!hasVertex(u)) {
            throw new IllegalArgumentException("Vertex " + u + " does not exist in the graph.");
        }

        int degree = 0;
        for (int i = 0; i < adjMatrix.length; i++) {
            if (adjMatrix[u][i] == 1) {
                degree++;
            }
        }
        return degree;
    }

    public int[] getNeighbors(int u) {
        if (!hasVertex(u)) {
            throw new IllegalArgumentException("Vertex " + u + " does not exist in the graph.");
        }

        int[] neighbors = new int[getDegree(u)];
        for (int i = 0, j = 0; i < adjMatrix.length; i++) {
            if (adjMatrix[u][i] == 1) {
                neighbors[j++] = i;
            }
        }

        return neighbors;
    }

    public List<Integer> getVertices() {
        List<Integer> vertices = new ArrayList<>();
        for (int i = 0; i < adjMatrix.length; i++) {
            if (adjMatrix[i][i] == 0) {
                vertices.add(i);
            }
        }
        return vertices;
    }

    public void printGraph() {
        List<Integer> vertices = getVertices();
        if (vertices.isEmpty()) {
            System.out.println("Graph is empty.");
            return;
        }

        System.out.print("    ");
        for (int v : vertices) {
            System.out.printf("%3d", v);
        }
        System.out.println();

        for (int u : vertices) {
            System.out.printf("%3d:", u);
            for (int v : vertices) {
                System.out.printf("%3d", adjMatrix[u][v]);
            }
            System.out.println();
        }
    }

    public boolean isConnectedDFS() {
        if (numVertices <= 1) {
            return true;
        }

        int start = -1;
        for (int i = 0; i < adjMatrix.length; i++) {
            if (adjMatrix[i][i] == 0) {
                start = i;
                break;
            }
        }

        boolean[] visited = new boolean[adjMatrix.length];
        Stack<Integer> stack = new Stack<>();

        visited[start] = true;
        stack.push(start);
        int visitedCount = 1;

        while (!stack.isEmpty()) {
            int current = stack.pop();
            for (int i = 0; i < adjMatrix.length; i++) {
                if (adjMatrix[current][i] == 1 && !visited[i]) {
                    visited[i] = true;
                    stack.push(i);
                    visitedCount++;
                }
            }
        }

        return visitedCount == numVertices;
    }

    public boolean hasHamiltonianCycle() {
        if (getNumVertices() < 3) return false;

        for (int vertex : getVertices()) {
            if (getDegree(vertex) < 2) {
                return false;
            }
        }

        if (!isConnectedDFS()) {
            return false;
        }

        int start = -1;
        for (int i = 0; i < adjMatrix.length; i++) {
            if (adjMatrix[i][i] == 0) {
                start = i;
                break;
            }
        }

        List<Integer> path = new ArrayList<>();
        boolean[] visited = new boolean[adjMatrix.length];

        visited[start] = true;
        path.add(start);

        if (solveHCP(start, start, visited, 1, path)) {
            System.out.println("Hamiltonian Cycle found: " + path);
            return true;
        }

        System.out.println("No Hamiltonian Cycle found.");
        return false;
    }

    private boolean solveHCP(int current, int start, boolean[] visited, int numOfVisited, List<Integer> path) {
        if (numOfVisited == numVertices) {
            if (adjMatrix[current][start] == 1) {
                path.add(start);
                return true;
            }

            return false;
        }


        if (!hasValidDegreeLeft(current, start, visited)) {
            return false;
        }

        if (!isUnvisitedConnected(visited, numOfVisited)) {
            return false;
        }


        for (int neighbor = 0; neighbor < adjMatrix.length; neighbor++) {
            if (adjMatrix[current][neighbor] == 1 && !visited[neighbor]) {
                visited[neighbor] = true;
                path.add(neighbor);

                if (solveHCP(neighbor, start, visited, numOfVisited + 1, path)) {
                    return true;
                }

                visited[neighbor] = false;
                path.removeLast();

            }
        }


        return false;
    }

    private boolean hasValidDegreeLeft(int current, int start, boolean[] visited) {
        for (int i = 0; i < adjMatrix.length; i++) {
            // active and unvisited vertex
            if (adjMatrix[i][i] == 0 && !visited[i]) {
                int degreeNum = 0;

                for (int v = 0; v < adjMatrix.length; v++) {
                    // The edge actually exists then the valid Pool = unvisited U {current, start}
                    if (adjMatrix[i][v] == 1 && (!visited[v] || v == current || v == start)) {
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

    private boolean isUnvisitedConnected(boolean[] visited, int numOfVisited) {

        int seed = -1;
        for (int i = 0; i < adjMatrix.length; i++) {
            if (adjMatrix[i][i] == 0 && !visited[i]) {
                seed = i;
                break;
            }
        }

        boolean[] seen = new boolean[adjMatrix.length];
        Queue<Integer> queue = new ArrayDeque<>();

        seen[seed] = true;
        queue.offer(seed);
        int reachedCount = 1;

        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int v = 0; v < adjMatrix.length; v++) {
                if (adjMatrix[u][v] == 1 && !visited[v] && !seen[v]) {
                    seen[v] = true;
                    queue.offer(v);
                    reachedCount++;
                }
            }
        }

        return reachedCount == numVertices - numOfVisited;

    }


    public static void main(String[] args) {
        UndirectedGraph graph = new UndirectedGraph();

        // Adding vertices
        for (int i = 0; i <= 7; i++) {
            graph.addVertex(i);
        }

        // Adding edges
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(0, 4);

        graph.addEdge(1, 5);
        graph.addEdge(5, 6);
        graph.addEdge(6, 7);
        graph.addEdge(7, 3);

        graph.addEdge(4, 3);
        graph.addEdge(4, 5);

        graph.addEdge(2, 3);

        graph.addEdge(4, 7);


        // Print the graph
        graph.printGraph();

        // Check if the graph is connected
        System.out.println("Is the graph connected? " + graph.isConnectedDFS());

        // Check for Hamiltonian cycle
        System.out.println("Does the graph have a Hamiltonian cycle? " + graph.hasHamiltonianCycle());
    }
}