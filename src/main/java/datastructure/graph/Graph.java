package datastructure.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Graph {
    protected int numVertices;
    protected int numEdges;
    protected final Map<Integer, List<Integer>> adjList;

    public Graph() {
        this.adjList = new HashMap<>();
    }

    public abstract boolean isDirected();

    public abstract void addEdge(int u, int v);

    public abstract boolean removeEdge(int u, int v);

    public abstract boolean removeVertex(int u);

    public boolean hasVertex(int u) {
        return adjList.containsKey(u);
    }

    public boolean hasEdge(int u, int v) {
        if (!hasVertex(u) || !hasVertex(v)) return false;
        return adjList.get(u).contains(v);
    }

    public void addVertex(int u) {
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

    public int getDegree(int u) {
        if (!hasVertex(u)) {
            throw new IllegalArgumentException("Vertex " + u + " does not exist in the graph.");
        }
        return adjList.get(u).size();
    }

    public List<Integer> getNeighbors(int u) {
        if (!hasVertex(u)) {
            throw new IllegalArgumentException("Vertex " + u + " does not exist in the graph.");
        }
        return Collections.unmodifiableList(adjList.get(u));
    }

    public void printGraph() {
        for (Map.Entry<Integer, List<Integer>> entry : adjList.entrySet()) {
            System.out.print("Vertex " + entry.getKey() + ": ");
            for (Integer neighbor : entry.getValue()) {
                System.out.print(neighbor + " ");
            }
            System.out.println();
        }
    }
}