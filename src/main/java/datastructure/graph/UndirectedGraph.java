package datastructure.graph;

import java.util.*;

public class UndirectedGraph<V> {
    private int numVertices;
    private int numEdges;
    private final Map<V, List<V>> adjList;

    public UndirectedGraph() {
        this.adjList = new HashMap<>();
    }

    public boolean isEmpty() {
        return numVertices == 0;
    }

    public boolean hasVertex(V u) {
        return adjList.containsKey(u);
    }

    public boolean hasEdge(V u, V v) {
        if (!hasVertex(u) || !hasVertex(v)) return false;
        return adjList.get(u).contains(v);
    }

    public void addVertex(V u) {
        Objects.requireNonNull(u, "Vertex cannot be null.");
        if (hasVertex(u)) {
            throw new IllegalArgumentException("Vertex " + u + " already exists in the graph.");
        }
        adjList.put(u, new ArrayList<>());
        numVertices++;
    }

    public void addEdge(V u, V v) {
        if (!hasVertex(u) || !hasVertex(v)) {
            throw new IllegalArgumentException("Both vertices must exist before adding an edge.");
        }
        if (u.equals(v)) {
            throw new IllegalArgumentException("Self-loops are not allowed in a simple undirected graph.");
        }
        if (hasEdge(u, v)) {
            throw new IllegalArgumentException("Edge between " + u + " and " + v + " already exists.");
        }

        // Undirected edge goes both ways
        adjList.get(u).add(v);
        adjList.get(v).add(u);
        numEdges++;
    }

    public boolean removeEdge(V u, V v) {
        if (!hasEdge(u, v)) return false;

        adjList.get(u).remove(v);
        adjList.get(v).remove(u);
        numEdges--;
        return true;
    }

    public boolean removeVertex(V u) {
        if (!hasVertex(u)) return false;

        List<V> neighbors = adjList.remove(u);
        numEdges -= neighbors.size();

        for (V neighbor : neighbors) {
            adjList.get(neighbor).remove(u);
        }

        numVertices--;
        return true;
    }

    public int getNumVertices() {
        return numVertices;
    }

    public int getNumEdges() {
        return numEdges;
    }

    public int getDegree(V u) {
        if (!hasVertex(u)) {
            throw new IllegalArgumentException("Vertex " + u + " does not exist in the graph.");
        }
        return adjList.get(u).size();
    }

    public List<V> getNeighbors(V u) {
        if (!hasVertex(u)) {
            throw new IllegalArgumentException("Vertex " + u + " does not exist in the graph.");
        }
        return Collections.unmodifiableList(adjList.get(u));
    }

    public List<V> getVertices() {
        return new ArrayList<>(adjList.keySet());
    }

    public void printGraph() {
        for (Map.Entry<V, List<V>> entry : adjList.entrySet()) {
            System.out.print("Vertex " + entry.getKey() + ": ");
            for (V neighbor : entry.getValue()) {
                System.out.print(neighbor + " ");
            }
            System.out.println();
        }
    }

    public boolean isConnected() {
        if (numVertices <= 1) {
            return true;
        }

        Set<V> visited = new HashSet<>();
        Deque<V> queue = new ArrayDeque<>();
        V start = getVertices().getFirst();

        visited.add(start);
        queue.offer(start);

        while (!queue.isEmpty()) {
            V current = queue.poll();
            for (V neighbor : getNeighbors(current)) {
                if (visited.add(neighbor)) {
                    queue.offer(neighbor);
                }
            }
        }

        return visited.size() == numVertices;
    }

    public boolean hasHamiltonianCycle() {
        if (getNumVertices() < 3) return false;

        for (V vertex : getVertices()) {
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