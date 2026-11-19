package datastructure.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public abstract class Graph<V> {
    protected int numVertices;
    protected int numEdges;
    protected final Map<V, List<V>> adjList;

    public Graph() {
        this.adjList = new HashMap<>();
    }

    public abstract boolean isDirected();

    public abstract void addEdge(V u, V v);

    public abstract boolean removeEdge(V u, V v);

    public abstract boolean removeVertex(V u);

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

    public void printGraph() {
        for (Map.Entry<V, List<V>> entry : adjList.entrySet()) {
            System.out.print("Vertex " + entry.getKey() + ": ");
            for (V neighbor : entry.getValue()) {
                System.out.print(neighbor + " ");
            }
            System.out.println();
        }
    }
}