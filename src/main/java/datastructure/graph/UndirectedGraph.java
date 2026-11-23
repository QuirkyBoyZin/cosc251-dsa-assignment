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

    public List<Integer> getNeighbors(int u) {
        if (!hasVertex(u)) {
            throw new IllegalArgumentException("Vertex " + u + " does not exist in the graph.");
        }

        List<Integer> neighbors = new ArrayList<>();
        for (int i = 0; i < adjMatrix.length; i++) {
            if (adjMatrix[u][i] == 1) {
                neighbors.add(i);
            }
        }
        return Collections.unmodifiableList(neighbors);
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

    public boolean isConnected() {
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
        Queue<Integer> queue = new ArrayDeque<>();

        visited[start] = true;
        queue.offer(start);
        int visitedCount = 1;

        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int i = 0; i < adjMatrix.length; i++) {
                if (adjMatrix[current][i] == 1 && !visited[i]) {
                    visited[i] = true;
                    queue.offer(i);
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

        if (!isConnected()) {
            return false;
        }


        return true;
    }
}